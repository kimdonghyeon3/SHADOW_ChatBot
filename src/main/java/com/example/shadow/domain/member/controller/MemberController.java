package com.example.shadow.domain.member.controller;

import com.example.shadow.domain.member.dto.MemberDto;
import com.example.shadow.domain.member.dto.MemberUpdateDto;
import com.example.shadow.domain.member.entity.Member;
import com.example.shadow.domain.member.service.MemberService;
import com.example.shadow.exception.SignupEmailDuplicatedException;
import com.example.shadow.exception.SignupUsernameDuplicatedException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class MemberController {

    private final MemberService memberService;
    private PasswordEncoder passwordEncoder;
    private static boolean usernameChecked;
    private static boolean emailChecked;
    @GetMapping("/signup")
    public String signup(MemberDto memberDto) {
        return "signup_form";
    }

    @PostMapping("/signup")
    public String signup(@Valid MemberDto memberDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "signup_form";
        }
        if(!usernameChecked){
            bindingResult.rejectValue("username","usernameNotCheck","아이디 중복 확인이 필요합니다.");
            return "signup_form";
        }
        if(!emailChecked){
            bindingResult.rejectValue("email","emailNotCheck","이메일 중복 확인이 필요합니다.");
            return "signup_form";
        }
        if (!memberDto.getPassword1().equals(memberDto.getPassword2())) {
            bindingResult.rejectValue("memberPwd2", "passwordInCorrect", "2개의 패스워드가 일치하지 않습니다.");
            return "signup_form";
        }

        try {
            memberService.create(
                    memberDto.getUsername(),
                    memberDto.getPassword1(),
                    memberDto.getName(),
                    memberDto.getEmail()
            );
        } catch (SignupUsernameDuplicatedException e) {
            e.printStackTrace();
            bindingResult.reject("signupUsernameDuplicated", e.getMessage());
            return "signup_form";
        } catch (SignupEmailDuplicatedException e) {
            e.printStackTrace();
            bindingResult.reject("signupEmailDuplicated", e.getMessage());
            return "signup_form";
        }
        return "redirect:/";
    }

    @PostMapping("/signup/usernameCheck")
    public String usernameCheck(@RequestParam String username, Model model) {
        try{
            memberService.usernameCheck(username);
            model.addAttribute("alert", "success");
            model.addAttribute("msg", "사용할 수 있는 아이디 입니다.");
            usernameChecked=true;
            return "signup_form :: #resultDiv";
        } catch (SignupUsernameDuplicatedException e) {
            log.debug("username already exists : "+ username);
            e.printStackTrace();
            model.addAttribute("alert", "danger");
            model.addAttribute("msg", e.getMessage());
            return "signup_form :: #resultDiv";
        }
    }
    @PostMapping("/signup/emailCheck")
    public String emailCheck(@RequestParam String email, Model model) {
        try{
            memberService.emailCheck(email);
            model.addAttribute("alert", "success");
            model.addAttribute("msg", "사용할 수 있는 이메일 입니다.");
            emailChecked=true;
            return "signup_form :: #resultDiv";
        } catch (SignupEmailDuplicatedException e) {
            log.debug("email already exists : "+ email);
            e.printStackTrace();
            model.addAttribute("alert", "danger");
            model.addAttribute("msg", e.getMessage());
            return "signup_form :: #resultDiv";
        }
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
    public String modify(@PathVariable("id") Long id, Model model, MemberUpdateDto memberUpdateDto) {
        Member member = memberService.findById(id);
        model.addAttribute("member", member);
        return "member_form";
    }
    @PutMapping("/members/{id}")
    public String update(@PathVariable Long id, @Valid @ModelAttribute("memberUpdateDto") MemberUpdateDto memberUpdateDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "member_form";
        }
        if (!memberUpdateDto.getPassword1().equals(memberUpdateDto.getPassword2())) {
            bindingResult.rejectValue("memberPwd2", "passwordInCorrect",
                    "2개의 패스워드가 일치하지 않습니다.");
            return "member_form";
        }
        Member member = memberService.findById(id);

        try {
            memberService.update(
                    member,
                    memberUpdateDto.getPassword1(),
                    memberUpdateDto.getName(),
                    memberUpdateDto.getEmail()
                    );
        } catch (SignupUsernameDuplicatedException e) {
            e.printStackTrace();
            bindingResult.reject("signupUsernameDuplicated", e.getMessage());
            return "member_form";
        } catch (SignupEmailDuplicatedException e) {
            e.printStackTrace();
            bindingResult.reject("signupEmailDuplicated", e.getMessage());
            return "member_form";
        }
        return "redirect:/";
    }

}