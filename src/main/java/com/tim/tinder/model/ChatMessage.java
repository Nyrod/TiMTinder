package com.tim.tinder.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessage {

    private String token;
    private String message;

    @Override
    public String toString() {
        return "ChatMessage{" +
                "idSender=" + token +
                ", message='" + message + '\'' +
                '}';
    }
}
