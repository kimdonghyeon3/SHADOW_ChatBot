package com.example.shadow.chatbot.ajax;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RequestMessage {

    private String message;

    public RequestMessage(){}
}