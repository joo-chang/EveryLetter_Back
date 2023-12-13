package com.joo.everyletter_back.chat.dto;

import com.joo.everyletter_back.common.model.entity.ChatMessageEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessageDetailDTO {

    private Long chatId;
    private Long chatRoomId;

    private String roomId;
    private String writer;
    private String message;

    public static ChatMessageDetailDTO toChatMessageDetailDTO(ChatMessageEntity chatMessageEntity){
        ChatMessageDetailDTO chatMessageDetailDTO = new ChatMessageDetailDTO();

        chatMessageDetailDTO.setChatId(chatMessageEntity.getId());

        chatMessageDetailDTO.setChatRoomId(chatMessageEntity.getChatRoomEntity().getId());
        chatMessageDetailDTO.setRoomId(chatMessageEntity.getChatRoomEntity().getRoomId());

        chatMessageDetailDTO.setWriter(chatMessageEntity.getWriter());
        chatMessageDetailDTO.setMessage(chatMessageEntity.getMessage());

        return chatMessageDetailDTO;

    }

}