package in.dragonbra.klayb0t.retrofit.response.jackbox;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * @author lngtr
 * @since 2018-08-25
 */
public class JackboxRoom {

    @SerializedName("success")
    @Expose
    private Boolean success;

    @SerializedName("error")
    @Expose
    private String error;

    @SerializedName("roomid")
    @Expose
    private String roomId;

    @SerializedName("server")
    @Expose
    private String server;

    @SerializedName("apptag")
    @Expose
    private String appTag;

    @SerializedName("appid")
    @Expose
    private String appId;

    @SerializedName("numPlayers")
    @Expose
    private int numPlayers;

    @SerializedName("numAudience")
    @Expose
    private int numAudience;

    @SerializedName("access_token")
    @Expose
    private String joinAs;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getAppTag() {
        return appTag;
    }

    public void setAppTag(String appTag) {
        this.appTag = appTag;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public int getNumPlayers() {
        return numPlayers;
    }

    public void setNumPlayers(int numPlayers) {
        this.numPlayers = numPlayers;
    }

    public int getNumAudience() {
        return numAudience;
    }

    public void setNumAudience(int numAudience) {
        this.numAudience = numAudience;
    }

    public String getJoinAs() {
        return joinAs;
    }

    public void setJoinAs(String joinAs) {
        this.joinAs = joinAs;
    }
}
