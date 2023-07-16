-- liquibase formatted sql

--changeset el:1
CREATE INDEX name_search_index ON student (name);

--changeset el:2
CREATE INDEX color_search_index ON faculty (name , color);
