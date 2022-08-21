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

    @RequestMapping("/chat2")
    public String chatGET(){

        log.info("제대로 실행되니?");

        return "chat/ajax/chat2";
    }

    @PostMapping("/chat2/write")
    @ResponseBody
    public ResponseMessage1 writeMessage(RequestMessage1 requestMessage){
        log.info("message = {}", requestMessage.getMessage());

        ResponseMessage1 responseMessage = new ResponseMessage1();

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

    @RequestMapping("/layout/test1")
    public String test1(){
        return "chat/layoutTest/layouttest1";
    }

    @RequestMapping("/layout/test2")
    public String test2(){
        return "chat/layoutTest/layouttest2";
    }

    @RequestMapping("/basic/test1")
    public String test3(){
        return "chat/originalTest/test1";
    }

    @RequestMapping("/basic/test2")
    public String test4(){
        return "chat/originalTest/test2";
    }

}
