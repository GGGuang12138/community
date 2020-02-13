package vip.gg.community.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import vip.gg.community.demo.mapper.UserMapper;
import vip.gg.community.demo.model.User;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 *
 */
@Controller
public class HelloController {

    @Autowired(required = false)
    private UserMapper userMapper;//默认要求依赖对象必须存在

    @GetMapping("/")
    public String hello(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        if(cookies!=null && cookies.length!=0){
            for(Cookie cookie:cookies){
                if(cookie.getName().equals("token")){
                    String token = cookie.getValue();//token保存cookies中找到token的值
                    User user = userMapper.findByToken(token);//数据库找到同token的记录，保存为user
                    if(user!=null){
                        request.getSession().setAttribute("user",user);
                    }
                    break;
                }
            }
        }
        return "index";
    }
}
