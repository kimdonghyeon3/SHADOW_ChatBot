package com.example.shadow.chatbot.ajax;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.HtmlUtils;

import java.util.Optional;

@Controller
@Slf4j
public class AjaxChatController {

    @RequestMapping("/chat")
    public String chatGET(){

        log.info("제대로 실행되니?");

        return "chat/ajax/chat";
    }

    @PostMapping("/chat/write")
    @ResponseBody
    public ResponseMessage writeMessage(RequestMessage requestMessage){
        log.info("message = {}", requestMessage.getMessage());

        ResponseMessage responseMessage = new ResponseMessage();

        //예시 코드 (이부분은 clova랑 합시다)
        if(requestMessage.getMessage().equals("반품 어떻게 해요?") || requestMessage.getMessage().equals("반품")){
            responseMessage.setMessage(HtmlUtils.htmlEscape("반품은 이곳 - 이곳 - 이곳으로 이동해주세요"));
        }

        if(requestMessage.getMessage().equals("상품조회 어떻게 해요?") || requestMessage.getMessage().equals("상품조회")){
            responseMessage.setMessage(HtmlUtils.htmlEscape("상품조회은 이곳 - 이곳 - 이곳으로 이동해주세요"));
        }

        Optional<String> optional = Optional.ofNullable(responseMessage.getMessage());

        if(!optional.isPresent()){
            responseMessage.setMessage(HtmlUtils.htmlEscape("질문을 알아 들을 수 없습니다."));
        }

        return responseMessage;
    }
}
