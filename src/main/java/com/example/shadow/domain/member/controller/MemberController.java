package com.example.shadow.domain.member.controller;

import com.example.shadow.domain.member.entity.Member;
import com.example.shadow.web.MemberCreateForm;
import com.example.shadow.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.security.Principal;


@RequiredArgsConstructor
@Controller
@RequestMapping("/")
public class MemberController {

    private final MemberService memberService;
    private PasswordEncoder passwordEncoder;
    @GetMapping("/signup")
    public String signup(MemberCreateForm memberCreateForm) {
        return "signup_form";
    }

    @PostMapping("/signup")
    public String signup(@Valid MemberCreateForm memberCreateForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "signup_form";
        }

        System.out.println("pw1 : " + memberCreateForm.getPassword1() + " pw2 : " + memberCreateForm.getPassword2());
        if (!memberCreateForm.getPassword1().equals(memberCreateForm.getPassword2())) {
            bindingResult.rejectValue("memberPwd2", "passwordInCorrect",
                    "2개의 패스워드가 일치하지 않습니다.");
            return "signup_form";
        }

        try {
            memberService.create(
                    memberCreateForm.getName(),
                    memberCreateForm.getUsername(),
                    memberCreateForm.getPassword1(),
                    memberCreateForm.getEmail());
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
    public String login(MemberCreateForm memberCreateForm) {
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
    public String modify(@PathVariable("id") Long id, Model model, MemberCreateForm memberCreateForm) {
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