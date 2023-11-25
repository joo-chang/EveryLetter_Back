package com.joo.everyletter_back.post.controller;

import com.joo.everyletter_back.common.response.ApiSuccResp;
import com.joo.everyletter_back.post.dto.CategoryListResp;
import com.joo.everyletter_back.post.dto.PostWriteReq;
import com.joo.everyletter_back.post.dto.PostWriteResp;
import com.joo.everyletter_back.post.service.PostService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Api(tags = "커뮤니티")
@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @Operation(summary = "게시글 작성", description = "게시글 작성 API 입니다. \n Category, User ID가 필요합니다.")
    @PostMapping("/write")
    public ApiSuccResp<PostWriteResp> postWrite(@RequestBody PostWriteReq postWriteReq) {

        PostWriteResp postWriteResp = postService.postWrite(postWriteReq);

        return ApiSuccResp.from(postWriteResp);
    }

    @GetMapping("/category/list")
    public ApiSuccResp<CategoryListResp> categoryList() {
        return ApiSuccResp.from(postService.cateList());
    }

    @PostMapping("/image")
    public ApiSuccResp<?> imageUpload(@RequestParam("file") MultipartFile multi) {

//        postService.imageUpload();
        try {
            String originFilename = multi.getOriginalFilename();
            String extName = originFilename.substring(originFilename.lastIndexOf("."), originFilename.length());
            long size = multi.getSize();

        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("[Error] " + e.getMessage());
        }
        return ApiSuccResp.NO_DATA_RESPONSE;
    }
}
