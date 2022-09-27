package com.example.shadow.domain.chat;

import com.example.shadow.domain.member.service.MemberService;
import com.example.shadow.domain.shadow.entity.*;
import com.example.shadow.domain.shadow.service.FlowChartService;
import com.example.shadow.domain.shadow.service.KeywordService;
import com.example.shadow.domain.shadow.service.QuestionService;
import com.example.shadow.domain.shadow.service.ShadowService;
import com.example.shadow.global.result.ResultResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Controller
@Slf4j
public class ChatController {
    // 재순님
//    private static String secretKey = "SHVTeG5SemVXRk9KcU1oSU1VVWpWeW1MQmxCY0xzSk4=";
//    private static String apiUrl = "https://z16j1lin9x.apigw.ntruss.com/custom/v1/7654/bb5bef27a0dd572b921c6b22c71e79115c1d4cca1dcbd766d269fa6c2d5bd9ad";
    private static String secretKey = "U1VoTXZua1BOT3hqVFNjS0Rqemxrc0JCdEZIc2RrSmg=";
    private static String apiUrl = "https://f4by9xj6rc.apigw.ntruss.com/custom/v1/7840/5324714f7fb3a860659ad721c75c0d677d2e1f165bc0f300db74ddf7a6e2e8b1";
    private final QuestionService questionService;
    private final ShadowService shadowService;
    private final MemberService memberService;
    private final KeywordService keywordService;
    private final FlowChartService flowChartService;
    private final Long testShadowId = 1L;

    //보낼 메세지를 네이버에서 제공해준 암호화로 변경해주는 메소드
    public static String makeSignature(String message, String secretKey) {

        String encodeBase64String = "";

        try {
            byte[] secrete_key_bytes = secretKey.getBytes("UTF-8");

            SecretKeySpec signingKey = new SecretKeySpec(secrete_key_bytes, "HmacSHA256");
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(signingKey);

            byte[] rawHmac = mac.doFinal(message.getBytes("UTF-8"));
            encodeBase64String = Base64.encodeBase64String(rawHmac);

            return encodeBase64String;

        } catch (Exception e) {
            System.out.println(e);
        }

        return encodeBase64String;
    }

    //보낼 메세지를 네이버 챗봇에 포맷으로 변경해주는 메소드
    public static String getReqMessage(String chatMessage) {

        String requestBody = "";

        try {

            JSONObject obj = new JSONObject();

            long timestamp = new Date().getTime();

            System.out.println("##" + timestamp); // timestamp 출력

            obj.put("version", "v2");
            obj.put("userId", "U47b00b58c90f8e47428af8b7bddcda3d");
            obj.put("timestamp", timestamp);

            JSONObject bubbles_obj = new JSONObject();

            bubbles_obj.put("type", "text");

            JSONObject data_obj = new JSONObject();
            chatMessage = chatMessage.replace("\"", "");
            data_obj.put("description", chatMessage);

            bubbles_obj.put("type", "text");
            bubbles_obj.put("data", data_obj);

            JSONArray bubbles_array = new JSONArray();
            bubbles_array.add(bubbles_obj);

            obj.put("bubbles", bubbles_array);
            obj.put("event", "send");

            requestBody = obj.toString();
        } catch (Exception e) {
            System.out.println("## Exception : " + e);
        }

        return requestBody;
    }

