package com.example.shadow.domain.shadow.service;


import com.example.shadow.domain.member.entity.Member;
import com.example.shadow.domain.shadow.dto.ShadowDto;
import com.example.shadow.domain.shadow.entity.Shadow;
import com.example.shadow.domain.shadow.repository.ShadowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ShadowService {

    private final ShadowRepository shadowRepository;

    public void create(String name, String mainurl, Member member) {
        Shadow shadow = new Shadow();
        shadow.setName(name);
        shadow.setMainurl(mainurl);
        shadow.setMember(member);
        String api_key = UUID.randomUUID().toString().replace("-","");
        shadow.setAPIKey(api_key);
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
}
