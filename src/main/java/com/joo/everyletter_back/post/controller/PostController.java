package com.joo.everyletter_back.post.controller;

import com.joo.everyletter_back.common.model.entity.Category;
import com.joo.everyletter_back.common.response.ApiSuccResp;
import com.joo.everyletter_back.post.dto.CategoryListResp;
import com.joo.everyletter_back.post.dto.PostListResp;
import com.joo.everyletter_back.post.dto.PostWriteReq;
import com.joo.everyletter_back.post.dto.PostWriteResp;
import com.joo.everyletter_back.post.service.PostService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

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

    @Operation(summary = "카테고리 리스트", description = "게시글 작성 시 카테고리 선택을 위해 호출 되는 API 입니다.")
    @GetMapping("/category/list")
    public ApiSuccResp<CategoryListResp> categoryList() {
        return ApiSuccResp.from(postService.cateList());
    }

    @Operation(summary = "게시판 글 리스트 조회", description = "게시판 글 조회 API 입니다. (카테고리가 0일 시 전체 조회)")
    @GetMapping("/list/{id}")
    public ApiSuccResp<PostListResp> categoryList(@PathVariable Long id, Pageable pageable) {
        return ApiSuccResp.from(postService.postList(id, pageable));
    }

    @Operation(summary = "카테고리 정보 조회", description = "게시판 카테고리 소개를 보여주는 API 입니다.")
    @GetMapping("category/{id}")
    public ApiSuccResp<Category> categoryInfo(@PathVariable Long id){
        return ApiSuccResp.from(postService.categoryInfo(id));
    }
}
