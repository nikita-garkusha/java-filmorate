DROP TABLE IF EXISTS films CASCADE;
DROP TABLE IF EXISTS film_genres CASCADE;
DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS friendships CASCADE;
DROP TABLE IF EXISTS film_likes CASCADE;

CREATE TABLE IF NOT EXISTS mpa_ratings
(
    mpa_rating_id INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    name          VARCHAR NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS films
(
    film_id             BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    name                VARCHAR NOT NULL,
    description         VARCHAR(200),
    release_date        DATE,
    duration_in_minutes INTEGER CHECK (duration_in_minutes > 0),
    mpa_rating_id       INTEGER REFERENCES mpa_ratings (mpa_rating_id)
);

CREATE TABLE IF NOT EXISTS genres
(
    genre_id INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    name     VARCHAR NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS film_genres
(
    film_id  BIGINT  NOT NULL REFERENCES films (film_id),
    genre_id INTEGER NOT NULL REFERENCES genres (genre_id)
);

CREATE TABLE IF NOT EXISTS users
(
    user_id  BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    email    VARCHAR NOT NULL UNIQUE,
    login    VARCHAR NOT NULL UNIQUE,
    name     VARCHAR NOT NULL,
    birthday DATE    NOT NULL
);

CREATE TABLE IF NOT EXISTS friendships
(
    from_user_id BIGINT  NOT NULL REFERENCES users (user_id),
    to_user_id   BIGINT  NOT NULL REFERENCES users (user_id),
    isMutual     BOOLEAN NOT NULL,
    PRIMARY KEY (from_user_id, to_user_id)
);

CREATE TABLE IF NOT EXISTS film_likes
(
    film_id BIGINT NOT NULL REFERENCES films (film_id),
    user_id BIGINT NOT NULL REFERENCES users (user_id)
);





