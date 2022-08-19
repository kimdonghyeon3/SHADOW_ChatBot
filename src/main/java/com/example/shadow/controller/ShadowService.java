package com.example.shadow.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ShadowService {
    private final ShadowRepository shadowRepository;
    public String makeSignature(String message, String secretKey) {
        return shadowRepository.makeSignature(message, secretKey);
    }

    public String getReqMessage(String chatMessage) {
        return shadowRepository.getReqMessage(chatMessage);
    }
}
