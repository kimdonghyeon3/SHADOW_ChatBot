package com.example.shadow.testshop;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestShopController {

    @RequestMapping("/testshop")
    public String myhome(){
        return "testshop/main";
    }

    @RequestMapping("/testshop/1")
    public String orderlist(){
        return "testshop/test1";
    }

    @RequestMapping("/testshop/2")
    public String returnOrder(){
        return "testshop/test2";
    }
}
