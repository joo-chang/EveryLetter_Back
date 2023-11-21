package com.joo.everyletter_back.post.controller;

import com.joo.everyletter_back.common.response.ApiSuccResp;
import com.joo.everyletter_back.post.dto.PostWriteReq;
import com.joo.everyletter_back.post.dto.PostWriteResp;
import com.joo.everyletter_back.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(name = "/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    /**
     * 게시글 작성
     * @param postWriteReq
     * @return
     */
    @PostMapping("/write")
    public ApiSuccResp<PostWriteResp> postWrite(@RequestBody PostWriteReq postWriteReq) {

        PostWriteResp postWriteResp = postService.postWrite(postWriteReq);

        return ApiSuccResp.from(postWriteResp);
    }
}
