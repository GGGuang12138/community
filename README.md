## 基于Spring Boot的问答社区网站

## 快速运行
1. 安装必备工具  
JDK，Maven
2. 克隆代码到本地  
3. 创建数据库
4. 运行项目  
```sh
java -jar target/community-0.0.1-SNAPSHOT.jar
```

5. 访问项目
```
http://localhost:8887
```

## 资料
[Spring Boot介绍](https://spring.io/projects/spring-boot/)

[Spring Boot开发文档](https://docs.spring.io/spring-boot/docs/2.0.0.RC1/reference/htmlsingle/)

[Spring Boot整合Mybatis](http://mybatis.org/spring-boot-starter/mybatis-spring-boot-autoconfigure/)

[Spring Security参考博客](https://www.pomit.cn/blog.html?catory=SpringBoot%u4E13%u9898#!)

[Github OAuth](https://developer.github.com/apps/building-oauth-apps/creating-an-oauth-app/)

[thymeleaf](https://www.thymeleaf.org/doc/tutorials/3.0/usingthymeleaf.html#a-website-for-a-grocery)

[bootstrap](https://v3.bootcss.com/components/)

[flyway数据库版本管理工具](https://flywaydb.org/documentation/)

[项目来源](https://github.com/codedrinker/community)
## 技术栈

版本控制 git

界面 bootstrap、thymeleaf

持久层 H2/SQL、MyBatis、Flyway

安全 Spring Security

其他 lombok、MyBatis generate

## 脚本
[数据库创建](https://github.com/GGGuang12138/community/tree/master/src/main/resources/db/migration)

flyway
```
mvn flyway:migrate
mvn -Dmybatis.generator.overwrite=true mybatis-generator:generate
```


