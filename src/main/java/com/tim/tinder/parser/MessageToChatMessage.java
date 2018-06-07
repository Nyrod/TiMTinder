package com.tim.tinder.parser;

import com.tim.tinder.entities.Message;
import com.tim.tinder.model.ChatMessage;

import java.util.ArrayList;
import java.util.List;

public class MessageToChatMessage {

    public static List<ChatMessage> messageToChatMessage(List<Message> messageList) {
        List<ChatMessage> chatMessageList = new ArrayList<>();
        for(Message message: messageList) {
            chatMessageList.add(messageToChatMessage(message));
        }
        return chatMessageList;
    }

    public static ChatMessage messageToChatMessage(Message message) {
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setIdSender(message.getUserFrom().getIdUser());
        chatMessage.setDate(message.getDate());
        chatMessage.setMessage(message.getMessage());
        return chatMessage;
    }

    public static Message chatMessageToMessage(ChatMessage chatMessage) {
        Message message = new Message();
        message.setDate(chatMessage.getDate());
        message.setMessage(chatMessage.getMessage());
        return message;
    }
}
