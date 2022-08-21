package com.example.shadow.chatbot;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class chatTest {


    @RequestMapping("/test1")
    public String test1(){

        return "chat/test1";
    }

    @RequestMapping("/test2")
    public String test2(){

        return "chat/test2";
    }

    @RequestMapping("/test3")
    public String test3(){

        return "chat/test3";
    }

}
