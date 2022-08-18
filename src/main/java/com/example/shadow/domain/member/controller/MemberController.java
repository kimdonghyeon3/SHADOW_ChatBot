package com.example.shadow.domain.member.controller;

import com.example.shadow.domain.member.dto.MemberDto;
import com.example.shadow.domain.member.entity.Member;
import com.example.shadow.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;


@RequiredArgsConstructor
@Controller
@RequestMapping("/")
public class MemberController {

    private final MemberService memberService;
    private PasswordEncoder passwordEncoder;
    @GetMapping("/signup")
    public String signup(MemberDto memberDto) {
        return "signup_form";
    }

    @PostMapping("/signup")
    public String signup(@Valid MemberDto memberDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "signup_form";
        }

        System.out.println("pw1 : " + memberDto.getPassword1() + " pw2 : " + memberDto.getPassword2());
        if (!memberDto.getPassword1().equals(memberDto.getPassword2())) {
            bindingResult.rejectValue("memberPwd2", "passwordInCorrect",
                    "2개의 패스워드가 일치하지 않습니다.");
            return "signup_form";
        }

        try {
            memberService.create(
                    memberDto.getName(),
                    memberDto.getUsername(),
                    memberDto.getPassword1(),
                    memberDto.getEmail());
        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
            bindingResult.reject("signupFailed", "이미 등록된 사용자입니다.");
            return "signup_form";
        } catch (Exception e) {
            e.printStackTrace();
            bindingResult.reject("signupFailed", e.getMessage());
            return "signup_form";
        }
        return "redirect:/";
    }

    @GetMapping("/login")
    public String login(MemberDto memberDto) {
        return "login_form";
    }

    @GetMapping("/members")
    public String detail(Model model, Principal principal){
        Member member = memberService.findByUsername(principal.getName());
        model.addAttribute("member",member);
        return "member_detail";
    }

    @DeleteMapping("/members/{id}")
    public String delete(@PathVariable long id){
        memberService.delete(id);
        return "redirect:/logout";
    }
    @GetMapping("/members/{id}")
    public String modify(@PathVariable("id") Long id, Model model, MemberDto memberDto) {
        Member member = memberService.findById(id);
        model.addAttribute("member", member);
        return "member_modify";
    }
    @PutMapping("/members/{id}")
    public String update(@PathVariable Long id, Member newMember) {
        Member orgMember = memberService.findById(id);
        memberService.update(orgMember,newMember);
        return "redirect:/";
    }

}