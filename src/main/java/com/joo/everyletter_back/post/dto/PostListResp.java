package com.joo.everyletter_back.post.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Slice;

@Getter
@Builder
@AllArgsConstructor
public class PostListResp {
    private Slice<PostDto> postList;
}
