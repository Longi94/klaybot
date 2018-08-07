package in.dragonbra.klayb0t.retrofit.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author lngtr
 * @since 2018-01-01
 */
public class TwitchUsersResponse {

    @SerializedName("data")
    @Expose
    private List<TwitchUser> users = null;

    public List<TwitchUser> getUsers() {
        return users;
    }

    public void setUsers(List<TwitchUser> users) {
        this.users = users;
    }

}
