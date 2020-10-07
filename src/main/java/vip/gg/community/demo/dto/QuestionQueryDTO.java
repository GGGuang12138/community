package vip.gg.community.demo.dto;

import lombok.Data;

/**
 * Creat by GG
 * Date on 2020/9/2  5:37 下午
 */
@Data
public class QuestionQueryDTO {
    private String search;
    private Integer size;
    private Integer page;
}
