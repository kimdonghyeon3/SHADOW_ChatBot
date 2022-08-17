package com.example.shadow.chatbot.websocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.HtmlUtils;

import java.util.Optional;

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
    public ResponseMessage messageGet(RequestMessage requestMessage) throws Exception {
        log.info("requestMessage = {}", requestMessage);
        Thread.sleep(500); // simulated delay
        ResponseMessage responseMessage = new ResponseMessage();

        //예시 코드 (이부분은 clova랑 합시다)
        if(requestMessage.getContent().equals("반품 어떻게 해요?") || requestMessage.getContent().equals("반품")){
            responseMessage.setContent(HtmlUtils.htmlEscape("반품은 이곳 -> 이곳 -> 이곳으로 이동해주세요"));
        }

        if(requestMessage.getContent().equals("상품조회 어떻게 해요?") || requestMessage.getContent().equals("상품조회")){
            responseMessage.setContent(HtmlUtils.htmlEscape("상품조회은 이곳 -> 이곳 -> 이곳으로 이동해주세요"));
        }

        Optional<String> optional = Optional.ofNullable(responseMessage.getContent());

        if(!optional.isPresent()){
            responseMessage.setContent(HtmlUtils.htmlEscape("질문을 알아 들을 수 없습니다."));
        }

        return  responseMessage;
    }
}
