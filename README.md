## 基于Spring Boot的问答社区网站

## 资料
[Spring文档](https://spring.io/guides)

[Github OAuth](https://developer.github.com/apps/building-oauth-apps/creating-an-oauth-app/)

[Github 登陆](https://developer.github.com/apps/building-oauth-apps/authorizing-oauth-apps/)


## 工具

git 

## 脚本
```sql
    create table USER
    (
    	ID INT auto_increment,
    	ACCOUNT_ID VARCHAR(100),
    	NAME VARCHAR(50),
    	TOKEN CHAR(36),
    	GMT_CREATE BIGINT,
    	GMT_MODIFIED BIGINT,
    	constraint USER_PK
    		primary key (ID)
    );
    
```

 

