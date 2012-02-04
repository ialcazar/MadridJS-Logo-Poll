DROP TABLE if exists users;
DROP SEQUENCE if exists users_sec;
CREATE SEQUENCE users_sec START 1;
CREATE TABLE  users
(
  id bigint NOT NULL DEFAULT nextval('users_sec'),
  email varchar(255),
  name varchar(255),
  surname varchar(255),
  CONSTRAINT t_users_pkey PRIMARY KEY (id)
);