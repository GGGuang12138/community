package vip.gg.community.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import vip.gg.community.demo.dto.CommentDTO;
import vip.gg.community.demo.dto.ResultDTO;
import vip.gg.community.demo.exception.CustomizeErrorCode;
import vip.gg.community.demo.model.Comment;
import vip.gg.community.demo.model.User;
import vip.gg.community.demo.service.CommentService;

import javax.servlet.http.HttpServletRequest;

/**
 * Creat by GG
 * Date on 2020/2/22  4:46 下午
 */
@Controller
public class CommentController {


    @Autowired(required = false)
    private CommentService commentService;

    @ResponseBody
    @RequestMapping(value = "/comment",method = RequestMethod.POST)
    public Object post(@RequestBody CommentDTO commentDTO,
                       HttpServletRequest request){

        User user = (User) request.getSession().getAttribute("user");
        if (user == null){
            return ResultDTO.errorOf(CustomizeErrorCode.NO_LOGIN);
        }
        Comment comment = new Comment();
        comment.setParentId(commentDTO.getParentId());
        comment.setContent(commentDTO.getContent());
        comment.setType(commentDTO.getType());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(System.currentTimeMillis());
        comment.setCommentator(user.getId());
        comment.setLikeCount(0L);
        commentService.insert(comment);

        return ResultDTO.okof();
    }

}
