package com.joo.everyletter_back.post.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReplyDto {
    private Long replyId;
    private String content;
    private String nickname;
    private String profileUrl;
    private String createdDate;
}
