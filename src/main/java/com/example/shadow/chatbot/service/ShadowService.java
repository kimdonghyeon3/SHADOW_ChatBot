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
}
