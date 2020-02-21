package vip.gg.community.demo.advice;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import vip.gg.community.demo.exception.CustomizeException;

import javax.servlet.http.HttpServletRequest;

/**
 * Creat by GG
 * Date on 2020/2/21  3:24 下午
 */
@ControllerAdvice
public class CustomizeExceptionHandler {

    @ExceptionHandler(Exception.class)
    ModelAndView handle(HttpServletRequest request, Throwable e, Model model) {
        if(e instanceof CustomizeException){
            model.addAttribute("message",e.getMessage());
        }else {
            model.addAttribute("message","过会儿再试呗～");
        }
        return new ModelAndView("error");
    }
}
