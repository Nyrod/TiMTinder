package com.tim.tinder.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseChatMessage {

    Long idMatch;
    Long idSender;
    String message;
    Date date;

    @Override
    public String toString() {
        return "ResponseChatMessage{" +
                "idMatch=" + idMatch +
                ", idSender=" + idSender +
                ", message='" + message + '\'' +
                ", date=" + date +
                '}';
    }
}
