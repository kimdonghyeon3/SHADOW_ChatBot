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
import org.hibernate.LazyInitializationException;
import org.hibernate.TransientPropertyValueException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class  FlowService{
    private final FlowRepository flowRepository;
    private final FlowChartRepository flowChartRepository;
    private final KeywordRepository keywordRepository;
    public void create(List<KeywordDto> keywords, Shadow shadow) {

        for(KeywordDto keyword : keywords){
            List<FlowDto> flows = keyword.getFlow();
            Keyword findkeyword = keywordRepository.findByNameAndShadow(keyword.getName(),shadow);
                for(int i = 0 ; i < flows.size() ; i++){
                    System.out.println("여기가 언제 실행되고 오류가나니?");
                    save(new Flow(),flows.get(i).getName(),flows.get(i).getDescription(),flows.get(i).getUrl(), findkeyword);
                }
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

                if( i < originLength){  //기존 키워드 수정 ( 기존 keyword + flow 추가/ 기존 keyword + flow 삭제)

                    List<Flowchart> originFlowcharts = originKeywords.get(i).getFlowcharts();
                    List<FlowDto> flowcharts = keywords.get(i).getFlow();

                    int originFlowchartsLength = originFlowcharts.size();
                    int flowchartLength = flowcharts.size();

                    Keyword findkeyword = keywordRepository.findByNameAndShadow(keywords.get(i).getName(), originShadow);

                    if(originFlowchartsLength < flowchartLength) {   //flow가 추가되었을 경우
                        for(int j = 0 ; j < flowchartLength ; j++){

                            if( j < originFlowchartsLength){
                                save(originFlowcharts.get(j).getFlow(),flowcharts.get(j).getName(),flowcharts.get(j).getDescription(),flowcharts.get(j).getUrl(), findkeyword);
                                continue;
                            }
                            Flow flow = save(new Flow(),flowcharts.get(j).getName(),flowcharts.get(j).getDescription(),flowcharts.get(j).getUrl(), findkeyword);

                            Flowchart flowchart = save(new Flowchart(),keywordRepository.findByNameAndShadow(keywords.get(i).getName(), originShadow),flow,j+1);
                        }
                    }else if(originFlowchartsLength > flowchartLength){ //flow가 삭제되었을 경우
                        for(int j = 0 ; j < originFlowchartsLength ; j++){

                            Flow flow = originFlowcharts.get(j).getFlow();

                            if( j < flowchartLength){
                                save(flow,flowcharts.get(j).getName(),flowcharts.get(j).getDescription(),flowcharts.get(j).getUrl(), findkeyword);
                                continue;
                            }

                            flowChartRepository.deleteByFlow(flow);
                            flowRepository.delete(flow);
                        }
                    }else{  //flow가 그대로인 경우
                        for(int j = 0 ; j < flowchartLength ; j++){
                            save(originFlowcharts.get(j).getFlow(),flowcharts.get(j).getName(),flowcharts.get(j).getDescription(),flowcharts.get(j).getUrl(), findkeyword);
                        }
                    }
                    continue;
                }
                //추가된 경우 ( 추가 keyword + flow추가)
                for(int j = 0 ; j < keywords.get(i).getFlow().size() ; j++){
                    Flow flow = save(new Flow(),keywords.get(i).getFlow().get(j).getName() ,keywords.get(i).getFlow().get(j).getDescription() ,keywords.get(i).getFlow().get(j).getUrl(), keywordRepository.findByNameAndShadow(keywords.get(i).getName(), originShadow));

                    Flowchart flowchart = save(new Flowchart(),keywordRepository.findByNameAndShadow(keywords.get(i).getName(), originShadow),flow,j+1);
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

                    Keyword findkeyword = keywordRepository.findByNameAndShadow(keywords.get(i).getName(), originShadow);

                    if(originFlowchartsLength < flowchartsLength) {   //flow가 추가되었을 경우
                        for(int j = 0 ; j < flowchartsLength ; j++){

                            if( j < originFlowchartsLength){
                                save(originFlowcharts.get(j).getFlow(),flowcharts.get(j).getName(),flowcharts.get(j).getDescription(),flowcharts.get(j).getUrl(), findkeyword);
                                continue;
                            }
                            Flow flow = save(new Flow(),flowcharts.get(j).getName(),flowcharts.get(j).getDescription(), flowcharts.get(j).getUrl(), findkeyword);

                            Flowchart flowchart = save(new Flowchart(),keywordRepository.findByNameAndShadow(keywords.get(i).getName(), originShadow),flow,j+1);
                        }
                    }else if(originFlowchartsLength > flowchartsLength){ //flow가 삭제되었을 경우
                        for(int j = 0 ; j < originFlowchartsLength ; j++){

                            Flow flow = originFlowcharts.get(j).getFlow();

                            if( j < flowchartsLength){
                                save(flow,flowcharts.get(j).getName(),flowcharts.get(j).getDescription(),flowcharts.get(j).getUrl(), findkeyword);
                                continue;
                            }

                            flowChartRepository.deleteByFlow(flow);
                            flowRepository.delete(flow);
                        }
                    }else{  //flow가 그대로인 경우
                        for(int j = 0 ; j < flowchartsLength ; j++){
                            save(originFlowcharts.get(j).getFlow(),flowcharts.get(j).getName(),flowcharts.get(j).getDescription(),flowcharts.get(j).getUrl(), findkeyword);
                        }
                    }
                    continue;
                }
            }

        }else{                                              //키워드 일정 (키워드 수가 일정할 경우)
            for(int i = 0 ; i < originLength ; i++){  //( 기존 keyword + flow 추가/ 기존 keyword + flow 삭제)
                //걍 모두 순회하면서, 바꿔주면된다.
                List<Flowchart> originFlowcharts = originKeywords.get(i).getFlowcharts();
                List<FlowDto> flowcharts = keywords.get(i).getFlow();

                int originFlowchartsLength = originFlowcharts.size();
                int flowchartsLength = flowcharts.size();

                Keyword findkeyword = keywordRepository.findByNameAndShadow(keywords.get(i).getName(), originShadow);

                if(originFlowchartsLength < flowchartsLength) {   //flow가 추가되었을 경우
                    for(int j = 0 ; j < flowchartsLength ; j++){

                        if( j < originFlowchartsLength){
                            save(originFlowcharts.get(j).getFlow(),flowcharts.get(j).getName(),flowcharts.get(j).getDescription(),flowcharts.get(j).getUrl(), findkeyword);
                            continue;
                        }
                        Flow flow = save(new Flow(),flowcharts.get(j).getName(),flowcharts.get(j).getDescription(),flowcharts.get(j).getUrl(), findkeyword);
                        if(flow.getId() == null){
                            flow = flowRepository.findByName(flow.getName());
                        }

                        Flowchart flowchart  = save(new Flowchart(),keywordRepository.findByNameAndShadow(keywords.get(i).getName(), originShadow),flow,j+1);

                    }
                }else if(originFlowchartsLength > flowchartsLength){  //flow가 삭제되었을 경우
                    for(int j = 0 ; j < originFlowchartsLength ; j++){

                        Flow flow = originFlowcharts.get(j).getFlow();

                        if( j < flowchartsLength){
                            save(flow,flowcharts.get(j).getName(),flowcharts.get(j).getDescription(),flowcharts.get(j).getUrl(), findkeyword);
                            continue;
                        }

                        Flowchart findflowchart = flowChartRepository.findByFlow(flow);
                        flowChartRepository.delete(findflowchart);
                        flowRepository.delete(flow);
                    }
                }else{  //flow가 그대로인 경우
                    for(int j = 0 ; j < flowchartsLength ; j++){
                        save(originFlowcharts.get(j).getFlow(),flowcharts.get(j).getName(),flowcharts.get(j).getDescription(),flowcharts.get(j).getUrl(), findkeyword);
                    }
                }
            }
        }

    }

    private Flow save(Flow flow, String name, String description, String url, Keyword keyword) {
        flow.setName(name);
        flow.setDescription(description);
        flow.setUrl(url);
        flow.setKeyword(keyword.getId());
        try{
           flow=flowRepository.save(flow);
        }catch (DataIntegrityViolationException e){
            log.debug("동일한 flow 입니다. flow를 저장하지 않습니다.");
        }catch (Exception e){
            log.debug("flow 생성 실패");
        }
        return flow;
    }

    private Flowchart save(Flowchart flowchart, Keyword keyword, Flow flow, int seq ){
        flowchart.setKeyword(keyword);
        flowchart.setFlow(flow);
        flowchart.setSeq(seq);
        try{
            flowchart=flowChartRepository.save(flowchart);
        }catch (DataIntegrityViolationException e){
            log.debug("동일한 flowchart 입니다. flowchart를 저장하지 않습니다.");
        }catch (InvalidDataAccessApiUsageException e){
            e.printStackTrace();
            log.debug("Invalid : 기존의 keyword에 해당 flowchart 가 없는데, flowchart 에 keyword를 추가할때 may to one, one to many 충돌");
        }catch (Exception e){
            e.printStackTrace();
            log.debug("flowchart 생성 실패");
        }
        return flowchart;
    }

}

