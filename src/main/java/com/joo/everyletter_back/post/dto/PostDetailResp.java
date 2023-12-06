package com.joo.everyletter_back.post.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class PostDetailResp {
    PostDto postDto;
    List<ReplyDto> replyList;
}
