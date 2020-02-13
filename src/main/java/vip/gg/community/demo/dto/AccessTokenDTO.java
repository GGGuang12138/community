package vip.gg.community.demo.dto;

import lombok.Data;

/**
 * Creat by GG
 * Date on 2020/2/4  3:46 下午
 */
@Data
public class AccessTokenDTO {
    private String client_id;
    private String client_secret;
    private String code;
    private String redirect_uri;
    private String state;
}
