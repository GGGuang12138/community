alter table QUESTION drop column CONTENT;
alter table COMMENT
	add content VARCHAR(1024);
