package com.example.shadow.domain.shadow;

import com.example.shadow.domain.shadow.dto.KeywordDto;
import com.example.shadow.domain.shadow.dto.ShadowDto;
import com.example.shadow.domain.shadow.entity.Keyword;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Array;
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
    public String createView(Model model){
        model.addAttribute("shadow",new ShadowDto());
        return "shadow/shadow_form";
    }

    @PostMapping("/shadow/create")
    public String createShadow(HttpServletRequest request, ShadowDto shadow){

        Enumeration<String> parameterNames = request.getParameterNames();

        while(parameterNames.hasMoreElements()) {
            String key = parameterNames.nextElement();
            System.out.println(key + ": " + request.getParameter(key));
        }

        System.out.println("shadow.getName() = " + shadow.getName());

        return "redirect:/shadow/list";
        //return "shadow/shadow_form";
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
