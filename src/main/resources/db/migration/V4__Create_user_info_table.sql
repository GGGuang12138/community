create table user_info
(
	id bigint auto_increment,
	name varchar(50) null,
	email varchar(50) null,
	mobile varchar(50) null comment '手机号码',
	sign varchar(256) null comment '个性签名',
	avatar_url varchar(100) null comment '头像',
	gmt_create bigint null comment '创建时间',
	gmt_modified bigint null comment '最后修改时间',
	status bigint(4) null comment '状态',
	constraint user_info_pk
		primary key (id)
);