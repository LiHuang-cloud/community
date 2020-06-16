package com.huang.dto;


import com.huang.model.user;
import lombok.Data;

@Data
public class QuestionDTO {
    private Integer id;
    private String title;
    private String description;
    private String tag;
    private Long gmtCreate;
    private Long gmtModified;
    private Integer  creator;
    private Integer viewCount;
    private Integer commentCount;
    private Integer likeCount;
    private user user;
}
