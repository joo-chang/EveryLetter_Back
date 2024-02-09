package com.joo.everyletter_back.chat.dto;

import com.joo.everyletter_back.common.model.entity.ChatMessage;
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

    public static ChatMessageDetailDTO toChatMessageDetailDTO(ChatMessage chatMessage){
        ChatMessageDetailDTO chatMessageDetailDTO = new ChatMessageDetailDTO();

        chatMessageDetailDTO.setChatId(chatMessage.getId());

        chatMessageDetailDTO.setChatRoomId(chatMessage.getChatRoom().getId());
        chatMessageDetailDTO.setRoomId(chatMessage.getChatRoom().getRoomId());

        chatMessageDetailDTO.setWriter(chatMessage.getWriter());
        chatMessageDetailDTO.setMessage(chatMessage.getMessage());

        return chatMessageDetailDTO;

    }

}