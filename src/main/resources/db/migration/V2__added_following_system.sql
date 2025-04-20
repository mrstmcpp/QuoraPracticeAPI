CREATE TABLE user_following
(
    followee_id INT NOT NULL,
    follower_id INT NOT NULL,
    CONSTRAINT pk_user_following PRIMARY KEY (followee_id, follower_id)
);

ALTER TABLE user_following
    ADD CONSTRAINT fk_usefol_on_followee FOREIGN KEY (followee_id) REFERENCES user (id);

ALTER TABLE user_following
    ADD CONSTRAINT fk_usefol_on_follower FOREIGN KEY (follower_id) REFERENCES user (id);
