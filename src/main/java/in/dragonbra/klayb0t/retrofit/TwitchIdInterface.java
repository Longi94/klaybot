package in.dragonbra.klayb0t.retrofit;

import in.dragonbra.klayb0t.retrofit.response.TwitchAccessTokenResponse;
import in.dragonbra.klayb0t.retrofit.response.TwitchValidateResult;
import retrofit2.Call;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * @author lngtr
 * @since 2018-08-07
 */
public interface TwitchIdInterface {
    String BASE_URL = "https://id.twitch.tv/";

    @POST("oauth2/token")
    Call<TwitchAccessTokenResponse> getAccessToken(
            @Query("client_id") String clientId,
            @Query("client_secret") String clientSecret,
            @Query("code") String code,
            @Query("grant_type") String grantType,
            @Query("redirect_uri") String redirectUri
    );

    @POST("oauth2/validate")
    Call<TwitchValidateResult> validate(
            @Header("Authorization") String authToken
    );

    @POST("oauth2/token")
    Call<TwitchAccessTokenResponse> refreshAccessToken(
            @Query("client_id") String clientId,
            @Query("client_secret") String clientSecret,
            @Query("refresh_token") String code,
            @Query("grant_type") String grantType
    );
}
