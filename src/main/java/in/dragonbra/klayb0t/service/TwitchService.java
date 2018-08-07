package in.dragonbra.klayb0t.service;

import in.dragonbra.klayb0t.controller.exception.UnauthorizedException;
import in.dragonbra.klayb0t.entity.User;
import in.dragonbra.klayb0t.repository.UserRepository;
import in.dragonbra.klayb0t.retrofit.TwitchIdInterface;
import in.dragonbra.klayb0t.retrofit.TwitchInterface;
import in.dragonbra.klayb0t.retrofit.response.TwitchAccessTokenResponse;
import in.dragonbra.klayb0t.retrofit.response.TwitchCheckUserSubResponse;
import in.dragonbra.klayb0t.retrofit.response.TwitchUsersResponse;
import in.dragonbra.klayb0t.util.SessionAttributeNames;
import okhttp3.HttpUrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import retrofit2.Response;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author lngtr
 * @since 2017-12-31
 */
@Service
public class TwitchService {

    private static final SimpleDateFormat SUB_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");

    private static final long MAX_EXPIRY_GAP = TimeUnit.MINUTES.toMillis(15);

    @Value("${twitch.client-id}")
    private String twitchClientId;

    @Value("${twitch.client-secret}")
    private String twitchClientSecret;

    @Value("${twitch.channel_id}")
    private String channelId;

    private String redirectUri = "http://localhost:8080/twitch/klaybot/authorize";

    private final TwitchInterface twitchInterface;

    private final TwitchIdInterface twitchIdInterface;

    private final UserRepository userRepository;

    @Autowired
    public TwitchService(TwitchInterface twitchInterface,
                         TwitchIdInterface twitchIdInterface,
                         UserRepository userRepository) {
        this.twitchInterface = twitchInterface;
        this.twitchIdInterface = twitchIdInterface;
        this.userRepository = userRepository;
    }

    public String getLoginUrl(HttpSession session) {
        String state = new BigInteger(130, new SecureRandom()).toString(32);
        session.setAttribute(SessionAttributeNames.LOGIN_STATE, state);

        HttpUrl url = new HttpUrl.Builder()
                .scheme("https")
                .host("id.twitch.tv")
                .addPathSegments("oauth2/authorize")
                .addQueryParameter("client_id", twitchClientId)
                .addQueryParameter("redirect_uri", redirectUri)
                .addQueryParameter("response_type", "code")
                .addQueryParameter("scope", "user_subscriptions")
                .addQueryParameter("state", state)
                .build();

        return url.toString();
    }

    public void authorize(HttpServletRequest request) {
        try {
            String code = request.getParameter("code");
            String state = request.getParameter("state");

            if (!state.equals(request.getSession().getAttribute(SessionAttributeNames.LOGIN_STATE))) {
                throw new UnauthorizedException();
            }

            TwitchAccessTokenResponse token = twitchIdInterface.getAccessToken(twitchClientId, twitchClientSecret,
                    code, "authorization_code", redirectUri).execute().body();

            if (token == null) {
                throw new RuntimeException();
            }

            Response<TwitchUsersResponse> resp = twitchInterface.getUser("Bearer " + token.getAccessToken())
                    .execute();

            TwitchUsersResponse usersResponse = resp.body();

            String userId = usersResponse.getUsers().get(0).getId();

            User user = userRepository.findByTwitchId(userId);

            if (user == null) {
                user = new User();
            }

            user.setUsername(usersResponse.getUsers().get(0).getLogin());
            user.setTwitchAccessToken(token.getAccessToken());
            user.setTwitchRefreshToken(token.getRefreshToken());
            user.setTokenExpiresIn(token.getExpiresIn().longValue() * 1000L);
            user.setLastTokenRefresh(new Date());
            user.setTwitchId(userId);

            userRepository.save(user);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public long checkSub(String name) throws IOException, ParseException {

        User user = userRepository.findByUsername(name);

        if (user == null) {
            return -1;
        }

        validateToken(user);

        Response<TwitchCheckUserSubResponse> response = twitchInterface
                .checkUserSubByChannel("OAuth " + user.getTwitchAccessToken(), user.getTwitchId(), channelId).execute();

        if (response.code() == 422) {
            return -2;
        }

        if (response.code() == 401) {
            // retry after refreshing
            validateToken(user);

            response = twitchInterface
                    .checkUserSubByChannel("OAuth " + user.getTwitchAccessToken(), user.getTwitchId(), channelId).execute();
        }

        TwitchCheckUserSubResponse subResponse = response.body();

        Date subDate = SUB_DATE_FORMAT.parse(subResponse.getCreatedAt());

        return subDate.getTime();
    }

    private void validateToken(User user) throws IOException {
        if (user.getLastTokenRefresh().getTime() + user.getTokenExpiresIn() > System.currentTimeMillis() - MAX_EXPIRY_GAP) {
            return;
        }

        Response<TwitchAccessTokenResponse> response = twitchIdInterface
                .refreshAccessToken(twitchClientId, twitchClientSecret, user.getTwitchRefreshToken(), "refresh_token").execute();

        TwitchAccessTokenResponse tokenResponse = response.body();

        user.setTwitchAccessToken(tokenResponse.getAccessToken());
        user.setTwitchRefreshToken(tokenResponse.getRefreshToken());
        user.setLastTokenRefresh(new Date());

        userRepository.save(user);
    }
}
