package com.example.shadow.domain.member.service;

import com.example.shadow.domain.member.entity.Member;
import com.example.shadow.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public Member create(String name, String username, String password, String email) {
        Member member = new Member();
        member.updateName(name);
        member.updateUsername(username);
        member.setEncryptedPassword(passwordEncoder.encode(password));
        member.updateEmail(email);
        this.memberRepository.save(member);
        return member;
    }

    public Member findByUsername(String username) {
        return memberRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));
    }
    public Member findById(long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("유효하지 않은 사용자 입니다."));
    }

    @Transactional
    public void delete(long id) {
        memberRepository.delete(findById(id));
    }

    public void update(Member orgMember, Member newMember) {
        orgMember.updateName(newMember.getName());
        orgMember.updateEmail(newMember.getEmail());
        orgMember.setEncryptedPassword(newMember.getPassword());
    }
}