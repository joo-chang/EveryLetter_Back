package com.joo.everyletter_back.common.model.repository;

import com.joo.everyletter_back.common.model.entity.Post;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepostiory extends JpaRepository<Post, Long> {

    Slice<Post> findByCategoryId(Long categoryId, Pageable pageable);

}
