package com.example.shadow.sitemap;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RobotsController {

    @RequestMapping(value = "/robots.txt")
    @ResponseBody
    public String robots() {
        return "User-agent: *\nAllow: /md\n";
    }

}
