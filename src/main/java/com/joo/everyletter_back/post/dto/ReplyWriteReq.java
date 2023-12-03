package com.joo.everyletter_back.post.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ReplyWriteReq {
    private Long userId;
    private String content;
}
