package vip.gg.community.demo.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import vip.gg.community.demo.dto.CommentCreateDTO;
import vip.gg.community.demo.dto.CommentDTO;
import vip.gg.community.demo.dto.ResultDTO;
import vip.gg.community.demo.enums.CommentTypeEnum;
import vip.gg.community.demo.exception.CustomizeErrorCode;
import vip.gg.community.demo.model.Comment;
import vip.gg.community.demo.model.User;
import vip.gg.community.demo.service.CommentService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
    public Object post(@RequestBody CommentCreateDTO commentCreateDTO,
                       HttpServletRequest request){

        User user = (User) request.getSession().getAttribute("user");
        if (user == null){
            return ResultDTO.errorOf(CustomizeErrorCode.NO_LOGIN);
        }
        if(commentCreateDTO == null || StringUtils.isBlank(commentCreateDTO.getContent())){
            return ResultDTO.errorOf(CustomizeErrorCode.COMMENT_IS_EMPTY);
        }

        Comment comment = new Comment();
        comment.setParentId(commentCreateDTO.getParentId());
        comment.setContent(commentCreateDTO.getContent());
        comment.setType(commentCreateDTO.getType());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(System.currentTimeMillis());
        comment.setCommentator(user.getId());
        comment.setLikeCount(0L);
        commentService.insert(comment);

        return ResultDTO.okof();
    }
    @ResponseBody
    @RequestMapping(value = "/comment/{id}", method = RequestMethod.GET)
    public  ResultDTO<List<CommentDTO>> comments(@PathVariable(name = "id") Long id){
        List<CommentDTO> commentDTOS = commentService.listByTargetId(id, CommentTypeEnum.COMMENT);
        return  ResultDTO.okof(commentDTOS);
    }

}
