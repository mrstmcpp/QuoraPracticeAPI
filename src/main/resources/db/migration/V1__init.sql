CREATE TABLE IF NOT EXISTS answer
(
    id          INT AUTO_INCREMENT NOT NULL,
    created_at  datetime           NOT NULL,
    text        VARCHAR(255)       NOT NULL,
    question_id INT                NOT NULL,
    user_id     INT                NOT NULL,
    CONSTRAINT pk_answer PRIMARY KEY (id)
);

CREATE TABLE if not exists comment
(
    id                INT AUTO_INCREMENT NOT NULL,
    created_at        datetime           NOT NULL,
    text              VARCHAR(255)       NOT NULL,
    answer_id         INT                NOT NULL,
    user_id           INT                NOT NULL,
    parent_comment_id INT                NULL,
    CONSTRAINT pk_comment PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS question
(
    id         INT AUTO_INCREMENT NOT NULL,
    created_at datetime           NOT NULL,
    title      VARCHAR(100)       NOT NULL,
    body       VARCHAR(255)       NULL,
    userid     INT                NOT NULL,
    CONSTRAINT pk_question PRIMARY KEY (id)
);

CREATE TABLE question_topics
(
    question_id INT NOT NULL,
    topic_id    INT NOT NULL
);

CREATE TABLE IF NOT EXISTS topic
(
    id   INT AUTO_INCREMENT NOT NULL,
    name VARCHAR(255)       NOT NULL,
    CONSTRAINT pk_topic PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS user
(
    id       INT AUTO_INCREMENT NOT NULL,
    username VARCHAR(255)       NULL,
    password VARCHAR(255)       NULL,
    email    VARCHAR(255)       NULL,
    bio      VARCHAR(255)       NULL,
    CONSTRAINT pk_user PRIMARY KEY (id)
);

ALTER TABLE question
    ADD CONSTRAINT uc_question_title UNIQUE (title);

ALTER TABLE topic
    ADD CONSTRAINT uc_topic_name UNIQUE (name);

ALTER TABLE user
    ADD CONSTRAINT uc_user_email UNIQUE (email);

ALTER TABLE user
    ADD CONSTRAINT uc_user_username UNIQUE (username);

ALTER TABLE answer
    ADD CONSTRAINT FK_ANSWER_ON_QUESTION FOREIGN KEY (question_id) REFERENCES question (id);

ALTER TABLE answer
    ADD CONSTRAINT FK_ANSWER_ON_USER FOREIGN KEY (user_id) REFERENCES user (id);

ALTER TABLE comment
    ADD CONSTRAINT FK_COMMENT_ON_ANSWER FOREIGN KEY (answer_id) REFERENCES answer (id);

ALTER TABLE comment
    ADD CONSTRAINT FK_COMMENT_ON_PARENT_COMMENT FOREIGN KEY (parent_comment_id) REFERENCES comment (id);

ALTER TABLE comment
    ADD CONSTRAINT FK_COMMENT_ON_USER FOREIGN KEY (user_id) REFERENCES user (id);

ALTER TABLE question
    ADD CONSTRAINT FK_QUESTION_ON_USERID FOREIGN KEY (userid) REFERENCES user (id);

ALTER TABLE question_topics
    ADD CONSTRAINT fk_quetop_on_question FOREIGN KEY (question_id) REFERENCES question (id);

ALTER TABLE question_topics
    ADD CONSTRAINT fk_quetop_on_topic FOREIGN KEY (topic_id) REFERENCES topic (id);