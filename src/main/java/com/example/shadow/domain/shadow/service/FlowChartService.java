package com.example.shadow.domain.shadow.service;


import com.example.shadow.domain.member.entity.Member;
import com.example.shadow.domain.shadow.dto.FlowDto;
import com.example.shadow.domain.shadow.dto.KeywordDto;
import com.example.shadow.domain.shadow.dto.ShadowDto;
import com.example.shadow.domain.shadow.entity.Flow;
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
public class FlowChartService {

    private final FlowChartRepository flowChartRepository;
    private final KeywordRepository keywordRepository;
    private final FlowRepository flowRepository;

    public void create(List<KeywordDto> keywords, Shadow shadow) {

        for (KeywordDto k : keywords) {
            Keyword keyword = keywordRepository.findByNameAndShadow(k.getName(), shadow);

            for(int i = 1 ; i <= k.getFlow().size(); i ++){

                FlowDto f = k.getFlow().get(i-1);

                Flow flow = flowRepository.findByNameAndKeyword(f.getName(), keyword.getId());
                Flowchart flowchart = new Flowchart();
                flowchart.setKeyword(keyword);
                flowchart.setFlow(flow);
                flowchart.setSeq(i);

                flowChartRepository.save(flowchart);
            }
        }
    }

    public void update(Shadow originShadow, ShadowDto shadowDto) {

    }

    public Flowchart findByKeyword(Keyword keyword) {
        return flowChartRepository.findTopByKeywordOrderBySeqDesc(keyword);
    }

    public Flowchart findById(Long id) {
        return flowChartRepository.findById(id).orElseThrow(() -> new RuntimeException("flowchart not found"));
    }

}
