create table comment
(
	id BIGINT auto_increment,
	parent_id BIGINT not null,
	type int not null,
	commentator int not null,
	gmt_create BIGINT not null,
	gmt_modified BIGINT,
	like_count BIGINT default 0,
	constraint comment_pk
		primary key (id)
);