package com.example.shadow.domain.member.controller;

import com.example.shadow.domain.member.dto.MemberDto;
import com.example.shadow.domain.member.dto.MemberUpdateDto;
import com.example.shadow.domain.member.entity.Member;
import com.example.shadow.domain.member.service.MemberService;
import com.example.shadow.global.exception.SignupEmailDuplicatedException;
import com.example.shadow.global.exception.SignupUsernameDuplicatedException;
import com.example.shadow.global.result.ResultResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;


@RequiredArgsConstructor
@Controller
@RequestMapping("/")
@Slf4j
public class MemberController {

    private final MemberService memberService;
    private PasswordEncoder passwordEncoder;

    @GetMapping("/signup")
    public String signup(Model model, MemberDto memberDto) {
        model.addAttribute("pageTitle", "SignUp");
        return "member/signup_form";
    }

    @PostMapping("/signup")
    public String signup(Model model, @Valid MemberDto memberDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "member/signup_form";
        }
        if (!memberDto.getPassword1().equals(memberDto.getPassword2())) {
            bindingResult.rejectValue("memberPwd2", "passwordInCorrect", "2개의 패스워드가 일치하지 않습니다.");
            return "member/signup_form";
        }
        log.info("memberDto Name = {}", memberDto.getUsername());
        memberService.create(memberDto.getUsername(), memberDto.getPassword1(), memberDto.getName(), memberDto.getEmail());
        model.addAttribute("pageTitle", "SignUp");
        return "redirect:/";
    }

    @PostMapping("/signup/checkUsername")
    public ResponseEntity<ResultResponse> checkUsername(@RequestParam String username) {
        final boolean check = memberService.checkUsername(username);
        if (!check) {
            return ResponseEntity.ok(ResultResponse.of("CHECK_USERNAME_GOOD","사용할 수 있는 아이디입니다.", true));
        } else {

            return ResponseEntity.ok(ResultResponse.of("CHECK_USERNAME_BAD","중복된 아이디 입니다." ,false));
        }
    }
    @PostMapping("/signup/checkEmail")
    public ResponseEntity<ResultResponse> checkEmail(@RequestParam String email) {
        final boolean check = memberService.checkEmail(email);
        if (!check) {
            return ResponseEntity.ok(ResultResponse.of("CHECK_EMAIL_GOOD","사용할 수 있는 이메일입니다..", true));
        } else {
            return ResponseEntity.ok(ResultResponse.of("CHECK_EMAIL_BAD","중복된 이메일 입니다." ,false));
        }
    }
    @PostMapping("/isChecked")
    public ResponseEntity<ResultResponse> checkUsernameAndEmail(@RequestParam boolean isCheckedUsername, @RequestParam boolean isChangedUsername, @RequestParam boolean isCheckedEmail, @RequestParam boolean isChangedEmail) {
        log.debug("isChecked username : "+ isCheckedUsername);
        log.debug("isChanged username: "+ isChangedUsername);
        log.debug("isChecked email : "+ isCheckedEmail);
        log.debug("isChanged email: "+ isChangedEmail);
        Map<String,Boolean> data = new HashMap<>();
        data.put("username",true);
        data.put("email",true);
        if (isCheckedUsername && !isChangedUsername && isCheckedEmail && !isChangedEmail) {
            return ResponseEntity.ok(ResultResponse.of("CHECK_FIN","",data ));
        } else if(isCheckedEmail && !isChangedEmail){
            data.replace("username", false);
            log.debug("isChecked username : "+ isCheckedUsername);
            log.debug("isChanged username: "+ isChangedUsername);
            return ResponseEntity.ok(ResultResponse.of("CHECK_USERNAME_NO","아이디 중복 체크가 필요합니다." ,data));
        } else if(isCheckedUsername && !isChangedUsername){
            data.replace("email", false);
            log.debug("isChecked email : "+ isCheckedEmail);
            log.debug("isChanged email: "+ isChangedEmail);
            return ResponseEntity.ok(ResultResponse.of("CHECK_EMAIL_NO","이메일 중복 체크가 필요합니다." ,data));
        } else{
            data.replace("username", false);
            data.replace("email", false);
            return ResponseEntity.ok(ResultResponse.of("CHECK_ALL_NO","아이디, 이메일 중복 체크가 필요합니다." ,data));
        }
    }
    @GetMapping("/login")
    public String login(Model model, MemberDto memberDto) {
        model.addAttribute("pageTitle", "SignIn");
        return "member/login_form";
    }

    @GetMapping("/members")
    public String detail(Model model, Principal principal){
        Member member = memberService.findByUsername(principal.getName());
        model.addAttribute("member", member);
        model.addAttribute("pageTitle", "Profile");
        return "member/member_detail";
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
        model.addAttribute("pageTitle", "User Modify");
        return "member/member_form";
    }
    @PutMapping("/members/{id}")
    public String update(Model model, @PathVariable Long id, @Valid @ModelAttribute("memberUpdateDto") MemberUpdateDto memberUpdateDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "member/member_form";
        }
        if (!memberUpdateDto.getPassword1().equals(memberUpdateDto.getPassword2())) {
            bindingResult.rejectValue("memberPwd2", "passwordInCorrect",
                    "2개의 패스워드가 일치하지 않습니다.");
            return "member/member_form";
        }
        memberService.update(memberService.findById(id), memberUpdateDto.getPassword1(), memberUpdateDto.getName(), memberUpdateDto.getEmail());
        model.addAttribute("pageTitle", "User Modify");
        return "redirect:/";
    }
    @PostMapping("/members/{id}/checkEmail")
    public ResponseEntity<ResultResponse> updateEmailCheck(@PathVariable long id, @RequestParam String email) {
        if(memberService.findById(id).getEmail().equals(email)){
            return ResponseEntity.ok(ResultResponse.of("UPDATE_EMAIL_NONE","", "same"));
        } else{
            final boolean check = memberService.checkEmail(email);
            if (!check) {
                return ResponseEntity.ok(ResultResponse.of("CHECK_EMAIL_GOOD","사용할 수 있는 이메일입니다..", true));
            } else {
                return ResponseEntity.ok(ResultResponse.of("CHECK_EMAIL_BAD","중복된 이메일 입니다." ,false));
            }
        }
    }

}