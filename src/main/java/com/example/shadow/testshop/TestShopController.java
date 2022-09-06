package com.example.shadow.testshop;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestShopController {

    @RequestMapping("/myhome")
    public String myhome(){
        return "testshop/myhome";
    }

    @RequestMapping("/orderlist")
    public String orderlist(){
        return "testshop/main";
    }

    @RequestMapping("/return")
    public String returnOrder(){
        return "testshop/main";
    }
}
