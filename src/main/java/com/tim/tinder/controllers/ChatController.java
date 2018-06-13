package com.tim.tinder.controllers;

import com.tim.tinder.model.ChatMessage;
import com.tim.tinder.services.interfaces.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/rest/chat")
@CrossOrigin("http://localhost:4200")
public class ChatController {

    private ChatService chatService;

    @Autowired
    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @MessageMapping("/webSocket/chat/{idChat}")
    @SendTo("/chat/{idChat}")
    public ChatMessage sendMessage(@DestinationVariable Long idChat, @Payload ChatMessage chatMessage) {
        chatService.saveMessage(chatMessage, idChat);
        return chatMessage;
    }

    @RequestMapping(value = "/getAllMessage", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ChatMessage>> getAllChatMessage(@RequestParam("idChat") Long idChat) {
        List<ChatMessage> allMessage = chatService.getAllMessage(idChat);
        return new ResponseEntity<>(allMessage, HttpStatus.OK);
    }
}
