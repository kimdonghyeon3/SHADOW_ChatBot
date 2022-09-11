package com.example.shadow.chatbot.service;


import com.example.shadow.chatbot.Repository.KeywordRepository;
import com.example.shadow.chatbot.shadow.entity.Keyword;
import com.example.shadow.chatbot.shadow.entity.Shadow;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class KeywordService {

    private final KeywordRepository keywordRepository;
    public List<Keyword> findByShadow(Shadow shadow) {
        return keywordRepository.findByShadow(shadow);
    }

    public List<Keyword> findByShadowAndFavorite(Shadow shadow) {
        return keywordRepository.findByShadowAndFavorite(shadow, true);
    }
    public Keyword findByName(String keyword){
        return keywordRepository.findByName(keyword).orElseThrow(()->new RuntimeException("%s keyword는 없습니다.".formatted(keyword)));
    }

    public Keyword findByNameAndShadow(String keyword, Shadow shadow) {
        return keywordRepository.findByNameAndShadow(keyword,shadow).orElseThrow(()->new RuntimeException("%s keyword는 없습니다.".formatted(keyword)));
    }
}
