package com.joo.everyletter_back.post.controller;

import com.joo.everyletter_back.common.model.entity.Category;
import com.joo.everyletter_back.common.model.entity.Reply;
import com.joo.everyletter_back.common.response.ApiSuccResp;
import com.joo.everyletter_back.post.dto.*;
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
    @GetMapping("/list/{categoryId}")
    public ApiSuccResp<PostListResp> categoryList(@PathVariable Long categoryId, Pageable pageable) {
        return ApiSuccResp.from(postService.postList(categoryId, pageable));
    }

    @Operation(summary = "카테고리 정보 조회", description = "게시판 카테고리 소개를 보여주는 API 입니다.")
    @GetMapping("/category/{categoryId}")
    public ApiSuccResp<Category> categoryInfo(@PathVariable Long categoryId){
        return ApiSuccResp.from(postService.categoryInfo(categoryId));
    }

    @Operation(summary = "게시글 상세 정보 조회", description = "게시판에서 게시글 클릭 시 이동하는 페이지 입니다.")
    @GetMapping("/detail/{postId}")
    public ApiSuccResp<PostDto> postDetail(@PathVariable Long postId) {
        return ApiSuccResp.from(postService.postDetail(postId));
    }

    @Operation(summary = "게시글 댓글 작성")
    @PostMapping("/reply/write/{postId}")
    public ApiSuccResp<Reply> replyWrite(@PathVariable Long postId, ReplyWriteReq replyWriteReq) {
        return ApiSuccResp.from(postService.replyWrite(postId, replyWriteReq));
    }
}