    @GetMapping("/chat/{apiKey}")
    public String chatGET(Model model, @PathVariable String apiKey) {

        log.info("apikey로 변경된 값을 잘 가져오는가? = {} ", apiKey);

        // 처음 가져올 떄, 해당 사용자 chat에 있는 favorite을 가져오자. (필요한 정보 : member id와 shadow id 가 필요하다.)
        // 우선 가정하자, member id가 1이고 shadow id가 1인 것을 시작했다고 하자.

        HashMap<String, String> favoriteKeywords = new HashMap<>();

        //long memberId = 1;

        long shadowId = shadowService.findByApiKey(apiKey).getId();

        //Member member = memberService.findById(1);
        Shadow shadow = shadowService.findById(shadowId);

        //해당 shadow에 있는 keyword 목록을 가져오자. (favorite이 체크되어있는)
        List<Keyword> keywords = keywordService.findByShadowAndFavorite(shadow);

        //그러면 이제 keyword에 맞는 최종 목적지 url을 가져오지
        //즉 flow의 마지막 단계의 url을 찾아오자는 의미가 된다.
        keywords.forEach(keyword -> {

            //1. flowchart에서 해당 keyword가 있는 것 중에서, order desc순으로, limit 1을 걸어서 가져오자
            Flowchart flowchart = flowChartService.findByKeyword(keyword);

            //2. 가져온 flowchart에 저장되어있는 flow id를 통해서, flow에서 url을 가져오자
            log.info("keyword에서 가져온 flowchart에 seq가 마지막인, flow id = {}", flowchart.getFlow().getId());

            //3. keyword와 url을 합쳐서 Map으로 해서 model에 넣어주자
            favoriteKeywords.put(keyword.getName(), flowchart.getFlow().getUrl());
        });

        model.addAttribute("favoriteKeywords", favoriteKeywords);

        return "chatbot/chat";
    }
    @PostMapping("/chat/{apiKey}")
    public String chatGET(Model model, @RequestParam("shadow_keyword") String keywordName,
                          @RequestParam("shadow_seq") Integer seq, @RequestParam("shadow_url") String url, @PathVariable String apiKey) {


        log.info("시나리오에서는 ? = {} ", apiKey);

        HashMap<String, String> favoriteKeywords = new HashMap<>();

        long shadowId = shadowService.findByApiKey(apiKey).getId();
        Shadow shadow = shadowService.findById(shadowId);

        List<Keyword> keywords = keywordService.findByShadowAndFavorite(shadow);
        keywords.forEach(keyword -> {
            Flowchart flowchart = flowChartService.findByKeyword(keyword);
            log.info("keyword에서 가져온 flowchart에 seq가 마지막인, flow id = {}", flowchart.getFlow().getId());
            favoriteKeywords.put(keyword.getName(), flowchart.getFlow().getUrl());
        });

        model.addAttribute("favoriteKeywords", favoriteKeywords);
        Keyword keyword = keywordService.findByNameAndShadow(keywordName,shadow);

        String targetUrl = getFlowcharts(keyword).get(seq-1).getFlow().getUrl();
        String description = seq == getFlowcharts(keyword).size() ? null : getFlowcharts(keyword).get(seq).getFlow().getDescription();
        log.debug("[scenario] targetUrl : "+targetUrl);
        log.debug("[scenario] description : "+description);
        if(url.equals(targetUrl)){
            seq++;
        }else{
            log.debug("[scenario] targetUrl : "+targetUrl+" url : "+url+" 다름");
        }

        log.debug("[scenario] flowcharts : "+getFlowcharts(keyword));
        log.debug("[scenario] seq : "+seq);
        model.addAttribute("keyword",keywordName);
        model.addAttribute("flowcharts",getFlowcharts(keyword));
        model.addAttribute("seq",seq);
        model.addAttribute("description",description);
        model.addAttribute("fixedMsg",getFixedMsg(keyword));

        return "chatbot/chat";
    }

