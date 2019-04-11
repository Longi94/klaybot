package in.dragonbra.klayb0t.retrofit;

import in.dragonbra.klayb0t.retrofit.response.TwitchStreamsResponse;
import org.springframework.lang.Nullable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @author lngtr
 * @since 2017-12-31
 */
public interface TwitchInterface {

    String BASE_URL = "https://api.twitch.tv/";

    @GET("helix/streams")
    Call<TwitchStreamsResponse> getStreams(@Nullable @Query("user_id") String userId);
}
