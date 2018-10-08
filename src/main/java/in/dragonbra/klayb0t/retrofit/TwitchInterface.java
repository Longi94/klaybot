package in.dragonbra.klayb0t.retrofit;

import in.dragonbra.klayb0t.retrofit.response.TwitchAccessTokenResponse;
import in.dragonbra.klayb0t.retrofit.response.TwitchCheckUserSubResponse;
import in.dragonbra.klayb0t.retrofit.response.TwitchStreamsResponse;
import in.dragonbra.klayb0t.retrofit.response.TwitchUsersResponse;
import org.springframework.lang.Nullable;
import retrofit2.Call;
import retrofit2.http.*;

/**
 * @author lngtr
 * @since 2017-12-31
 */
public interface TwitchInterface {

    String BASE_URL = "https://api.twitch.tv/";

    @POST("kraken/oauth2/token")
    Call<TwitchAccessTokenResponse> refreshAccessToken(
            @Query("client_id") String clientId,
            @Query("client_secret") String clientSecret,
            @Query("refresh_token") String code,
            @Query("grant_type") String grantType
    );

    @GET("helix/users")
    Call<TwitchUsersResponse> getUser(@Header("Authorization") String bearerStr);

    @GET("helix/streams")
    Call<TwitchStreamsResponse> getStreams(@Nullable @Query("user_id") String userId);

    @Deprecated
    @GET("kraken/users/{userId}/subscriptions/{channelId}")
    Call<TwitchCheckUserSubResponse> checkUserSubByChannel(
            @Header("Authorization") String oauth,
            @Path("userId") String userId,
            @Path("channelId") String channelId
    );
}
