package com.joo.everyletter_back.post.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostWriteReq {
    @ApiModelProperty(value = "제목", example = "제목01")
    private String title;
    @ApiModelProperty(value = "내용", example = "내용01")
    private String content;
    @ApiModelProperty(value = "카테고리 ID", example = "1")
    private Long categoryId;
    @ApiModelProperty(value = "사용자 ID", example = "1")
    private Long userId;
}
