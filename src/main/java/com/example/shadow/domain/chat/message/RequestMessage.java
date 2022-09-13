package com.example.shadow.domain.chat.message;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RequestMessage {

    private String message;

    public RequestMessage(){}
}