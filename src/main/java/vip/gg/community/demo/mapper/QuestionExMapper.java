package vip.gg.community.demo.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import vip.gg.community.demo.model.Question;
import vip.gg.community.demo.model.QuestionExample;

import java.util.List;

public interface QuestionExMapper {
    int incView(Question record);
    int incCommentCount(Question record);
}