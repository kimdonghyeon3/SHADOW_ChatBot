package com.example.shadow.chatbot.service;

import com.example.shadow.chatbot.Repository.ShadowRepository;
import com.example.shadow.chatbot.shadow.entity.Shadow;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ShadowService {
    private final ShadowRepository shadowRepository;
    public Shadow findById(long id) {
        return shadowRepository.findById(id).get();
    }

    public Shadow findByMainurl(String url) {
        return shadowRepository.findByMainurl(url).orElseThrow(() -> new RuntimeException("%s 에 해당하는 shadow 가 없습니다.".formatted(url)));
    }
}
