package com.joo.everyletter_back.common.model.repository;

import com.joo.everyletter_back.common.model.entity.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
//    private List<ChatMessage> findAllByChatRoo
}
