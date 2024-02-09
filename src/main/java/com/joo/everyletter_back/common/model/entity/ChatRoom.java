package com.joo.everyletter_back.common.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "CHAT_ROOM")
public class ChatRoom extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String roomId;

    @Column
    private String roomName;

    @Column
    private String chatMentor;

    @OneToMany(mappedBy = "chatRoom", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ChatMessage> chatMessageList = new ArrayList<>();


    public static ChatRoom toChatRoom(String roomName, String roomId, String chatMentor){
        ChatRoom chatRoom = new ChatRoom();
        chatRoom.setRoomName(roomName);
        chatRoom.setRoomId(roomId);
        chatRoom.setChatMentor(chatMentor);
        return chatRoom;
    }
}