CREATE TABLE user (
  id                   BIGINT       NOT NULL AUTO_INCREMENT,
  twitch_access_token  VARCHAR(255) NOT NULL,
  twitch_id            VARCHAR(255) NOT NULL,
  twitch_refresh_token VARCHAR(255) NOT NULL,
  last_token_refresh   DATETIME     NOT NULL,
  username             VARCHAR(255) NOT NULL,
  token_expires_in     BIGINT       NOT NULL,
  PRIMARY KEY (id)
);
