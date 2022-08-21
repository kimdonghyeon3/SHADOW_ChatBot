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


@RequiredArgsConstructor
@Controller
@RequestMapping("/")
@Slf4j
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

    @PostMapping("/signup/isCheckedUsername")
    public ResponseEntity<ResultResponse> checkUsername(@RequestParam boolean isCheckedUsername, @RequestParam boolean isChangedUsername) {
        log.debug("isChecked : "+ isCheckedUsername);
        log.debug("isChanged : "+ isChangedUsername);
        if (isCheckedUsername&&!isChangedUsername) {
            return ResponseEntity.ok(ResultResponse.of("CHECK_USERNAME_FIN","", true));
        } else {
            return ResponseEntity.ok(ResultResponse.of("CHECK_USERNAME_NO","아이디 중복 체크가 필요합니다." ,false));
        }
    }

    @PostMapping("/signup/isCheckedEmail")
    public ResponseEntity<ResultResponse> checkEmail( @RequestParam boolean isCheckedEmail, @RequestParam boolean isChangedEmail) {
        if (isCheckedEmail&&!isChangedEmail) {
            return ResponseEntity.ok(ResultResponse.of("CHECK_EMAIL_FIN","", true));
        } else {
            return ResponseEntity.ok(ResultResponse.of("CHECK_EMAIL_NO","이메일 중복 체크가 필요합니다." ,false));
        }
    }

    @PostMapping("/signup/usernameCheck")
    public String usernameCheck(@RequestParam String username, Model model) {
        try{
            memberService.usernameCheck(username);
            model.addAttribute("alert", "success");
            model.addAttribute("msg", "사용할 수 있는 아이디 입니다.");
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
    @PostMapping("/members/{id}/emailCheck")
    public String updateEmailCheck(@PathVariable long id, @RequestParam String email, Model model) {
        if(memberService.findById(id).getEmail().equals(email)){
            return "member_form :: #resultDiv";
        }
        try{
            memberService.emailCheck(email);
            model.addAttribute("alert", "success");
            model.addAttribute("msg", "사용할 수 있는 이메일 입니다.");

            return "member_form :: #resultDiv";
        } catch (SignupEmailDuplicatedException e) {
            log.debug("email already exists : "+ email);
            e.printStackTrace();
            model.addAttribute("alert", "danger");
            model.addAttribute("msg", e.getMessage());
            return "member_form :: #resultDiv";
        }
    }


}