package in.dragonbra.klayb0t.retrofit;

import in.dragonbra.klayb0t.retrofit.response.jackbox.JackboxRoom;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * @author lngtr
 * @since 2018-08-25
 */
public interface JackboxInterface {

    String BASE_URL = "http://blobcast.jackboxgames.com/";

    @GET("room/{code}")
    Call<JackboxRoom> getRoom(@Path("code") String code);
}
