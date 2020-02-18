package vip.gg.community.demo.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import vip.gg.community.demo.model.Question;

import java.util.List;

/**
 * Creat by GG
 * Date on 2020/2/8  8:51 下午
 */
@Mapper
public interface QuestionMapper {
    @Insert("insert into question (title,description,gmt_create,gmt_modified,creator,tag) values (#{title},#{description},#{gmtCreate},#{gmtModified},#{creator},#{tag})")
    public void create(Question question);

    @Select("select * from question limit #{offset}, #{size}")
    List<Question> list(Integer offset, Integer size);

    @Select("select count(1) from question")
    Integer count();

    @Select("select * from question where creator = #{userId} limit #{offset},#{size}")
    List<Question> listbyuser(Integer userId, Integer offset, Integer size);

    @Select("select count(1) from question where creator = #{userId}")
    Integer countByuserId(Integer userId);
}
