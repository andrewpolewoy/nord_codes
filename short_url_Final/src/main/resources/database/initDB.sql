-- Table: shorter.shorter

-- DROP TABLE shorter.shorter;

CREATE TABLE IF NOT EXISTS shorter.shorter
(
    id           SERIAL PRIMARY KEY,
    hash         varchar(20) not null unique,
    original_url varchar,
    created_at   timestamp
);