package com.example.shadow.chatbot.websocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.HtmlUtils;

@Controller
@Slf4j
public class ChatController {

    @RequestMapping("/chat")
    public String chatGET(){

        log.info("제대로 실행되니?");

        return "chat/chat";
    }

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Message messageGet(Message message) throws Exception {
        log.info("messageGet?");
        Thread.sleep(500); // simulated delay
        return new Message("답변 : ??? , 질문한 내용 :  " + HtmlUtils.htmlEscape(message.getContent()));
    }
}
