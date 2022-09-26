package com.example.shadow.domain.shadow.service;


import com.example.shadow.domain.shadow.dto.KeywordDto;
import com.example.shadow.domain.shadow.dto.ShadowDto;
import com.example.shadow.domain.shadow.entity.Flowchart;
import com.example.shadow.domain.shadow.entity.Keyword;
import com.example.shadow.domain.shadow.entity.Shadow;
import com.example.shadow.domain.shadow.repository.FlowChartRepository;
import com.example.shadow.domain.shadow.repository.FlowRepository;
import com.example.shadow.domain.shadow.repository.KeywordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class KeywordService {
    private final KeywordRepository keywordRepository;
    private final FlowChartRepository flowChartRepository;
    private final FlowRepository flowRepository;

    public void create(List<KeywordDto> keywords, Shadow shadow) {

        for(KeywordDto key : keywords){
            Keyword keyword = new Keyword();
            keyword.setName(key.getName());
            keyword.setFavorite(key.getFavorite());
            keyword.setShadow(shadow);
            keywordRepository.save(keyword);
        }
    }
    public void update(Shadow originShadow, ShadowDto shadowDto) {

        List<Keyword> originKeywords = originShadow.getKeywords();  //기존 키워드
        List<KeywordDto> keywords = shadowDto.getKeyword();         //수정된 키워드

        int originLength = originKeywords.size();
        int length = keywords.size();

        if( originLength < length){       //키워드 추가 (수정된 키워드가 더 많은 경우)
            //기존 키워드에 새로운 키워드를 추가 (단, 기존 키워드 수정되었다며면, 수정작업도해줘야됨)
            for(int i = 0 ; i < length ; i++){

                if( i < originLength){  //기존 키워드 수정
                    Keyword originKeyword = originKeywords.get(i);
                    originKeyword.setName(keywords.get(i).getName());
                    originKeyword.setFavorite(keywords.get(i).getFavorite());
                    keywordRepository.save(originKeyword);
                    continue;
                }
                //추가된 경우
                Keyword newKeyword = new Keyword();
                newKeyword.setName(keywords.get(i).getName());
                newKeyword.setFavorite(keywords.get(i).getFavorite());
                newKeyword.setShadow(originShadow);
                keywordRepository.save(newKeyword);
            }

        }else if(originLength > length){  //키워드 삭제 (기존 키워드가 더 많은 경우)
            //수정 키워드에 삭제된 키워드를 원본 키워드에 적용시켜야됨 (단, 기존 삭제되지 않은 키워드 수정되었다며면, 수정작업도해줘야됨)
            for(int i = 0 ; i < originLength ; i++){

                if( i < length){    //삭제되지 않은 기존 키워드 수정
                    Keyword originKeyword = originKeywords.get(i);
                    originKeyword.setName(keywords.get(i).getName());
                    originKeyword.setFavorite(keywords.get(i).getFavorite());
                    keywordRepository.save(originKeyword);
                    continue;
                }
                //삭제된 경우
                Keyword originKeyword = originKeywords.get(i);
                List<Flowchart> flowcharts = flowChartRepository.findByKeyword(originKeyword);

                for (Flowchart flowchart : flowcharts) {
                    flowChartRepository.delete(flowchart);
                    flowRepository.delete(flowchart.getFlow());
                }

                keywordRepository.delete(originKeyword);
            }

        }else{                                              //키워드 일정 (키워드 수가 일정할 경우)
            for(int i = 0 ; i < originLength ; i++){
                //걍 모두 순회하면서, 바꿔주면된다.
                Keyword originKeyword = originKeywords.get(i);
                originKeyword.setName(keywords.get(i).getName());
                originKeyword.setFavorite(keywords.get(i).getFavorite());
                keywordRepository.save(originKeyword);
            }
        }
    }

    public void delete(Shadow shadow) {
        keywordRepository.findByShadow(shadow)
                .forEach(
                        keyword -> {
                            keywordRepository.delete(keyword);
                            flowChartRepository.findByKeyword(keyword)
                                    .forEach(
                                            flowchart -> {
                                                flowRepository.findByFlowcharts(flowchart)
                                                        .forEach(flow -> flowRepository.delete(flow));
                                                flowChartRepository.delete(flowchart);
                                            }
                                    );
                        });
    }
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
       // return keywordRepository.findByNameAndShadow(keyword,shadow).orElseThrow(()->new RuntimeException("%s keyword는 없습니다.".formatted(keyword)));
        return keywordRepository.findByNameAndShadow(keyword,shadow);
    }
}
