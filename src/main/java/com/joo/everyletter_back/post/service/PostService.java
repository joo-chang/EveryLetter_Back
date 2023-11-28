package com.joo.everyletter_back.post.service;

import com.joo.everyletter_back.common.exception.ServiceException;
import com.joo.everyletter_back.common.model.entity.Category;
import com.joo.everyletter_back.common.model.entity.Post;
import com.joo.everyletter_back.common.model.entity.User;
import com.joo.everyletter_back.common.model.repository.CategoryRepository;
import com.joo.everyletter_back.common.model.repository.PostRepostiory;
import com.joo.everyletter_back.common.model.repository.UserRepository;
import com.joo.everyletter_back.post.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepostiory postRepostiory;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

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
        List<PostDto> postDtos = posts.getContent().stream()
                .map(post -> PostDto.builder()
                        .id(post.getId())
                        .careatedDate(post.getCreatedDate())
                        .title(post.getTitle())
                        .viewCnt(post.getViewCnt())
                        .nickname(post.getUser().getNickname())
                        .build()
                ).toList();
        return new PostListResp(new SliceImpl<>(postDtos, pageable, posts.hasNext()));
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
}
