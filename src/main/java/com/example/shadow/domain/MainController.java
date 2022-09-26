package com.example.shadow.domain;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @RequestMapping("/")
    public String root(Model model) {
        model.addAttribute("pageTitle", "Main Page");
        return "intro";
    }
    @RequestMapping("/main")
    public String main(Model model) {
        model.addAttribute("pageTitle", "Main Page");
        return "index";
    }
}
