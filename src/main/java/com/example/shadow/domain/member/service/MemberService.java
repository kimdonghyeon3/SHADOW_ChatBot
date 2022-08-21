package com.example.shadow.domain.member.service;

import com.example.shadow.domain.member.entity.Member;
import com.example.shadow.domain.member.repository.MemberRepository;
import com.example.shadow.global.exception.SignupEmailDuplicatedException;
import com.example.shadow.global.exception.SignupUsernameDuplicatedException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public void create(String username,String password, String name,  String email) {
        Member member = new Member(username, passwordEncoder.encode(password), name, email);
        memberRepository.save(member);
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

    public void update(Member member, String password, String name, String email ) throws SignupUsernameDuplicatedException, SignupEmailDuplicatedException {
        member.setEncryptedPassword(passwordEncoder.encode(password));
        member.updateName(name);
        member.updateEmail(email);
        memberRepository.save(member);
    }
    public boolean checkUsername(String username) {
        return memberRepository.existsByUsername(username);
    }
    public boolean checkEmail(String email) {
        return memberRepository.existsByEmail(email);
    }

}