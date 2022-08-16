package com.example.shadow.chatbot.websocket;

import lombok.Data;

@Data
public class Message {

    private String content;
    //받은 질문?
    //답변 ?

    public Message(){}

    public Message(String content){ this.content = content;}
}
