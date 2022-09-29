package com.example.shadow.domain.member.service;

import com.example.shadow.domain.member.entity.Member;
import com.example.shadow.domain.member.repository.MemberRepository;
import com.example.shadow.global.exception.SignupEmailDuplicatedException;
import com.example.shadow.global.exception.SignupUsernameDuplicatedException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@RequiredArgsConstructor
@Service
@Slf4j
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JavaMailSender emailSender;

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

    public void update(Member member, String password, String name, String email ) {
        if(!member.getPassword().equals(password)){
            log.debug("패스워드 다름 : 변경 : "+ password + " 기존 : "+ member.getPassword());
            member.setEncryptedPassword(passwordEncoder.encode(password));
        }
        if(!member.getName().equals(name)){
            log.debug("기존 : "+ member.getName() +"변경 : "+ name);
            member.updateName(name);
        }
        if(!member.getEmail().equals(email)){
            log.debug("기존 : "+ member.getEmail() +"변경 : "+ email);
            member.updateEmail(email);
        }
        memberRepository.save(member);
    }
    public void update(Member member, String password ) {
        if(!member.getPassword().equals(password)){
            log.debug("패스워드 다름 : 변경 : "+ password + " 기존 : "+ member.getPassword());
            member.setEncryptedPassword(passwordEncoder.encode(password));
        }
        memberRepository.save(member);
    }
    public boolean checkUsername(String username) {
        return memberRepository.existsByUsername(username);
    }
    public boolean checkEmail(String email) {
        return memberRepository.existsByEmail(email);
    }

    public void login(String username, String password) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
        SecurityContextHolder.getContext().setAuthentication(token);
    }

    public String sendSimpleMessage(Member member) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("a2346532@gmail.com");
        message.setTo(member.getEmail());
        String code = UUID.randomUUID().toString().replace("-","");
        String text = "인증코드 : " + code;
        log.debug("msg text : "+text);
        message.setSubject("%s 님의 비밀번호 인증코드 입니다.".formatted(member.getName()));
        message.setText(text);
        emailSender.send(message);
        return code;
    }

    public Member findByEmail(String email) {
        return memberRepository.findByEmail(email);
    }
}