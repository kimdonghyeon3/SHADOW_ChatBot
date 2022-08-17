package com.example.shadow.domain.member;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @RequestMapping("/")
    public String root() {
        return "index";
    }
    @RequestMapping("/admin")
    public String admin() {
        return "index";
    }
    @RequestMapping("/main")
    public String main() {
        return "index";
    }
}
