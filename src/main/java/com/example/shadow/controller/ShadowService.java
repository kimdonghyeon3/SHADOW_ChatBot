package com.example.shadow.controller;

import com.example.shadow.test.Test_Keyword;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.codec.binary.Base64;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;

@RequiredArgsConstructor
@Service
public class ShadowService {
    private final ShadowRepository shadowRepository;

    public void create(String question, String respMessage) {
        Test_Keyword t = new Test_Keyword();
        t.setQuestion(question);
        t.setKeyword(respMessage);
        shadowRepository.save(t);
    }

    public boolean existByQuestion(String question) {
        return shadowRepository.existsByQuestion(question);
    }
}
