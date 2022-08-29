package com.example.shadow.domain.shadow.controller;

import com.example.shadow.domain.member.entity.Member;
import com.example.shadow.domain.member.service.MemberService;
import com.example.shadow.domain.shadow.dto.KeywordDto;
import com.example.shadow.domain.shadow.dto.ShadowDto;
import com.example.shadow.domain.shadow.entity.*;
import com.example.shadow.domain.shadow.repository.ShadowRepository;
import com.example.shadow.domain.shadow.entity.Shadow;
import com.example.shadow.domain.shadow.service.FlowChartService;
import com.example.shadow.domain.shadow.service.FlowService;
import com.example.shadow.domain.shadow.service.KeywordService;
import com.example.shadow.domain.shadow.service.ShadowService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
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

    private final ShadowService shadowService;
    private final ShadowRepository shadowRepository; // list에서 사용
    private final KeywordService keywordService;
    private final FlowChartService flowchartService;
    private final FlowService flowService;
    private final MemberService memberService;

    @GetMapping("/shadow/create")
    public String createView(Model model){

        List<String> keywordCodes = new ArrayList<>();
        keywordCodes.add("반품");
        keywordCodes.add("배송조회");
        keywordCodes.add("구매목록조회");

        model.addAttribute("keywordCodes",keywordCodes);
        return "shadow/shadow_form";
    }

    @PostMapping("/shadow/create")
    @ResponseBody
    public HashMap<String, String> createShadow(String shadow, Principal principal) throws JsonProcessingException {

        System.out.println("shadow = " + shadow);

        ObjectMapper objectMapper = new ObjectMapper();
        ShadowDto shadowDto = objectMapper.readValue(shadow, ShadowDto.class);

//      debug
        log.info("name = {}",shadowDto.getName());
        log.info("mainurl = {}", shadowDto.getMainurl());
        for(KeywordDto k : shadowDto.getKeyword()){
            log.info("keyword = {}", k.getName());
/*            for(String s : k.getFlow())
                log.info("flow = {}", s);
            for(String s : k.getDescription())
                log.info("description = {}", s);
            for(String s : k.getUrl())
                log.info("url = {}", s);*/
            log.info("favorite = {}",k.getFavorite());
        }

        Member member = memberService.findByUsername(principal.getName());

        //shadow
        shadowService.create(shadowDto.getName(), shadowDto.getMainurl(), member);

        //keyword
        Shadow findShadow = shadowService.findByNameAndMember(shadowDto.getName(), member);
        keywordService.create(shadowDto.getKeyword(), findShadow);

        //flow
        flowService.create(shadowDto.getKeyword());

        //flowchart
        flowchartService.create(shadowDto.getKeyword());

        HashMap<String, String> redirectMsg = new HashMap<>();
        redirectMsg.put("redirect", "/shadow/list");

        return redirectMsg;
    }

    @GetMapping("/shadow/update/{id}")
    public String modify(Model model){


        return "shadow/shadow_update";
    }


    @RequestMapping("/shadow/list")
    public String list(Model model){
        List<Shadow> shadowList = this.shadowService.findAll();
        model.addAttribute("shadowList", shadowList);
        return "shadow/shadow_list";
    }

    @RequestMapping("/shadow/detail/{id}")
    public String detail(@PathVariable Long id, Model model){
        Shadow shadow = shadowService.findById(id);
        log.debug("shadow : "+shadow.getName()+" / " + shadow.getMainurl());
        model.addAttribute("shadow", shadow);
        List<Keyword> keywords = shadow.getKeywords();
        keywords.forEach(keyword ->
                {
                    log.debug("keyword : "+ keyword.getName());
                    List<Flowchart> flowcharts = keyword.getFlowcharts();
                    flowcharts.forEach(
                            flowchart -> {
                                log.debug("flowchart : "+flowchart.getId());
                                Flow flow = flowchart.getFlow();
                                log.debug("flow : "+flow.getName()+" / "+flow.getDescription()+" / "+ flow.getUrl());
                            }
                    );
                }
        );


        return "shadow/flow_list";
    }

    //여기는 /shadow/detail/{id} 해당 뷰 하단에 작성 예정
    @RequestMapping("/my/plugins")
    public String showPlugins(){
        return "shadow/plugins";
    }
}
