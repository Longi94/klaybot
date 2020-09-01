package in.dragonbra.klayb0t.retrofit;

import in.dragonbra.klayb0t.retrofit.response.TwitchChattersResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface TwitchTmiInterface {

    String BASE_URL = "https://tmi.twitch.tv/";

    @GET("group/user/{channelName}/chatters")
    Call<TwitchChattersResponse> getChatters(@Path("channelName") String channelName);
}
