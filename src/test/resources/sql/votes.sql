create table votes 
(
	id bigint not null, 
	userId bigint, 
	CONSTRAINT t_votes_pkey primary key (id)
);

