CREATE TABLE jackbox_game (
  id      BIGINT       NOT NULL AUTO_INCREMENT,
  code    VARCHAR(4)   NOT NULL,
  server  VARCHAR(255),
  app_tag VARCHAR(255) NOT NULL,
  app_id  VARCHAR(255),
  created DATETIME     NOT NULL DEFAULT NOW(),
  PRIMARY KEY (id)
);