    @RequestMapping("/chat/question/{apiKey}")
    @SendTo("/topic/shadow")
    @ResponseBody
    public ResponseEntity<ResultResponse> sendScenario(String question, @PathVariable String apiKey) throws IOException {

        // 테스트용 shadow , testShadowId로 지정
        long shadowId = shadowService.findByApiKey(apiKey).getId();
        Shadow shadow = shadowService.findById(shadowId);
        log.debug("[scenario] shadow : " + shadow.getId() + " , " + shadow.getName() + ", " + shadow.getMainurl());
        String reqMessage = question;
        reqMessage = reqMessage.replace("\"", "");


        // [scenario] 시작
        Question q = questionService.existByQuestion(reqMessage) ? questionService.findByQuestionAndShadow(reqMessage,shadow) : null;
        if(q!=null){

                log.debug("[scenario][case1] 키워드 DB에서 도출 시작");
                Keyword keyword = getKeyword(reqMessage,shadow);
                log.debug("[scenario] keyword : " + keyword);

                shadow.addDbCall();
                shadowService.save(shadow);

                return getMessage(keyword);

        } else {
            log.debug("[scenario][case2] 키워드 API에서 도출 시작");
            URL url = new URL(apiUrl);

            String message = getReqMessage(question);
            String encodeBase64String = makeSignature(message, secretKey);

            //api서버 접속 (서버 -> 서버 통신)
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json;UTF-8");
            con.setRequestProperty("X-NCP-CHATBOT_SIGNATURE", encodeBase64String);
            System.out.println("API 호출 완료");

            // post request
            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.write(message.getBytes("UTF-8"));
            wr.flush();
            wr.close();

            int responseCode = con.getResponseCode();

            if (responseCode == 200) { // 정상 호출
                log.debug("[scenario] API 정상 호출");

                shadow.addApiCall();
                shadowService.save(shadow);

                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
                String decodedString;
                String jsonString = "";
                while ((decodedString = in.readLine()) != null) {
                    jsonString = decodedString;
                }

                //받아온 값을 세팅하는 부분
                JSONParser jsonparser = new JSONParser();
                try {
                    JSONObject json = (JSONObject) jsonparser.parse(jsonString);
                    JSONArray bubblesArray = (JSONArray) json.get("bubbles");
                    JSONObject bubbles = (JSONObject) bubblesArray.get(0);
                    JSONObject data = (JSONObject) bubbles.get("data");
                    String description = (String) data.get("description");
                    log.debug("[scenario][case2] 키워드 API에서 도출 , description : " + description);
                    Keyword keyword = keywordService.findByNameAndShadow(description, shadow);
                    log.debug("[scenario][case2] 키워드 API에서 도출 , keyword : " + keyword);

                    if (keyword == null) {
                        String msg = "shadow가 이해하지 못했어요. 다시 한번 입력해 주세요.";
                        log.error("[scenario] 에러 메세지 : " + msg);
                        in.close();
                        return ResponseEntity.ok(ResultResponse.of("NOT_FOUND_KEYWORD", msg, false));
                    }

                    if (!questionService.existByQuestionAndKeyword(reqMessage,keyword)) { // DB에 저장이 안되어 있을 경우 DB에 keyword 저장
                        log.debug("[scenario] db에 저장 : "+reqMessage);
                        create(reqMessage, keyword);
                    }
                    in.close();



                    return getMessage(keyword);

                } catch (Exception e) {
                    String msg = "shadow가 이해하지 못했어요. 다시 한번 입력해 주세요.";
                    log.error("[scenario] 에러 메세지 : " + msg);
                    e.printStackTrace();
                    in.close();
                    return ResponseEntity.ok(ResultResponse.of("NOT_FOUND_KEYWORD", msg, false));
                }

            } else {  // 에러 발생
                log.debug("[scenario] API 정상 호출 실패");
                question = con.getResponseMessage();
                log.error("[scenario] 에러 메세지 : " + question);
            }

            log.error("[scenario] 에러 메세지 : " + question);
            return ResponseEntity.ok(ResultResponse.of("ERROR_GET_KEYWORD", question, false));
        }
    }

    public void create(String question, Keyword keyword) {
        questionService.create(question, keyword);
    }

    private Shadow getShadow(String url) {
        return shadowService.findByMainurl(url);
    }

    private Keyword getKeyword(String reqMessage,Shadow shadow) {
        return questionService.findByQuestionAndShadow(reqMessage,shadow).getKeyword();
    }

    private List<Flowchart> getFlowcharts(Keyword keyword) {
        List<Flowchart> flowcharts = keyword.getFlowcharts();
        if (flowcharts.size() == 0) {
            log.error("[scenario][error] keyword has not flowcharts");
        }
        return flowcharts;
    }

    private List<Flow> getFlows(List<Flowchart> flowcharts) {
        List<Flow> flows = flowcharts.stream().map(Flowchart::getFlow).collect(Collectors.toList());
        if (flows.size() == 0) {
            log.error("[scenario][error] flowcharts has not flows");
        }
        log.debug("[scenario] flows : " + flows);
        return flows;
    }

    private String getFixedMsg(Keyword keyword){
        // 도입부 안내 메세지
        return """
                shadow가 \'%s\' 을 안내합니다. <br>
                아래 빨간 버튼을 따라 눌러주세요. <br>
                """.formatted(keyword.getName());
    }
    private ResponseEntity<ResultResponse> getMessage(Keyword keyword) {

        List<Flowchart> flowcharts = getFlowcharts(keyword);

        // 전체 flow 응답
        return ResponseEntity.ok(ResultResponse.of("GET_FLOWS_FROM_KEYWORD", getFixedMsg(keyword), flowcharts));
    }

}