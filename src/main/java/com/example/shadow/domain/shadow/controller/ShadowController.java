package com.example.shadow.domain.shadow.controller;

import com.example.shadow.domain.member.entity.Member;
import com.example.shadow.domain.member.entity.MemberRole;
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
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

// markdown
import org.commonmark.node.*;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import org.springframework.web.servlet.ModelAndView;

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

    // Script File 위치 지정
    private final static String LOCAL_MANUAL_PATH = "static/manuals/";

    @RequestMapping("/admin")
    public ModelAndView list(Model model, ModelAndView mav, Principal principal){

        log.debug("[admin] 유저 관리자인지 여부 판단 : "+ principal.getName());

        Member member = memberService.findByUsername(principal.getName());
        if(!member.getRole().equals(MemberRole.ADMIN)){
            mav.addObject("msg","접근이 불가능합니다.");
            mav.addObject("url","/main");
            mav.setViewName("alert");
            return mav;
        }
        log.debug("[admin] 모든 shadows 찾기");
        List<Shadow> shadows = shadowService.findAll();
        mav.addObject("shadows",shadows);
        mav.addObject("pageTitle", "Statistics");
        mav.setViewName("admin/member_api_db_call");
        return mav;

    }

    @RequestMapping("/count")
    public String callShow(Model model, Principal principal){

        log.info("관리자 페이지 요청 받아지니? = {}", principal.getName());

        Member member = memberService.findByUsername(principal.getName());
        List<Shadow> shadows = shadowService.findByMember(member);
        model.addAttribute("shadows",shadows);
        model.addAttribute("pageTitle", "Statistics");

        return "admin/api_db_call";
    }

    @RequestMapping("/test")
    public String test(Model model) {
        List<Shadow> shadowList = this.shadowService.findAll();
        model.addAttribute("shadowList", shadowList);
        model.addAttribute("pageTitle", "test");
        return "testPage";
    }

    @RequestMapping("/test2")
    public String test2(Model model) {
        List<Shadow> shadowList = this.shadowService.findAll();
        model.addAttribute("shadowList", shadowList);
        return "testPage2";
    }

    @GetMapping("/shadow/create")
    public String createView(Model model){

        List<String> keywordCodes = new ArrayList<>();
        keywordCodes.add("반품");
        keywordCodes.add("배송조회");
        keywordCodes.add("구매목록조회");

        model.addAttribute("keywordCodes",keywordCodes);
        model.addAttribute("pageTitle", "Make Shadow");
        return "shadow/shadow_form";
    }

    @PostMapping("/shadow/create")
    @ResponseBody
    public HashMap<String, String> createShadow(Model model, String shadow, Principal principal) throws JsonProcessingException {

        System.out.println("shadow = " + shadow);

        ObjectMapper objectMapper = new ObjectMapper();
        ShadowDto shadowDto = objectMapper.readValue(shadow, ShadowDto.class);

        Member member = memberService.findByUsername(principal.getName());

        shadowService.create(shadowDto.getName(), shadowDto.getMainurl(), member);
        Shadow findShadow = shadowService.findByNameAndMember(shadowDto.getName(), member);
        keywordService.create(shadowDto.getKeyword(), findShadow);
        flowService.create(shadowDto.getKeyword(), findShadow);
        flowchartService.create(shadowDto.getKeyword(), findShadow);

        HashMap<String, String> redirectMsg = new HashMap<>();
        redirectMsg.put("redirect", "/shadow/list");

        model.addAttribute("pageTitle", "Make Shadow");

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
        model.addAttribute("pageTitle", "Modify Shadow");

        return "shadow/shadow_update";
    }

    @PostMapping("/shadow/update/{id}")
    @ResponseBody
    public HashMap<String, String> update(Model model, String shadow, @PathVariable Long id, Principal principal) throws JsonProcessingException {

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

        model.addAttribute("pageTitle", "Modify Shadow");

        return redirectMsg;
    }

    @RequestMapping("/shadow/list")
    public String list(Model model,Principal principal){
        Member member = this.memberService.findByUsername(principal.getName());
        List<Shadow> shadowList = this.shadowService.findByMember(member);
        model.addAttribute("shadowList", shadowList);

        model.addAttribute("pageTitle", "My Shadow List");
        return "shadow/shadow_list";
    }

    @RequestMapping("/shadow/detail/{id}")
    public ModelAndView detail(@PathVariable Long id, ModelAndView mav, Principal principal) throws Exception {
        Shadow shadow = shadowService.findById(id);
        Member member = shadow.getMember();
        if(!member.getUsername().equals(principal.getName())){
            mav.addObject("msg","접근이 불가능합니다.");
            mav.addObject("url","/members");
            mav.setViewName("alert");
            return mav;
        }
        log.debug("shadow : "+shadow.getName()+" / " + shadow.getMainurl());

        mav.addObject("shadow",shadow);
        mav.addObject("pageTitle", "Shadow Detail");


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



        String api_key = shadow.getApiKey();

        // code viewer(Markdown)
        String markdownValueFormLocal = """
                ```javascript
                    <script type="text/javascript" async=true charset="UTF-8" src="https://shadows.site/js/chat.js"></script>
                    <script>
                        window.dyc = {
                            chatUid: \"%s\"              
                        }
                    </script>
                ```
                """.formatted(api_key);

        Parser parser = Parser.builder().build();
        Node document = parser.parse(markdownValueFormLocal);
        HtmlRenderer renderer = HtmlRenderer.builder().build();

        mav.addObject("contents", renderer.render(document));

        mav.setViewName("shadow/flow_list");
        return mav;
    }

    @RequestMapping("/shadow/update/db/{id}")
    public String update_api_key(@PathVariable Long id) {
        Shadow shadow = shadowService.findById(id);
        String new_api_key = UUID.randomUUID().toString().replace("-","");
        shadowService.updateApiKey(shadow, new_api_key);

        return "redirect:/shadow/detail/{id}";
    }

    @GetMapping("/shadow/delete/{id}")
    public String delete(Model model, @PathVariable Long id){
        Shadow shadow = shadowService.findById(id);
        keywordService.delete(shadow);
        shadowService.delete(shadow);

        model.addAttribute("pageTitle", "Shadow Delete");

        return "redirect:/shadow/list";
    }

}
