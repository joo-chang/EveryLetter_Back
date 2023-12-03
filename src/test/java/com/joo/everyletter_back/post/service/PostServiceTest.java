package com.joo.everyletter_back.post.service;

import com.joo.everyletter_back.common.enumeration.Role;
import com.joo.everyletter_back.common.model.entity.Category;
import com.joo.everyletter_back.common.model.entity.Post;
import com.joo.everyletter_back.common.model.entity.Reply;
import com.joo.everyletter_back.common.model.entity.User;
import com.joo.everyletter_back.common.model.repository.CategoryRepository;
import com.joo.everyletter_back.common.model.repository.PostRepostiory;
import com.joo.everyletter_back.common.model.repository.UserRepository;
import com.joo.everyletter_back.post.dto.ReplyWriteReq;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PostServiceTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private PostRepostiory postRepostiory;
    @Autowired
    private PostService postService;

    @Test
    void replyWrite(){
        //given
        User user = userRepository.save(User.builder()
                .email("jooc0311@naver.com")
                .role(Role.ROLE_USER)
                .password("joochang")
                .nickname("주창")
                .build());
        ReplyWriteReq replyWriteReq = new ReplyWriteReq(user.getId(), "안녕하세요!");
        Category category = categoryRepository.save(Category.builder()
                .content("IT 카테고리")
                .name("IT")
                .build());
//        User user = userRepository.findById(2L).orElseThrow(() -> ServiceException.USER_NOT_FOUND);
//        Category category = categoryRepository.findById(1L).orElseThrow();
        Post post = new Post(null, user, category, null, "hihi", "# 안녕하세요", 0);
        postRepostiory.save(post);

        //when
        Reply reply = postService.replyWrite(post.getId(), replyWriteReq);

        //then
        Reply reply1 = new Reply(1L, "# 안녕하세요", post, user);
        Assertions.assertEquals(reply.getId(), reply1.getId());
    }
}