package com.example.shadow.domain.shadow.service;


import com.example.shadow.domain.member.entity.Member;
import com.example.shadow.domain.shadow.entity.Shadow;
import com.example.shadow.domain.shadow.repository.ShadowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShadowService {

    private final ShadowRepository shadowRepository;

    public void create(String name, String mainurl, Member member) {
        Shadow shadow = new Shadow();
        shadow.setName(name);
        shadow.setMainurl(mainurl);
        shadow.setMember(member);

        shadowRepository.save(shadow);
    }

    public Shadow findByName(String name) {
        return shadowRepository.findByName(name);
    }

    public Shadow findByNameAndMember(String name, Member member) {
        return shadowRepository.findByNameAndMember(name, member);
    }
}
