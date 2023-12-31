package com.joo.everyletter_back.post.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PostDto {
    private Long id;
    private String createdDate;
    private String title;
    private String content;
    private String profileUrl;
    private int viewCnt;
    private String nickname;
}
