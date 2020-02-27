package vip.gg.community.demo.dto;

import lombok.Data;
import vip.gg.community.demo.model.User;

/**
 * Creat by GG
 * Date on 2020/2/14  12:03 下午
 */
@Data
public class QuestionDTO {
    private Long id;
    private String title;
    private String description;
    private String tag;
    private Long gmtCreate;
    private Long gmtModified;
    private Long creator;
    private Integer viewCount;
    private Integer commentCount;
    private User user;
}
