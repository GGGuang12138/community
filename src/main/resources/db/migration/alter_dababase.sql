ALTER SCHEMA `community`  DEFAULT CHARACTER SET utf8mb4 ; //修改数据库支持中文编码

//注意具体的列也要切换为uff8
ALTER TABLE `community`.`question`
CHANGE COLUMN `title` `title` VARCHAR(50) CHARACTER SET 'utf8mb4' NULL DEFAULT NULL ,
CHANGE COLUMN `description` `description` TEXT CHARACTER SET 'utf8mb4' NULL DEFAULT NULL ,
CHANGE COLUMN `tag` `tag` VARCHAR(256) CHARACTER SET 'utf8mb4' NULL DEFAULT NULL ;