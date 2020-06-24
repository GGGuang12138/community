package vip.gg.community.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import vip.gg.community.demo.dto.CommentDTO;
import vip.gg.community.demo.dto.QuestionDTO;
import vip.gg.community.demo.enums.CommentTypeEnum;
import vip.gg.community.demo.exception.CustomizeException;
import vip.gg.community.demo.service.CommentService;
import vip.gg.community.demo.service.QuestionService;

import java.util.List;

import static vip.gg.community.demo.exception.CustomizeErrorCode.QUESTION_NOT_FOUND;

/**
 * Creat by GG
 * Date on 2020/2/19  4:28 下午
 */
@Controller
public class QuestionController {

    @Autowired(required = false)
    private QuestionService questionService;

    @Autowired(required = false)
    private CommentService commentService;

    @GetMapping("/question/{id}")
    public String question(@PathVariable(name = "id") Long id,
                           Model model
    ){
        QuestionDTO questionDTO = questionService.getById(id);
        if (questionDTO == null) {
            throw new CustomizeException(QUESTION_NOT_FOUND);
        }
        List<QuestionDTO> relatedQuestions = questionService.selectRelated(questionDTO);
        List<CommentDTO> commentDTOList = commentService.listByTargetId(id, CommentTypeEnum.QUESTION);
        questionService.incView(id);
        model.addAttribute("question",questionDTO);
        model.addAttribute("comments",commentDTOList);
        model.addAttribute("relatedQuestions",relatedQuestions);
        return "question";
    }
}
