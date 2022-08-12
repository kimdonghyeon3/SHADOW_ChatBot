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

    public Member create(String member_name,String member_id, String member_pwd, String member_email, Boolean member_login_status) {
        Member member = new Member();
        member.setMember_name(member_name);
        member.setMember_id(member_id);
        member.setMember_pwd(passwordEncoder.encode(member_pwd));
        member.setMember_email(member_email);
        member.setMember_login_status(member_login_status);
        this.memberRepository.save(member);
        return member;
    }
}