package com.example.shadow.chatbot.controller;

import com.example.shadow.chatbot.message.RequestMessage;
import com.example.shadow.chatbot.message.ResponseMessage;
import com.example.shadow.chatbot.test.Test_Keyword;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

@RequiredArgsConstructor
@Controller
@Slf4j
public class ShadowController {
    private final ShadowService shadowService;
    private static String secretKey = "SHVTeG5SemVXRk9KcU1oSU1VVWpWeW1MQmxCY0xzSk4=";
    private static String apiUrl = "https://z16j1lin9x.apigw.ntruss.com/custom/v1/7654/bb5bef27a0dd572b921c6b22c71e79115c1d4cca1dcbd766d269fa6c2d5bd9ad";


    @RequestMapping("/chat")
    public String chatGET(){

        log.info("제대로 실행되니?");

        return "chatbot/chat";
    }


    //@MessageMapping("/sendMessage")
    @RequestMapping("/chat/write")
// 우리가 구독하고 있는 /topic에서 메시지를 보낼 곳으로 이동시킨다. 우리의 prefix는 /shadow이다.(/shadow -> /topic -> CLOVA로 보내기)
    @SendTo("/topic/shadow")
    @ResponseBody
    public ResponseMessage sendMessage(String chatMessage) throws IOException { // @Payload는 websocket에서 요청할 메시지의 meta 데이터
        String reqMessage = chatMessage;
        reqMessage = reqMessage.replace("\"", "");

        ResponseMessage responseMessage = new ResponseMessage();

        if (shadowService.existByQuestion(reqMessage)) {
            Test_Keyword keywords = shadowService.findByQuestion(reqMessage);
            responseMessage.setMessage(keywords.getKeyword());
            //String keyword = keywords.getKeyword();
            return responseMessage;
        } else {
            URL url = new URL(apiUrl);

            String message = getReqMessage(chatMessage);
            String encodeBase64String = makeSignature(message, secretKey);

            //api서버 접속 (서버 -> 서버 통신)
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
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

            if(responseCode==200) { // 정상 호출
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
                String decodedString;
                String jsonString = "";
                while ((decodedString = in.readLine()) != null) {
                    jsonString = decodedString;
                }

                //받아온 값을 세팅하는 부분
                JSONParser jsonparser = new JSONParser();
                try {
                    JSONObject json = (JSONObject)jsonparser.parse(jsonString);
                    JSONArray bubblesArray = (JSONArray)json.get("bubbles");
                    JSONObject bubbles = (JSONObject)bubblesArray.get(0);
                    JSONObject data = (JSONObject)bubbles.get("data");
                    String description = "";
                    description = (String)data.get("description");
                    chatMessage = description;
                    String respMessage = chatMessage;

                    if(!shadowService.existByQuestion(reqMessage)) { // DB에 저장이 안되어 있을 경우
                        // DB저장
                        create(reqMessage, respMessage);
                    }
                } catch (Exception e) {
                    System.out.println("error");
                    e.printStackTrace();
                }
                in.close();
            } else {  // 에러 발생
                chatMessage = con.getResponseMessage();
            }

            responseMessage.setMessage(chatMessage);

            return responseMessage;
        }
    }

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

    public void create(String question, String keyword) {
        shadowService.create(question, keyword);
    }
}