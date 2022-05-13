create table if not exists candidates (
    id         serial primary key,
    name       text,
    experience text,
    salary     DOUBLE PRECISION
);