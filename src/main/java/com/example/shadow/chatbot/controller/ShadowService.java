package com.example.shadow.chatbot.controller;

import com.example.shadow.chatbot.test.Test_Keyword;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

    public Test_Keyword findByQuestion(String question) {
        return shadowRepository.findByQuestion(question);
    }
}
