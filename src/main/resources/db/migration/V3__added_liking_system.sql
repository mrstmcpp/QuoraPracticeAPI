CREATE TABLE likes
(
    id         INT AUTO_INCREMENT NOT NULL,
    created_at datetime NOT NULL,
    user_id    INT      NOT NULL,
    type       VARCHAR(255) NULL,
    entity_id  BIGINT NULL,
    CONSTRAINT pk_likes PRIMARY KEY (id)
);

ALTER TABLE likes
    ADD CONSTRAINT uc_2fc6767d7e694c0a4242450d0 UNIQUE (user_id);

ALTER TABLE likes
    ADD CONSTRAINT FK_LIKES_ON_USER FOREIGN KEY (user_id) REFERENCES user (id);