package com.example.shadow.domain.shadow;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Set;

@Controller
@Slf4j
public class ShadowController {

    @GetMapping("/newshadow")
    public String createView(){
        return "shadow/shadow_form";
    }

    @PostMapping("/newshadow")
    public String createShadow(String[] keyword, String[] flow, String[] description, String[] url, Boolean[] favorite){


        for(String s : keyword){
            System.out.println("keyword = " + s);
        }

        for(String s : flow){
            System.out.println("flow = " + s);
        }

        for(int i = 0 ; i < flow.length - 1; i++){
            System.out.println("flow" + i + " = " + flow[i]);
            System.out.println("description" + i + " = " + description[i]);
            System.out.println("url" + i + " = " + url[i]);
            System.out.println("favorite" + i + " = " + favorite[i]);
        }

        return "shadow/shadow_form";
    }

    @RequestMapping("/my/shadows")
    public String list(){
        return "shadow/shadow_list";
    }

    @RequestMapping("/my/shadows/{id}")
    public String detail(Long id){
        return "shadow/flow_list";
    }

    @RequestMapping("/my/plugins")
    public String showPlugins(){
        return "shadow/plugins";
    }
}
