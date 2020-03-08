create table comment
(
	id BIGINT auto_increment,
	parent_id BIGINT not null,
	type INT,
	commentator BIGINT,
	gmt_create BIGINT,
	gmt_modified BIGINT,
	like_count BIGINT default 0,
	content VARCHAR(1024),
	comment_count INT default 0,
	constraint comment_pk
		primary key (id)
);