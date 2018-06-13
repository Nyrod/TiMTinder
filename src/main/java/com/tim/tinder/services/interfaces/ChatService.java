package com.tim.tinder.services.interfaces;

import com.tim.tinder.model.ChatMessage;

public interface ChatService {

//    List<ChatMessage> getAllMessage(Long idChat);
    void saveMessage(ChatMessage chatMessage, Long idChat);
}
