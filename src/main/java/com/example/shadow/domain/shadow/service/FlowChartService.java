package com.example.shadow.domain.shadow.service;


import com.example.shadow.domain.shadow.Repository.FlowChartRepository;
import com.example.shadow.domain.shadow.entity.Flowchart;
import com.example.shadow.domain.shadow.entity.Keyword;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FlowChartService {

    private final FlowChartRepository flowChartRepository;


    public Flowchart findByKeyword(Keyword keyword) {
        return flowChartRepository.findTopByKeywordOrderBySeqDesc(keyword);
    }

    public Flowchart findById(Long id) {
        return flowChartRepository.findById(id).orElseThrow(() -> new RuntimeException("flowchart not found"));
    }
}
