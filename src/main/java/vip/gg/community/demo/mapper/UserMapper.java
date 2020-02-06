package vip.gg.community.demo.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import vip.gg.community.demo.model.User;

/**
 * Creat by GG
 * Date on 2020/2/6  11:38 上午
 */
@Mapper
public interface UserMapper {
    @Insert("insert into user (name,account_id,token,gmt_create,gmt_modified) values (#{name},#{accountId},#{token},#{gmtCreate},#{gmtModified})")
    public void insert(User user);

}
