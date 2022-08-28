package com.example.shadow.domain.shadow.service;


import com.example.shadow.domain.shadow.dto.KeywordDto;
import com.example.shadow.domain.shadow.entity.Keyword;
import com.example.shadow.domain.shadow.entity.Shadow;
import com.example.shadow.domain.shadow.repository.KeywordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class KeywordService {

    private final KeywordRepository keywordRepository;

    public void create(List<KeywordDto> kewords, Shadow shadow) {

        for(KeywordDto key : kewords){
            Keyword keyword = new Keyword();
            keyword.setName(key.getName());
            keyword.setFavorite(key.getFavorite());
            keyword.setShadow(shadow);
            keywordRepository.save(keyword);
        }
    }

}
