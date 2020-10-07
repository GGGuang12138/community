package vip.gg.community.demo.service;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vip.gg.community.demo.dto.PaginationDTO;
import vip.gg.community.demo.dto.QuestionDTO;
import vip.gg.community.demo.dto.QuestionQueryDTO;
import vip.gg.community.demo.exception.CustomizeErrorCode;
import vip.gg.community.demo.exception.CustomizeException;
import vip.gg.community.demo.mapper.QuestionExMapper;
import vip.gg.community.demo.mapper.QuestionMapper;
import vip.gg.community.demo.mapper.UserAuthMapper;
import vip.gg.community.demo.mapper.UserInfoMapper;
import vip.gg.community.demo.model.Question;
import vip.gg.community.demo.model.QuestionExample;
import vip.gg.community.demo.model.UserInfo;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Creat by GG
 * Date on 2020/2/14  12:07 下午
 */
@Service
public class QuestionService {

    @Autowired(required = false)
    private QuestionExMapper questionExMapper;

    @Autowired(required = false)
    private QuestionMapper questionMapper;


    @Autowired(required = false)
    private UserInfoMapper userMapper;

    public PaginationDTO list(String search, Integer page, Integer size) {
        if (StringUtils.isNotBlank(search)){
            search = StringUtils.replace(search," ","|");
        }
        QuestionQueryDTO questionQueryDTO = new QuestionQueryDTO();
        questionQueryDTO.setSearch(search);

        PaginationDTO paginationDTO = new PaginationDTO();
        //往pagination里放东西
        Integer totalCount = questionExMapper.countBySearch(questionQueryDTO);
        paginationDTO.setPagination(totalCount, page, size);

        Integer totalPage = paginationDTO.getTotalPage();
        //判断page
        if(page<1){
            page = 1;
        }
        if(page>totalPage){
            page = totalPage;
        }
        //往pagination里放question
        //size*(page-1)
        Integer offset = page < 1 ? 0: size * (page-1);

        questionQueryDTO.setPage(offset);
        questionQueryDTO.setSize(size);
        List<Question> questions = questionExMapper.selectBySearch(questionQueryDTO);

        List<QuestionDTO> questionDTOList = new ArrayList<>();
        for(Question question :questions){
            UserInfo user = userMapper.selectByPrimaryKey(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUserInfo(user);
            questionDTOList.add(questionDTO);
        }
        paginationDTO.setQuestions(questionDTOList);
        return paginationDTO;
    }

    public PaginationDTO list(Long userId, Integer page, Integer size) {
        PaginationDTO paginationDTO = new PaginationDTO();

        //往pagination里放东西
        QuestionExample questionExample = new QuestionExample();
        questionExample.createCriteria().andCreatorEqualTo(userId);
        Integer totalCount = (int)questionMapper.countByExample(questionExample);
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
        QuestionExample example = new QuestionExample();
        example.createCriteria().andCreatorEqualTo(userId);
        List<Question> questions = questionMapper.selectByExampleWithRowbounds(example,new RowBounds(offset,size));
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        for(Question question :questions){
            UserInfo user = userMapper.selectByPrimaryKey(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUserInfo(user);
            questionDTOList.add(questionDTO);
        }
        paginationDTO.setQuestions(questionDTOList);
        return paginationDTO;

    }

    public QuestionDTO getById(Long id) {
        Question question  = questionMapper.selectByPrimaryKey(id);
        if (question == null){
            throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
        }
        QuestionDTO questionDTO = new QuestionDTO();
        BeanUtils.copyProperties(question,questionDTO);
        UserInfo user = userMapper.selectByPrimaryKey(question.getCreator());
        questionDTO.setUserInfo(user);
        return questionDTO;
    }

    public void createOrUpdate(Question question) {
        if(question.getId() == null){
            //创建
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
            question.setViewCount(0);
            question.setLikeCount(0);
            question.setCommentCount(0);
            questionMapper.insert(question);
        }else{
            //更新
            question.setGmtModified(System.currentTimeMillis());
            QuestionExample example = new QuestionExample();
            example.createCriteria().andIdEqualTo(question.getId());
            int updated = questionMapper.updateByExampleSelective(question, example);
            if(updated != 1){
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
        }
    }

    public void incView(Long id) {
       Question question = new Question();
       question.setId(id);
       question.setViewCount(1);
       questionExMapper.incView(question);
    }


    public List<QuestionDTO> selectRelated(QuestionDTO queryDTO) {
        if(StringUtils.isBlank(queryDTO.getTag())){
            return new ArrayList<>();
        }
        String regexpTag = StringUtils.replace(queryDTO.getTag(),"/","|");
        Question question = new Question();
        question.setId(queryDTO.getId());
        question.setTag(regexpTag);
        List<Question> questions = questionExMapper.selectRelated(question);
        List<QuestionDTO> questionDTOS = questions.stream().map(q -> {
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(q,questionDTO);
            return questionDTO;
        }).collect(Collectors.toList());
        return questionDTOS;
    }
}
