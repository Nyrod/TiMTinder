package com.tim.tinder.controllers;

import com.tim.tinder.model.ChatMessage;
import com.tim.tinder.model.ResponseChatMessage;
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
@CrossOrigin("http://localhost:4200")
public class ChatController {

    private ChatService chatService;

    @Autowired
    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @MessageMapping("/sendMessage/{idChat}")
    @SendTo("/chat/getMessage")
    public ResponseChatMessage sendMessage(@DestinationVariable Long idChat, @Payload ChatMessage chatMessage) {
        return chatService.saveMessage(chatMessage, idChat);
    }

    @RequestMapping(value = "/rest/chat/getAllMessage", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ResponseChatMessage>> getAllChatMessage(@RequestParam("idChat") Long idChat) {
        return new ResponseEntity<>(chatService.getAllMessage(idChat), HttpStatus.OK);
    }
}
