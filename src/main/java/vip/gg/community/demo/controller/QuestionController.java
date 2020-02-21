package vip.gg.community.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import vip.gg.community.demo.dto.QuestionDTO;
import vip.gg.community.demo.service.QuestionService;

/**
 * Creat by GG
 * Date on 2020/2/19  4:28 下午
 */
@Controller
public class QuestionController {

    @Autowired(required = false)
    private QuestionService questionSerive;

    @GetMapping("/question/{id}")
    public String question(@PathVariable(name = "id") Integer id,
                           Model model
    ){
        QuestionDTO questionDTO = questionSerive.getById(id);
        model.addAttribute("question",questionDTO);
        return "question";
    }
}
