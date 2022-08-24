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

    /*
    List (나의 SHADOW 목록)
    /shadow/list
    C (SHADOW 새로 만들기)
    /shadow/create
    R (챗봇 디테일)
    /shadow/detail/{id}
    U (챗봇 수정)
    /shadow/update/{id}
    D (챗봇 삭제)
    /shadow/delete/{id}
    */

    @GetMapping("/shadow/create")
    public String createView(){
        return "shadow/shadow_form";
    }

    @PostMapping("/shadow/create")
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

    @RequestMapping("/shadow/list")
    public String list(){
        return "shadow/shadow_list";
    }

    @RequestMapping("/shadow/detail/{id}")
    public String detail(Long id){
        return "shadow/flow_list";
    }

    //여기는 /shadow/detail/{id} 해당 뷰 하단에 작성 예정
    @RequestMapping("/my/plugins")
    public String showPlugins(){
        return "shadow/plugins";
    }
}
