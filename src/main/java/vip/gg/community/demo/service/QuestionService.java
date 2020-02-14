package vip.gg.community.demo.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vip.gg.community.demo.dto.QuestionDTO;
import vip.gg.community.demo.mapper.QuestionMapper;
import vip.gg.community.demo.mapper.UserMapper;
import vip.gg.community.demo.model.Question;
import vip.gg.community.demo.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Creat by GG
 * Date on 2020/2/14  12:07 下午
 */
@Service
public class QuestionService {

    @Autowired(required = false)
    private QuestionMapper questionMapper;

    @Autowired(required = false)
    private UserMapper userMapper;
    public List<QuestionDTO> list() {
        List<Question> questions = questionMapper.list();
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        for(Question question :questions){
            User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        return questionDTOList;

    }
}
