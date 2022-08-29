package com.example.shadow.domain.shadow.controller;

import com.example.shadow.domain.shadow.dto.KeywordDto;
import com.example.shadow.domain.shadow.dto.ShadowDto;
import com.example.shadow.domain.shadow.entity.Keyword;
import com.example.shadow.domain.shadow.entity.Shadow;
import com.example.shadow.domain.shadow.repository.ShadowRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RequiredArgsConstructor
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
    @ResponseBody
    public HashMap<String, String> createShadow(String shadow) throws JsonProcessingException {

        System.out.println("shadow = " + shadow);

        ObjectMapper objectMapper = new ObjectMapper();
        ShadowDto shadowDto = objectMapper.readValue(shadow, ShadowDto.class);

//      debug
        log.info("name = {}",shadowDto.getName());
        log.info("mainurl = {}", shadowDto.getMainurl());
        for(KeywordDto k : shadowDto.getKeyword()){
            log.info("keyword = {}", k.getName());
            for(String s : k.getFlow())
                log.info("flow = {}", s);
            for(String s : k.getDescription())
                log.info("description = {}", s);
            for(String s : k.getUrl())
                log.info("url = {}", s);
            log.info("favorite = {}",k.getFavorite());
        }

        HashMap<String, String> redirectMsg = new HashMap<>();
        redirectMsg.put("redirect", "/shadow/list");

        return redirectMsg;
        //return "shadow/shadow_form";
    }

    private final ShadowRepository shadowRepository;
    @RequestMapping("/shadow/list")
    public String list(Model model){
        List<Shadow> shadowList = this.shadowRepository.findAll();
        model.addAttribute("shadowList", shadowList);
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
