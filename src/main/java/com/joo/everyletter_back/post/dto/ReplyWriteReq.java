package com.joo.everyletter_back.post.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReplyWriteReq {
    private Long userId;
    private String content;
}
