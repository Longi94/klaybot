package in.dragonbra.klayb0t.retrofit.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * @author lngtr
 * @since 2018-08-07
 */
public class TwitchValidateResult {

    @SerializedName("client_id")
    @Expose
    private String clientId;

    @SerializedName("login")
    @Expose
    private String login;

    @SerializedName("scopes")
    @Expose
    private String[] scopes;

    @SerializedName("user_id")
    @Expose
    private String userId;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String[] getScopes() {
        return scopes;
    }

    public void setScopes(String[] scopes) {
        this.scopes = scopes;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
