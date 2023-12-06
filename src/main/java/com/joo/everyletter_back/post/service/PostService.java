package com.joo.everyletter_back.post.service;

import com.joo.everyletter_back.common.exception.ServiceException;
import com.joo.everyletter_back.common.model.entity.Category;
import com.joo.everyletter_back.common.model.entity.Post;
import com.joo.everyletter_back.common.model.entity.Reply;
import com.joo.everyletter_back.common.model.entity.User;
import com.joo.everyletter_back.common.model.repository.CategoryRepository;
import com.joo.everyletter_back.common.model.repository.PostRepostiory;
import com.joo.everyletter_back.common.model.repository.ReplyRepository;
import com.joo.everyletter_back.common.model.repository.UserRepository;
import com.joo.everyletter_back.post.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepostiory postRepostiory;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final ReplyRepository replyRepository;

    public PostWriteResp postWrite(PostWriteReq postWriteReq) {

        Category category = categoryRepository.findById(postWriteReq.getCategoryId()).orElseThrow(() -> ServiceException.CATEGORY_NOT_FOUND);
        User user = userRepository.findById(postWriteReq.getUserId()).orElseThrow(() -> ServiceException.USER_NOT_FOUND);

        Post post = Post.builder()
                .category(category)
                .user(user)
                .title(postWriteReq.getTitle())
                .content(postWriteReq.getContent())
                .build();

        postRepostiory.save(post);

        return PostWriteResp.builder()
                .postId(post.getId())
                .build();
    }

    public PostListResp postList(Long categoryId, Pageable pageable) {
        Slice<Post> posts;
        if(categoryId == 0){
            posts = postRepostiory.findAll(pageable);
        }else {
            posts = postRepostiory.findByCategoryId(categoryId, pageable);
        }
        List<PostListDto> postListDtos = posts.getContent().stream()
                .map(post -> PostListDto.builder()
                        .id(post.getId())
                        .createdDate(post.getCreatedDate())
                        .title(post.getTitle())
                        .viewCnt(post.getViewCnt())
                        .profileUrl(post.getUser().getProfileUrl())
                        .nickname(post.getUser().getNickname())
                        .build()
                ).toList();
        return new PostListResp(new SliceImpl<>(postListDtos, pageable, posts.hasNext()));
    }

    public CategoryListResp cateList() {
        return new CategoryListResp(categoryRepository.findAll());
    }

    public Category categoryInfo(Long categoryId){
        if(categoryId == 0){
            return Category.builder()
                    .name("커뮤니티")
                    .content("다양한 사람들과 정보를 공유하고, 뉴스레터를 신청해보세요.")
                    .build();
        }
        return categoryRepository.findById(categoryId).orElseThrow(() ->ServiceException.CATEGORY_NOT_FOUND);
    }

    @Transactional
    public PostDetailResp postDetail(Long postId) {
        Post post = postRepostiory.findById(postId).orElseThrow(() -> ServiceException.POST_NOT_FOUND);
        PostDto postDto = PostDto.builder()
                .id(post.getId())
                .nickname(post.getUser().getNickname())
                .profileUrl(post.getUser().getProfileUrl())
                .title(post.getTitle())
                .content(post.getContent())
                .viewCnt(post.getViewCnt())
                .createdDate(post.getCreatedDate())
                .build();

        post.setViewCnt(post.getViewCnt() + 1);
        List<Reply> replyList = replyRepository.findByPost(post);
        List<ReplyDto> replyDtos = replyList.stream()
                .map(reply -> ReplyDto.builder()
                        .replyId(reply.getId())
                        .content(reply.getContent())
                        .nickname(reply.getUser().getNickname())
                        .createdDate(reply.getCreatedDate())
                        .build()
                ).toList();
        return new PostDetailResp(postDto, replyDtos);
    }

    public void replyWrite(Long postId, ReplyWriteReq replyWriteReq) {
        Post post = postRepostiory.findById(postId).orElseThrow(() -> ServiceException.POST_NOT_FOUND);
        User user = userRepository.findById(replyWriteReq.getUserId()).orElseThrow(() -> ServiceException.USER_NOT_FOUND);

        Reply reply = Reply.builder()
                .post(post)
                .user(user)
                .content(replyWriteReq.getContent())
                .build();
        replyRepository.save(reply);
    }
}
