package com.joo.everyletter_back.post.service;

import com.joo.everyletter_back.common.exception.ServiceException;
import com.joo.everyletter_back.common.model.entity.Category;
import com.joo.everyletter_back.common.model.entity.Post;
import com.joo.everyletter_back.common.model.entity.User;
import com.joo.everyletter_back.common.model.repository.CategoryRepository;
import com.joo.everyletter_back.common.model.repository.PostRepostiory;
import com.joo.everyletter_back.common.model.repository.UserRepository;
import com.joo.everyletter_back.post.dto.PostWriteReq;
import com.joo.everyletter_back.post.dto.PostWriteResp;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepostiory postRepostiory;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    public PostWriteResp postWrite(PostWriteReq postWriteReq) {

        Category category = categoryRepository.findById(postWriteReq.getCategoryId()).orElseThrow(() -> ServiceException.POST_NOT_FOUND);
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
}
