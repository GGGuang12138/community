package vip.gg.community.demo.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Creat by GG
 * Date on 2020/2/16  3:37 下午
 */
@Data
public class PaginationDTO {
    public List<QuestionDTO> questions;
    private boolean showPrevious;
    private boolean showFirstPage;
    private boolean showNext;
    private boolean showEndPage;
    private Integer totalPage;

    private Integer page;
    private List<Integer> pages = new ArrayList<>();

    public void setPagination(Integer totalCount,Integer page, Integer size){
        //pages列表
        if(totalCount%size == 0){
            totalPage = totalCount/size;
        }else{
            totalPage = totalCount/size + 1;
        }
        //判断page
        if(page<1){
            page = 1;
        }
        if(page>totalPage){
            page = totalPage;
        }
        this.page = page;
        //根据page往pages列表里放数据
        for(int i = -3;i<=3;i++){
            if(page+i > 0 && page+i<= totalPage){
                pages.add(page+i);
            }
        }

        //是否展示前标示
        if (page == 1){
            showPrevious = false;
        }else {
            showPrevious = true;
        }
        //是否展示后标示
        if (page == totalPage){
            showNext = false;
        }else {
            showNext = true;
        }
        //是否展示到第一页
        if(pages.contains(1)){
            showFirstPage = false;
        }else {
            showFirstPage = true;
        }
        //是否展示到最后一页
        if(pages.contains(totalPage)){
            showEndPage = false;
        }else {
            showEndPage = true;
        }




    }
}
