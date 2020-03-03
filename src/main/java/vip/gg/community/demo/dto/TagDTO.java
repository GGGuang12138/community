package vip.gg.community.demo.dto;

import lombok.Data;
import java.util.List;

/**
 * Creat by GG
 * Date on 2020/3/2  10:55 下午
 */
@Data
public class TagDTO {
    private String categoryName;
    private List<String> tags;
}
