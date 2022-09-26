package com.example.shadow.domain.shadow.service;


import com.example.shadow.domain.member.entity.Member;
import com.example.shadow.domain.shadow.dto.ShadowDto;
import com.example.shadow.domain.shadow.entity.Shadow;
import com.example.shadow.domain.shadow.repository.ShadowRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ShadowService {

    private final ShadowRepository shadowRepository;

    public void create(String name, String mainurl, Member member) {
        Shadow shadow = new Shadow();
        shadow.setName(name);
        shadow.setMainurl(mainurl);
        shadow.setMember(member);
        shadow.setApicall(0L);
        shadow.setDbcall(0L);
        String api_key = UUID.randomUUID().toString().replace("-","");
        log.debug("api_key : "+ api_key);
        shadow.setApiKey(api_key);
        shadowRepository.save(shadow);
    }

    public Shadow findByName(String name) {
        return shadowRepository.findByName(name);
    }

    public Shadow findByNameAndMember(String name, Member member) {
        return shadowRepository.findByNameAndMember(name, member);
    }
    public List<Shadow> findAll(){
        return this.shadowRepository.findAll();
    }

    public Shadow findById(Long id) {
        return shadowRepository.findById(id).orElseThrow(
                () -> new RuntimeException("shadow not found exception") // 추후 data not found exception 추가 필요
        );
    }

    public void update(Shadow originShadow, ShadowDto shadowDto) {
        // 기존 SHADOW에다가, 새로 받은 내용을 넣어주자.
        originShadow.setName(shadowDto.getName());
        originShadow.setMainurl(shadowDto.getMainurl());

        shadowRepository.save(originShadow);
    }

    public void delete(Shadow shadow) {
        shadowRepository.delete(shadow);
    }
    public Shadow findByMainurl(String url) {
        return shadowRepository.findByMainurl(url).orElseThrow(() -> new RuntimeException("%s 에 해당하는 shadow 가 없습니다.".formatted(url)));
    }

    public Shadow findByApiKey(String apiKey) {
        return shadowRepository.findByApiKey(apiKey);
    }

    public List<Shadow> findByMember(Member member) {
        return shadowRepository.findByMember(member);
    }

    public void updateApiKey(Shadow originShadow, String new_api_key) {
        originShadow.setApiKey(new_api_key);

        shadowRepository.save(originShadow);
    }

    public void save(Shadow shadow) {
        shadowRepository.save(shadow);
    }

    public List<Shadow> findByMemberId(long id) {
        return shadowRepository.findByMemberId(id);
    }
}
