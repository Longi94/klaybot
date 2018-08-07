package in.dragonbra.klayb0t.retrofit.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * @author lngtr
 * @since 2017-12-31
 */
public class TwitchAccessTokenResponse {

    @SerializedName("access_token")
    @Expose
    private String accessToken;

    @SerializedName("refresh_token")
    @Expose
    private String refreshToken;

    @SerializedName("expires_in")
    @Expose
    private Integer expiresIn;

    @SerializedName("scope")
    @Expose
    private String[] scope;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public Integer getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Integer expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String[] getScope() {
        return scope;
    }

    public void setScope(String[] scope) {
        this.scope = scope;
    }
}
