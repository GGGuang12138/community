package vip.gg.community.demo.dto;

import lombok.Data;
import vip.gg.community.demo.model.User;

/**
 * Creat by GG
 * Date on 2020/2/14  12:03 下午
 */
@Data
public class QuestionDTO {
    private Integer id;
    private String title;
    private String description;
    private String tag;
    private Long gmtCreate;
    private Long gmtModified;
    private Integer creator;
    private Integer viewCount;
    private Integer commentCount;
    private User user;
}
