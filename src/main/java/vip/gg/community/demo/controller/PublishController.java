package vip.gg.community.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vip.gg.community.demo.mapper.QuestionMapper;
import vip.gg.community.demo.mapper.UserMapper;
import vip.gg.community.demo.model.Question;
import vip.gg.community.demo.model.User;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * Creat by GG
 * Date on 2020/2/7  9:13 下午
 */
@Controller
public class PublishController {
    @Autowired(required = false)
    private QuestionMapper questionMapper;
    @Autowired(required = false)
    private UserMapper userMapper;


    @GetMapping("/publish")
    public String publish(){
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("tag") String tag,
            HttpServletRequest request,
            Model model){
        model.addAttribute("title",title);
        model.addAttribute("description",description);
        model.addAttribute("tag",tag);
        if(title==null || title ==""){
            model.addAttribute("error","标题不能为空");
            return "publish";
        }
        if(description==null || description ==""){
            model.addAttribute("error","问题补充不能为空");
            return "publish";
        }
        if(tag==null || tag ==""){
            model.addAttribute("error","标签不能为空");
            return "publish";
        }
        User user = null;
        Cookie[] cookies = request.getCookies();
        if(cookies!=null && cookies.length!=0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();//token保存cookies中找到token的值
                    user = userMapper.findByToken(token);//数据库找到同token的记录，保存为user
                    if (user != null) {
                        request.getSession().setAttribute("user", user);
                    }
                    break;
                }
            }
        }
        if (user == null) {
            model.addAttribute("error", "用户未登陆");
        }
        Question question = new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        question.setCreator(user.getId());
        question.setGmtCreate(System.currentTimeMillis());
        question.setGmtModified(question.getGmtCreate());
        questionMapper.create(question);
        return "redirect:/";

    }
}
