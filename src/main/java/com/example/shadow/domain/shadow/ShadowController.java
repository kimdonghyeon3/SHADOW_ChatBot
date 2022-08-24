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
    public String createShadow(HttpServletRequest request, String[] keyword){

        Set<String> keySet = request.getParameterMap().keySet();
        for(String key: keySet) {
            System.out.println(key + ": " + request.getParameter(key));
        }

        for(String s : keyword){
            System.out.println("s = " + s);
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
