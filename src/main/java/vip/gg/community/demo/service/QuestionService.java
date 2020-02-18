package vip.gg.community.demo.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vip.gg.community.demo.dto.PaginationDTO;
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
    public PaginationDTO list(Integer page, Integer size) {
        PaginationDTO paginationDTO = new PaginationDTO();
        //往pagination里放东西
        Integer totalCount = questionMapper.count();
        paginationDTO.setPagination(totalCount, page, size);
        Integer totalPage =paginationDTO.getTotalPage();
        //判断page
        if(page<1){
            page = 1;
        }
        if(page>totalPage){
            page = totalPage;
        }
        //往pagination里放question
        //size*(page-1)
        Integer offset = size * (page-1);
        List<Question> questions = questionMapper.list(offset,size);
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        for(Question question :questions){
            User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        paginationDTO.setQuestions(questionDTOList);
        return paginationDTO;
    }

    public PaginationDTO list(Integer userId, Integer page, Integer size) {
        PaginationDTO paginationDTO = new PaginationDTO();

        //往pagination里放东西
        Integer totalCount = questionMapper.countByuserId(userId);
        paginationDTO.setPagination(totalCount, page, size);
        Integer totalPage =paginationDTO.getTotalPage();
        //判断page
        if(page<1){
            page = 1;
        }
        if(page>totalPage){
            page = totalPage;
        }

        //往pagination里放question
        //size*(page-1)
        Integer offset = size * (page-1);
        List<Question> questions = questionMapper.listbyuser(userId,offset,size);
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        for(Question question :questions){
            User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        paginationDTO.setQuestions(questionDTOList);
        return paginationDTO;

    }
}
