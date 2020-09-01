package in.dragonbra.klayb0t.retrofit.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class TwitchChattersResponse {

    @SerializedName("_links")
    @Expose
    private Map<String, Object> links;

    @SerializedName("chatter_count")
    @Expose
    private int chatterCount;

    @SerializedName("chatters")
    @Expose
    private TwitchChatters chatters;

    public Map<String, Object> getLinks() {
        return links;
    }

    public void setLinks(Map<String, Object> links) {
        this.links = links;
    }

    public int getChatterCount() {
        return chatterCount;
    }

    public void setChatterCount(int chatterCount) {
        this.chatterCount = chatterCount;
    }

    public TwitchChatters getChatters() {
        return chatters;
    }

    public void setChatters(TwitchChatters chatters) {
        this.chatters = chatters;
    }
}
