package in.dragonbra.klayb0t.retrofit.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TwitchChatters {

    @SerializedName("broadcaster")
    @Expose
    private List<String> broadcaster;

    @SerializedName("vips")
    @Expose
    private List<String> vips;

    @SerializedName("moderators")
    @Expose
    private List<String> moderators;

    @SerializedName("staff")
    @Expose
    private List<String> staff;

    @SerializedName("admins")
    @Expose
    private List<String> admins;

    @SerializedName("global_mods")
    @Expose
    private List<String> globalMods;

    @SerializedName("viewers")
    @Expose
    private List<String> viewers;

    public List<String> getBroadcaster() {
        return broadcaster;
    }

    public void setBroadcaster(List<String> broadcaster) {
        this.broadcaster = broadcaster;
    }

    public List<String> getVips() {
        return vips;
    }

    public void setVips(List<String> vips) {
        this.vips = vips;
    }

    public List<String> getModerators() {
        return moderators;
    }

    public void setModerators(List<String> moderators) {
        this.moderators = moderators;
    }

    public List<String> getStaff() {
        return staff;
    }

    public void setStaff(List<String> staff) {
        this.staff = staff;
    }

    public List<String> getAdmins() {
        return admins;
    }

    public void setAdmins(List<String> admins) {
        this.admins = admins;
    }

    public List<String> getGlobalMods() {
        return globalMods;
    }

    public void setGlobalMods(List<String> globalMods) {
        this.globalMods = globalMods;
    }

    public List<String> getViewers() {
        return viewers;
    }

    public void setViewers(List<String> viewers) {
        this.viewers = viewers;
    }
}
