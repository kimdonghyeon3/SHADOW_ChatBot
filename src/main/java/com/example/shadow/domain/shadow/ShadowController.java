package com.example.shadow.domain.shadow;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ShadowController {

    @RequestMapping("/newshadow")
    public String create(){
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
