package com.example.shadow.domain.shadow.service;


import com.example.shadow.domain.shadow.dto.FlowDto;
import com.example.shadow.domain.shadow.dto.KeywordDto;
import com.example.shadow.domain.shadow.entity.Flow;
import com.example.shadow.domain.shadow.entity.Keyword;
import com.example.shadow.domain.shadow.repository.FlowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FlowService {

    private final FlowRepository flowRepository;

    public void create(List<KeywordDto> keywords) {

        for(KeywordDto keyword : keywords){

            List<FlowDto> flows = keyword.getFlow();

            for(int i = 0 ; i < flows.size() ; i++){
                Flow flow = new Flow();
                flow.setName(flows.get(i).getName());
                flow.setDescription(flows.get(i).getDescription());
                flow.setUrl(flows.get(i).getUrl());
                flowRepository.save(flow);
            }
        }

    }
}
