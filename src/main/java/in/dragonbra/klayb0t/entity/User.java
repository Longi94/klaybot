package in.dragonbra.klayb0t.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * @author lngtr
 * @since 2018-01-01
 */
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "twitch_id", nullable = false)
    private String twitchId;

    @Column(name = "twitch_access_token", nullable = false)
    private String twitchAccessToken;

    @Column(name = "twitch_refresh_token", nullable = false)
    private String twitchRefreshToken;

    @Column(name = "token_expires_in", nullable = false)
    private Long tokenExpiresIn;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "last_token_refresh", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastTokenRefresh;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTwitchId() {
        return twitchId;
    }

    public void setTwitchId(String twitchId) {
        this.twitchId = twitchId;
    }

    public String getTwitchAccessToken() {
        return twitchAccessToken;
    }

    public void setTwitchAccessToken(String twitchAccessToken) {
        this.twitchAccessToken = twitchAccessToken;
    }

    public String getTwitchRefreshToken() {
        return twitchRefreshToken;
    }

    public void setTwitchRefreshToken(String twitchRefreshToken) {
        this.twitchRefreshToken = twitchRefreshToken;
    }

    public Long getTokenExpiresIn() {
        return tokenExpiresIn;
    }

    public void setTokenExpiresIn(Long tokenExpiresIn) {
        this.tokenExpiresIn = tokenExpiresIn;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getLastTokenRefresh() {
        return lastTokenRefresh;
    }

    public void setLastTokenRefresh(Date lastTokenRefresh) {
        this.lastTokenRefresh = lastTokenRefresh;
    }
}
