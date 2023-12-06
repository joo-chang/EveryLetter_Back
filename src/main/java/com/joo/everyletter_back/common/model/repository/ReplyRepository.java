package com.joo.everyletter_back.common.model.repository;

import com.joo.everyletter_back.common.model.entity.Post;
import com.joo.everyletter_back.common.model.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReplyRepository extends JpaRepository<Reply, Long> {

    List<Reply> findByPost(Post post);

}
