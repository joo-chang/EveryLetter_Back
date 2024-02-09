package com.joo.everyletter_back.common.model.entity;

import com.joo.everyletter_back.chat.dto.ChatMessageSaveDTO;
import com.joo.everyletter_back.chat.dto.ChatRoomDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "CHAT_MESSAGE")
public class ChatMessage extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "chatRoomId")
    private ChatRoom chatRoom;

    private String writer;

    @Column
    private String message;

    public static ChatMessage toChatEntity(ChatMessageSaveDTO chatMessageSaveDTO, ChatRoomDTO chatRoomDTO){
        ChatMessage chatMessage = new ChatMessage();

//        chatMessage.setChatRoom(chatRoomEntity);

        chatMessage.setWriter(chatMessageSaveDTO.getWriter());
        chatMessage.setMessage(chatMessageSaveDTO.getMessage());

        return chatMessage;

    }
}
