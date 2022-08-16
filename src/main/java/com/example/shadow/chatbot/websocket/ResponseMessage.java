package com.example.shadow.chatbot.websocket;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseMessage {

    private String content;

    public ResponseMessage(){}
}
