package com.tim.tinder.controllers;

import com.tim.tinder.model.ChatMessage;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    @MessageMapping("/webSocket/chat/{idChat}")
    @SendTo("/chat/{idChat}")
    public ChatMessage sendMessage(@DestinationVariable Long idChat, @Payload ChatMessage chatMessage) {
        return chatMessage;
    }
}
