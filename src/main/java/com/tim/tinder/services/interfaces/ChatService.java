package com.tim.tinder.services.interfaces;

import com.tim.tinder.model.ChatMessage;
import com.tim.tinder.model.ResponseChatMessage;

import java.util.List;

public interface ChatService {

    List<ResponseChatMessage> getAllMessage(Long idChat);
    ResponseChatMessage saveMessage(ChatMessage chatMessage, Long idChat);
}
