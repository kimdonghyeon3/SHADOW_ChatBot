package com.example.shadow.chatbot.service;


import com.example.shadow.chatbot.Repository.FlowChartRepository;
import com.example.shadow.chatbot.shadow.entity.Flowchart;
import com.example.shadow.chatbot.shadow.entity.Keyword;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FlowChartService {

    private final FlowChartRepository flowChartRepository;


    public Flowchart findByKeyword(Keyword keyword) {
        return flowChartRepository.findTopByKeywordOrderBySeqDesc(keyword);
    }
}
