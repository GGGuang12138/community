package vip.gg.community.demo.advice;

import com.alibaba.fastjson.JSON;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import vip.gg.community.demo.dto.ResultDTO;
import vip.gg.community.demo.exception.CustomizeErrorCode;
import vip.gg.community.demo.exception.CustomizeException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 通过@ControllerAdvice注解实现错误处理
 * Creat by GG
 * Date on 2020/2/21  3:24 下午
 */
@ControllerAdvice
public class CustomizeExceptionHandler {

    @ExceptionHandler(Exception.class)
    ModelAndView handle(Throwable e, Model model, HttpServletRequest request, HttpServletResponse response) {
        String contentType = request.getContentType();
        if("application/json".equals(contentType)){
            //返回JSON
            ResultDTO resultDTO;
            if(e instanceof CustomizeException){
                resultDTO = ResultDTO.errorOf((CustomizeException)e);
            }else{
                resultDTO = ResultDTO.errorOf(CustomizeErrorCode.SYS_ERROR);
            }
            try {
                //流输出
                response.setContentType("application/json");
                response.setStatus(200);
                response.setCharacterEncoding("utf-8");
                PrintWriter writer = response.getWriter();
                writer.write(JSON.toJSONString(resultDTO));
                writer.close();

            }catch (IOException ioe){

            }
            return null;
        }else {
            //错误页面跳转
            if(e instanceof CustomizeException){ //抛出自己定义的错误
                model.addAttribute("message",e.getMessage());
            }else { //默认的错误
                model.addAttribute("message","过会儿再试呗～");
            }
            return new ModelAndView("error"); //跳转到error页面
        }

    }
}
