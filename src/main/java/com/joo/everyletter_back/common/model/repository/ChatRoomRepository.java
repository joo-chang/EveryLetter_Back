package com.joo.everyletter_back.common.model.repository;

import com.joo.everyletter_back.chat.dto.ChatRoomDTO;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.*;

@Repository
public class ChatRoomRepository{
    private Map<String, ChatRoomDTO> chatRoomMap;

    @PostConstruct
    private void init(){
        chatRoomMap = new LinkedHashMap<>();
    }

    public List<ChatRoomDTO> findAllRooms(){
        //채팅방 생성 순서 최근 순으로 반환
        List<ChatRoomDTO> result = new ArrayList<>(chatRoomMap.values());
        Collections.reverse(result);

        return result;
    }

    public ChatRoomDTO findRoomById(String id){
        return chatRoomMap.get(id);
    }

    public ChatRoomDTO createChatRoomDTO(String name){
        ChatRoomDTO room = ChatRoomDTO.create(name);
        chatRoomMap.put(room.getRoomId(), room);

        return room;
    }
}

