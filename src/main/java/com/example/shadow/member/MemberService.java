package com.example.shadow.member;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public Member create(String member_name, String member_email, String member_pwd, Boolean login_status) {
        Member member = new Member();
        member.setMember_name(member_name);
        member.setMember_pwd(passwordEncoder.encode(member_pwd));
        member.setMember_email(member_email);
        member.setLogin_status(login_status);
        this.memberRepository.save(member);
        return member;
    }
}