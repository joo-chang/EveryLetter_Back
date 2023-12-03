package com.joo.everyletter_back.post.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PostListDto {
    private Long id;
    private String createdDate;
    private String title;
    private String profileUrl;
    private int viewCnt;
    private String nickname;
}
