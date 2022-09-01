package com.example.shadow.domain.shadow.service;

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
import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class  FlowService{
    private final FlowRepository flowRepository;
    private final FlowChartRepository flowChartRepository;
    private final KeywordRepository keywordRepository;
    public void create(List<KeywordDto> keywords) {

        for(KeywordDto keyword : keywords){
            List<FlowDto> flows = keyword.getFlow();
                for(int i = 0 ; i < flows.size() ; i++){
                    save(new Flow(),flows.get(i).getName(),flows.get(i).getDescription(),flows.get(i).getUrl());
                }
            }
        }

    public void update(Shadow originShadow, ShadowDto shadowDto) {

//        log.info("SHADOW name = {}, SHADOW mainurl = {}", originShadow.getName(), originShadow.getMainurl());
//        for(Keyword k : originShadow.getKeywords()){
//            log.info("keyword = {}", k.getName());
//            List<Flowchart> flowCharts = k.getFlowcharts();
//            if(flowCharts == null){
//                log.info("아직 flowchart가 만들어지지 않았습니다. 새로생긴 keyword란 말이지");
//                continue;
//            }
//            for (Flowchart flowChart : flowCharts) {
//                log.info("flow name = {}, flow Description = {}, flow URL = {}", flowChart.getFlow().getName(), flowChart.getFlow().getDescription(), flowChart.getFlow().getUrl());
//            }
//            log.info("favorite = {}",k.getFavorite());
//        }

        List<Keyword> originKeywords = originShadow.getKeywords();  //기존 키워드
        List<KeywordDto> keywords = shadowDto.getKeyword();         //수정된 키워드

        int originLength = originKeywords.size();
        int length = keywords.size();

        if( originLength < length){       //키워드 추가 (수정된 키워드가 더 많은 경우)
            //기존 키워드에 새로운 키워드를 추가 (단, 기존 키워드 수정되었다며면, 수정작업도해줘야됨)
            for(int i = 0 ; i < length ; i++){

                if( i < originLength){  //기존 키워드 수정 ( 기존 keyword + flow 추가/ 기존 keyword + flow 삭제)

                    List<Flowchart> originFlowcharts = originKeywords.get(i).getFlowcharts();
                    List<FlowDto> flowcharts = keywords.get(i).getFlow();

                    int originFlowchartsLength = originFlowcharts.size();
                    int flowchartLength = flowcharts.size();

                    if(originFlowchartsLength < flowchartLength) {   //flow가 추가되었을 경우
                        for(int j = 0 ; j < flowchartLength ; j++){

                            if( j < originFlowchartsLength){
                                save(originFlowcharts.get(j).getFlow(),flowcharts.get(j).getName(),flowcharts.get(j).getDescription(),flowcharts.get(j).getUrl());
                                continue;
                            }
                            Flow flow = save(new Flow(),flowcharts.get(j).getName(),flowcharts.get(j).getDescription(),flowcharts.get(j).getUrl());

                            //따로 flowchart를 만들면,, flow를 찾아올 방법이 없다.
                            //고유한 id로 찾아야하는데, 가져올 방법이없음..
                            Flowchart flowchart = new Flowchart();
                            Keyword newKeyword = keywordRepository.findByNameAndShadow(keywords.get(i).getName(), originShadow);
                            flowchart.setKeyword(newKeyword);
                            flowchart.setFlow(flow);
                            flowchart.setSeq(j+1);
                            flowChartRepository.save(flowchart);

                        }
                    }else if(originFlowchartsLength > flowchartLength){ //flow가 삭제되었을 경우
                        for(int j = 0 ; j < originFlowchartsLength ; j++){

                            Flow flow = originFlowcharts.get(j).getFlow();

                            if( j < flowchartLength){
                                save(flow,flowcharts.get(j).getName(),flowcharts.get(j).getDescription(),flowcharts.get(j).getUrl());
                                continue;
                            }

                            flowRepository.delete(flow);
                        }
                    }else{  //flow가 그대로인 경우
                        for(int j = 0 ; j < flowchartLength ; j++){
                            save(originFlowcharts.get(j).getFlow(),flowcharts.get(j).getName(),flowcharts.get(j).getDescription(),flowcharts.get(j).getUrl());
                        }
                    }
                    continue;
                }
                //추가된 경우 ( 추가 keyword + flow추가)
                for(int j = 0 ; j < keywords.get(i).getFlow().size() ; j++){
                    Flow flow = save(new Flow(),keywords.get(i).getFlow().get(j).getName() ,keywords.get(i).getFlow().get(j).getDescription() ,keywords.get(i).getFlow().get(j).getUrl());

                    //따로 flowchart를 만들면,, flow를 찾아올 방법이 없다.
                    //고유한 id로 찾아야하는데, 가져올 방법이없음..
                    Flowchart flowchart = new Flowchart();
                    Keyword newKeyword = keywordRepository.findByNameAndShadow(keywords.get(i).getName(), originShadow);
                    flowchart.setKeyword(newKeyword);
                    flowchart.setFlow(flow);
                    flowchart.setSeq(j+1);
                    flowChartRepository.save(flowchart);
                }
            }

        }else if(originLength > length){  //키워드 삭제 (기존 키워드가 더 많은 경우)
            //수정 키워드에 삭제된 키워드를 원본 키워드에 적용시켜야됨 (단, 기존 삭제되지 않은 키워드 수정되었다며면, 수정작업도해줘야됨)
            for(int i = 0 ; i < originLength ; i++){

                if( i < length){    //삭제되지 않은 기존 키워드 수정 ( 기존 keyword + flow 추가/ 기존 keyword + flow 삭제)

                    List<Flowchart> originFlowcharts = originKeywords.get(i).getFlowcharts();
                    List<FlowDto> flowcharts = keywords.get(i).getFlow();

                    int originFlowchartsLength = originFlowcharts.size();
                    int flowchartsLength = flowcharts.size();

                    if(originFlowchartsLength < flowchartsLength) {   //flow가 추가되었을 경우
                        for(int j = 0 ; j < flowchartsLength ; j++){

                            if( j < originFlowchartsLength){
                                save(originFlowcharts.get(j).getFlow(),flowcharts.get(j).getName(),flowcharts.get(j).getDescription(),flowcharts.get(j).getUrl());
                                continue;
                            }
                            Flow flow = save(new Flow(),flowcharts.get(j).getName(),flowcharts.get(j).getDescription(), flowcharts.get(j).getUrl());

                            //따로 flowchart를 만들면,, flow를 찾아올 방법이 없다.
                            //고유한 id로 찾아야하는데, 가져올 방법이없음..
                            Flowchart flowchart = new Flowchart();
                            Keyword newKeyword = keywordRepository.findByNameAndShadow(keywords.get(i).getName(), originShadow);
                            flowchart.setKeyword(newKeyword);
                            flowchart.setFlow(flow);
                            flowchart.setSeq(j+1);
                            flowChartRepository.save(flowchart);

                        }
                    }else if(originFlowchartsLength > flowchartsLength){ //flow가 삭제되었을 경우
                        for(int j = 0 ; j < originFlowchartsLength ; j++){

                            Flow flow = originFlowcharts.get(j).getFlow();

                            if( j < flowchartsLength){
                                save(flow,flowcharts.get(j).getName(),flowcharts.get(j).getDescription(),flowcharts.get(j).getUrl());
                                continue;
                            }

                            flowRepository.delete(flow);
                        }
                    }else{  //flow가 그대로인 경우
                        for(int j = 0 ; j < flowchartsLength ; j++){
                            save(originFlowcharts.get(j).getFlow(),flowcharts.get(j).getName(),flowcharts.get(j).getDescription(),flowcharts.get(j).getUrl());
                        }
                    }
                    continue;
                }
                //삭제된 경우( 삭제 keyword + flow추가)
//                Keyword originKeyword = originKeywords.get(i);
//                keywordRepository.delete(originKeyword);
                //아 여기서 없애줘야된다.
                //삭제되어서 쓰레기 정보가 된다면, 해당 정보를 지워줘야 된다.

            }

        }else{                                              //키워드 일정 (키워드 수가 일정할 경우)
            for(int i = 0 ; i < originLength ; i++){  //( 기존 keyword + flow 추가/ 기존 keyword + flow 삭제)
                //걍 모두 순회하면서, 바꿔주면된다.
                List<Flowchart> originFlowcharts = originKeywords.get(i).getFlowcharts();
                List<FlowDto> flowcharts = keywords.get(i).getFlow();

                int originFlowchartsLength = originFlowcharts.size();
                int flowchartsLength = flowcharts.size();

                if(originFlowchartsLength < flowchartsLength) {   //flow가 추가되었을 경우
                    for(int j = 0 ; j < flowchartsLength ; j++){

                        if( j < originFlowchartsLength){
                            save(originFlowcharts.get(j).getFlow(),flowcharts.get(j).getName(),flowcharts.get(j).getDescription(),flowcharts.get(j).getUrl());
                            continue;
                        }
                        Flow flow = save(new Flow(),flowcharts.get(j).getName(),flowcharts.get(j).getDescription(),flowcharts.get(j).getUrl());

                        //따로 flowchart를 만들면,, flow를 찾아올 방법이 없다.
                        //고유한 id로 찾아야하는데, 가져올 방법이없음..
                        Flowchart flowchart = new Flowchart();
                        Keyword newKeyword = keywordRepository.findByNameAndShadow(keywords.get(i).getName(), originShadow);
                        flowchart.setKeyword(newKeyword);
                        flowchart.setFlow(flow);
                        flowchart.setSeq(j+1);
                        flowChartRepository.save(flowchart);

                    }
                }else if(originFlowchartsLength > flowchartsLength){  //flow가 삭제되었을 경우
                    for(int j = 0 ; j < originFlowchartsLength ; j++){

                        Flow flow = originFlowcharts.get(j).getFlow();

                        if( j < flowchartsLength){
                            save(flow,flowcharts.get(j).getName(),flowcharts.get(j).getDescription(),flowcharts.get(j).getUrl());
                            continue;
                        }

                        flowRepository.delete(flow);
                    }
                }else{  //flow가 그대로인 경우
                    for(int j = 0 ; j < flowchartsLength ; j++){
                        save(originFlowcharts.get(j).getFlow(),flowcharts.get(j).getName(),flowcharts.get(j).getDescription(),flowcharts.get(j).getUrl());
                    }
                }
//                Keyword originKeyword = originKeywords.get(i);
//                originKeyword.setName(keywords.get(i).getName());
//                originKeyword.setFavorite(keywords.get(i).getFavorite());
//                keywordRepository.save(originKeyword);
            }
        }

    }

    private Flow save(Flow flow, String name, String description, String url) {
        flow.setName(name);
        flow.setDescription(description);
        flow.setUrl(url);
        try{
           flow=flowRepository.save(flow);
        }catch (DataIntegrityViolationException e){
            log.debug("동일한 flow 입니다. flow를 저장하지 않습니다.");
        }catch (Exception e){
            log.debug("flow 생성 실패");
        }
        return flow;
    }

}

