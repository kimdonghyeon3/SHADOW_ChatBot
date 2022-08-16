package com.example.shadow.chatbot.websocket;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RequestMessage {

    private String content;

    public RequestMessage(){}
}
