package com.example.shadow.domain.shadow.service;

import com.example.shadow.domain.shadow.Repository.ShadowRepository;
import com.example.shadow.domain.shadow.entity.Shadow;
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
