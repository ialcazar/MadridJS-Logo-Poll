DROP TABLE if exists users CASCADE;
DROP SEQUENCE if exists users_sec;
CREATE SEQUENCE users_sec START 1;
CREATE TABLE  users
(
  user_id bigint NOT NULL DEFAULT nextval('users_sec'),
  email varchar(255),
  name varchar(255),
  surname varchar(255),
  CONSTRAINT t_users_pkey PRIMARY KEY (user_id)
);

DROP TABLE if exists votes CASCADE;
create table votes 
(
	id bigint not null, 
    description varchar(255),
	count int,
	url varchar(255),
	CONSTRAINT t_votes_pkey primary key (id)
);