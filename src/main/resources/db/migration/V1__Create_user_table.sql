create table user
(
	id BIGINT auto_increment,
	account_id VARCHAR(100),
	name VARCHAR(50),
	token char(36),
	gmt_create BIGINT,
	gmt_modified BIGINT,
	bio VARCHAR(256),
	avatar_url VARCHAR(100),
	constraint user_pk
		primary key (id)
);
