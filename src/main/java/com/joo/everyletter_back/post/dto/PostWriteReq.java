package com.joo.everyletter_back.post.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostWriteReq {
    private String title;
    private String content;
    private Long categoryId;
    private Long userId;
}
