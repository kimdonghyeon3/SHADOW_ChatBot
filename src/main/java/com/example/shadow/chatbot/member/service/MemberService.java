package com.example.shadow.chatbot.member.service;


import com.example.shadow.chatbot.member.entity.Member;
import com.example.shadow.chatbot.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class MemberService {

    private final MemberRepository memberRepository;


    public Member findById(long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("유효하지 않은 사용자 입니다."));
    }
}
