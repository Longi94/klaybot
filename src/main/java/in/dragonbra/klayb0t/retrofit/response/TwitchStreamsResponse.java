package in.dragonbra.klayb0t.retrofit.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author lngtr
 * @since 2018-10-04
 */
public class TwitchStreamsResponse {

    @SerializedName("data")
    @Expose
    private List<TwitchStream> streams = null;

    public List<TwitchStream> getStreams() {
        return streams;
    }

    public void setStreams(List<TwitchStream> streams) {
        this.streams = streams;
    }
}
