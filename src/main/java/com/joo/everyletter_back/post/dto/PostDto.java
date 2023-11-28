package com.joo.everyletter_back.post.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class PostDto {
    private Long id;
    private LocalDateTime careatedDate;
    private String title;
    private int viewCnt;
    private String nickname;
}
