package com.joo.everyletter_back.common.model.entity;

import com.joo.everyletter_back.chat.dto.ChatMessageSaveDTO;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "chat_table")
public class ChatMessageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chat_id")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "chatRoom_id")
    private ChatRoomEntity chatRoomEntity;

    private String writer;

    @Column
    private String message;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime sendDate;

    public static ChatMessageEntity toChatEntity(ChatMessageSaveDTO chatMessageSaveDTO, ChatRoomEntity chatRoomEntity){
        ChatMessageEntity chatMessageEntity = new ChatMessageEntity();

        chatMessageEntity.setChatRoomEntity(chatRoomEntity);

        chatMessageEntity.setWriter(chatMessageSaveDTO.getWriter());
        chatMessageEntity.setMessage(chatMessageSaveDTO.getMessage());

        return chatMessageEntity;

    }
}
