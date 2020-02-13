package vip.gg.community.demo.model;

import lombok.Data;

/**
 * Creat by GG
 * Date on 2020/2/6  11:50 上午
 */
@Data
public class User {
    private Integer id;
    private String name;
    private String accountId;
    private String token;
    private Long gmtCreate;
    private Long gmtModified;
    private String avatarUrl;

}
