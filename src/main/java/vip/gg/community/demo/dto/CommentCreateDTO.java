package vip.gg.community.demo.dto;

import lombok.Data;

/**
 * Creat by GG
 * Date on 2020/2/22  4:55 下午
 */
@Data
public class CommentCreateDTO {
    private Long parentId;
    private String content;
    private Integer type;
}
