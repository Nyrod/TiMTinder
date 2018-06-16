package com.tim.tinder.services;

import com.tim.tinder.entities.Match;
import com.tim.tinder.entities.Message;
import com.tim.tinder.entities.Token;
import com.tim.tinder.entities.User;
import com.tim.tinder.model.ChatMessage;
import com.tim.tinder.model.MatchPojo;
import com.tim.tinder.model.Response;
import com.tim.tinder.model.ResponseChatMessage;
import com.tim.tinder.parser.MatchToMatchPojo;
import com.tim.tinder.parser.MessageToChatMessage;
import com.tim.tinder.repositories.MatchRepository;
import com.tim.tinder.repositories.MessageRepository;
import com.tim.tinder.repositories.UserRepository;
import com.tim.tinder.services.interfaces.ChatService;
import com.tim.tinder.services.interfaces.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ChatServiceImpl implements ChatService{

    private MessageRepository messageRepository;
    private MatchRepository matchRepository;
    private UserRepository userRepository;
    private TokenService tokenService;

    @Autowired
    public ChatServiceImpl(MessageRepository messageRepository, MatchRepository matchRepository, UserRepository userRepository, TokenService tokenService) {
        this.messageRepository = messageRepository;
        this.matchRepository = matchRepository;
        this.userRepository = userRepository;
        this.tokenService = tokenService;
    }

    @Override
    public ResponseChatMessage saveMessage(ChatMessage chatMessage, Long idChat) {
        User user = tokenService.getUserByToken(chatMessage.getToken());
        Optional<Match> matchOptional = matchRepository.findById(idChat);
        Message message = new Message();
        message.setMatch(matchOptional.get());
        message.setMessage(chatMessage.getMessage());
        message.setUserFrom(user);
        message.setDate(new Date());
        messageRepository.save(message);

        ResponseChatMessage response = new ResponseChatMessage();
        response.setIdMatch(matchOptional.get().getIdMatch());
        response.setIdSender(user.getIdUser());
        response.setMessage(chatMessage.getMessage());
        response.setDate(new Date());
        return response;
    }

    @Override
    public List<ResponseChatMessage> getAllMessage(Long idChat) {
        Optional<Match> matchOptional = matchRepository.findById(idChat);
        if(matchOptional.isPresent()) {
            return parseMessageToResponseMessage(matchOptional.get().getMessages());
        }
        return new ArrayList<>();
    }

    public List<ResponseChatMessage> parseMessageToResponseMessage(List<Message> messageList) {
        List<ResponseChatMessage> ret = new ArrayList<>();
        System.out.println(messageList.size());
        for(Message message: messageList) {
            ResponseChatMessage response = new ResponseChatMessage();
            response.setIdMatch(message.getMatch().getIdMatch());
            response.setIdSender(message.getUserFrom().getIdUser());
            response.setMessage(message.getMessage());
            response.setDate(message.getDate());
            ret.add(response);
            System.out.println(response);
        }

        return ret;
    }
}
