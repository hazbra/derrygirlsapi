DROP TABLE IF EXISTS episode;
DROP TABLE IF EXISTS season;

CREATE TABLE season (
    season_id   INTEGER      NOT NULL AUTO_INCREMENT,
    season_name VARCHAR(255) DEFAULT NULL,
    PRIMARY KEY (season_id)
);

CREATE TABLE episode (
    episode_id INTEGER NOT NULL AUTO_INCREMENT,
    episode_name VARCHAR (255) DEFAULT NULL,
    description VARCHAR (2000) DEFAULT NULL,
    season_id INTEGER DEFAULT NULL,
    PRIMARY KEY (episode_id),
    CONSTRAINT episode_ibfk_1 FOREIGN KEY (season_id) REFERENCES season (season_id)
);