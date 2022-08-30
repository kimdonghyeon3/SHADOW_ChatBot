package com.example.shadow.domain.shadow;

import com.example.shadow.domain.member.entity.Member;
import com.example.shadow.domain.member.service.MemberService;
import com.example.shadow.domain.shadow.dto.FlowDto;
import com.example.shadow.domain.shadow.dto.KeywordDto;
import com.example.shadow.domain.shadow.dto.ShadowDto;
import com.example.shadow.domain.shadow.entity.Flow;
import com.example.shadow.domain.shadow.entity.Flowchart;
import com.example.shadow.domain.shadow.entity.Keyword;
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

        Member member = memberService.findByUsername(principal.getName());

        shadowService.create(shadowDto.getName(), shadowDto.getMainurl(), member);
        Shadow findShadow = shadowService.findByNameAndMember(shadowDto.getName(), member);
        keywordService.create(shadowDto.getKeyword(), findShadow);
        flowService.create(shadowDto.getKeyword());
        flowchartService.create(shadowDto.getKeyword(), findShadow);

        HashMap<String, String> redirectMsg = new HashMap<>();
        redirectMsg.put("redirect", "/shadow/list");

        return redirectMsg;
    }

    @GetMapping("/shadow/update/{id}")
    public String update(Model model, @PathVariable Long id){

        Shadow shadow = shadowService.findById(id);

        model.addAttribute("shadow", shadow);

        List<String> keywordCodes = new ArrayList<>();
        keywordCodes.add("반품");
        keywordCodes.add("배송조회");
        keywordCodes.add("구매목록조회");

        model.addAttribute("keywordCodes",keywordCodes);

        return "shadow/shadow_update";
    }

    @PostMapping("/shadow/update/{id}")
    @ResponseBody
    public HashMap<String, String> update(String shadow, @PathVariable Long id, Principal principal) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        ShadowDto shadowDto = objectMapper.readValue(shadow, ShadowDto.class);

        log.info("pathvariable id = {}", id);
        log.info("SHADOW name = {}, SHADOW mainurl = {}", shadowDto.getName(), shadowDto.getMainurl());
        for(KeywordDto k : shadowDto.getKeyword()){
            log.info("keyword = {}", k.getName());
            List<FlowDto> flow = k.getFlow();
            for (FlowDto flowDto : flow) {
                log.info("flow name = {}, flow Description = {}, flow URL = {}", flowDto.getName(), flowDto.getDescription(), flowDto.getUrl());
            }
            log.info("favorite = {}",k.getFavorite());
        }

        Member member = memberService.findByUsername(principal.getName());
        Shadow originShadow = shadowService.findById(id);   //기존 shadow 찾아오기

        //Shadow 수정
        shadowService.update(originShadow, shadowDto);

        //Keeyword 수정
        keywordService.update(originShadow, shadowDto);

        //Flow 수정
        flowService.update(originShadow, shadowDto);

        //FlowChart수정
        flowchartService.update(originShadow, shadowDto);

        HashMap<String, String> redirectMsg = new HashMap<>();
        redirectMsg.put("redirect", "/shadow/detail/"+id);

        return redirectMsg;
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
