package com.joo.everyletter_back.chat.service;

import com.joo.everyletter_back.common.model.repository.ChatMessageRepository;
import com.joo.everyletter_back.common.model.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final ChatRoomRepository chatRoomRepository;
    private final ChatMessageRepository chatMessageRepository;
//    public List<ChatMessageDetailDTO> (String roomId) {
//        chatRoomRepository.findAllRooms();
//    }
}
