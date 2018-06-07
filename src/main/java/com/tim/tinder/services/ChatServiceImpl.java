package com.tim.tinder.services;

import com.tim.tinder.entities.Match;
import com.tim.tinder.entities.Message;
import com.tim.tinder.entities.User;
import com.tim.tinder.model.ChatMessage;
import com.tim.tinder.parser.MessageToChatMessage;
import com.tim.tinder.repositories.MatchRepository;
import com.tim.tinder.repositories.MessageRepository;
import com.tim.tinder.repositories.UserRepository;
import com.tim.tinder.services.interfaces.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ChatServiceImpl implements ChatService{

    private MessageRepository messageRepository;
    private MatchRepository matchRepository;
    private UserRepository userRepository;

    @Autowired
    public ChatServiceImpl(MessageRepository messageRepository, MatchRepository matchRepository, UserRepository userRepository) {
        this.messageRepository = messageRepository;
        this.matchRepository = matchRepository;
        this.userRepository = userRepository;
    }
    
    public void saveMessage(ChatMessage chatMessage, Long idChat) {
        Message message = MessageToChatMessage.chatMessageToMessage(chatMessage);
        Optional<User> userOptional = userRepository.findById(chatMessage.getIdSender());
        Optional<Match> matchOptional = matchRepository.findById(idChat);
        if(userOptional.isPresent() && matchOptional.isPresent()) {
            message.setUserFrom(userOptional.get());
            message.setMatch(matchOptional.get());
            messageRepository.save(message);
        }
    }
    
    public List<ChatMessage> getAllMessage(Long idChat) {
        Optional<Match> matchOptional = matchRepository.findById(idChat);
        if(matchOptional.isPresent()) {
            Match match = matchOptional.get();
            return MessageToChatMessage.messageToChatMessage(match.getMessages());
        }
        return new ArrayList<>();
    }
}
