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

    @Column(name = "twitch_expiry", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date twitchTokenExpiry;

    @Column(name = "username", nullable = false)
    private String username;

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

    public Date getTwitchTokenExpiry() {
        return twitchTokenExpiry;
    }

    public void setTwitchTokenExpiry(Date twitchTokenExpiry) {
        this.twitchTokenExpiry = twitchTokenExpiry;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
