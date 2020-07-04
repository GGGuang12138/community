rename table user to user_auth;

alter table user_auth
	add auth_type varchar(50) null;

alter table user_auth
	add user_id bigint null;

alter table user_auth
	add password char(36) null;