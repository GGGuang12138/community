package vip.gg.community.demo.model;

import lombok.Data;

/**
 * Creat by GG
 * Date on 2020/2/8  8:57 下午
 */
@Data
public class Question {
    private Integer id;
    private String title;
    private String description;
    private String tag;
    private Long gmtCreate;
    private Long gmtModified;
    private Integer creator;
    private Integer viewCount;
    private Integer commentCount;


}
