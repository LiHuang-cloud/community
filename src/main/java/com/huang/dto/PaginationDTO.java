package com.huang.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PaginationDTO {
    private List<QuestionDTO> questions;
    private boolean showPrevious;
    private boolean showFirstPage;
    private boolean showNext;
    private boolean showEndPage;
    private Integer page;
    private List<Integer> pages = new ArrayList<>();
    private Integer count;

    public void setPagination(Integer totalCount, Integer page, Integer size) {
        //求总页数
        if (totalCount % size == 0) {
            count = totalCount / size;
        } else {
            count = totalCount / size + 1;
        }

        //页面到前端可以自己乱传递所以判断一下
        if(page<1){
            page=1;
        }else if(page>count){
            page=count;
        }

        this.page=page;
        //是否展示上一页
        if (page == 1) {
            showPrevious = false;
        } else {
            showPrevious = true;
        }
        //是否展示下一页
        if (page == count) {
            showNext = false;
        } else {
            showNext = true;
        }
        pages.add(page);
        for (int i = 1; i <= 3; i++) {
            if (page - i > 0) {
                pages.add(0,page - i);
            }

            if (page + i <= count) {
                pages.add(page+i);
            }
            System.out.println(pages);
        }
        System.out.println(pages);


        //是否展示第一页
        if (pages.contains(1)) {
            showFirstPage = false;
        } else {
            showFirstPage = true;
        }

        //是否展示最后一页
        if (pages.contains(count)) {
            showEndPage = false;
        } else {
            showEndPage = true;
        }
    }
}
