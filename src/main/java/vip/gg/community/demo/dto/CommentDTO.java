package vip.gg.community.demo.dto;

import lombok.Data;
import vip.gg.community.demo.model.UserInfo;

/**
 * Creat by GG
 * Date on 2020/2/27  12:05 下午
 */
@Data
public class CommentDTO {
    private Long id;
    private Long parentId;
    private Integer type;
    private Long commentator;
    private Long gmtCreate;
    private Long gmtModified;
    private Long likeCount;
    private Integer commentCount;
    private String content;
    private UserInfo userInfo;
}
