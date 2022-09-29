package com.example.shadow.domain.member.controller;

import com.example.shadow.domain.contact.dto.ContactDto;
import com.example.shadow.domain.member.dto.MemberDto;
import com.example.shadow.domain.member.dto.MemberUpdateDto;
import com.example.shadow.domain.member.entity.Member;
import com.example.shadow.domain.member.service.MemberService;
import com.example.shadow.domain.shadow.entity.Shadow;
import com.example.shadow.domain.shadow.service.KeywordService;
import com.example.shadow.domain.shadow.service.ShadowService;
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
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RequiredArgsConstructor
@Controller
@RequestMapping("/")
@Slf4j
public class MemberController {

    private final MemberService memberService;
    private final ShadowService shadowService;
    private final KeywordService keywordService;

    private final PasswordEncoder passwordEncoder;

    @GetMapping("/signup")
    public String signup(Model model, MemberDto memberDto) {
        model.addAttribute("pageTitle", "SignUp");
        return "member/signup_form";
    }

    @PostMapping("/signup")
    public String signup(Model model, @Valid MemberDto memberDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.debug("hasError");
            return "member/signup_form";
        }
        if (!memberDto.getPassword1().equals(memberDto.getPassword2())) {
            log.debug("passwordInCorrect");
            bindingResult.addError(new FieldError("member", "memberPwd2","2개의 패스워드가 일치하지 않습니다."));
            return "member/signup_form";
        }
        log.info("memberDto Name = {}", memberDto.getUsername());
        try {
            memberService.create(memberDto.getUsername(), memberDto.getPassword1(), memberDto.getName(), memberDto.getEmail());
        } catch (RuntimeException e) {
            e.printStackTrace();
            bindingResult.reject("signup failed", e.getMessage());
            return "/member/signup_form";
        }
        memberService.login(memberDto.getUsername(),memberDto.getPassword1());
        return "redirect:/members";
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

        List<Shadow> shadows = shadowService.findByMemberId(id);
        log.debug("[delete] shadow find by member id : "+shadows);
        shadows.forEach(s -> keywordService.delete(s));
        log.debug("[delete] keyword delete at shadow fin: ");

        shadows.forEach(s -> shadowService.delete(s));
        log.debug("[delete] shadow delete at shadow fin : ");
        //keywordService.delete(shadow);
        //shadowService.delete(shadow);
        memberService.delete(id);
        return "redirect:/logout";
    }
    @GetMapping("/members/checkPwd/{id}")
    public ModelAndView checkPwd(@PathVariable("id") Long id, ModelAndView mav, Principal principal){
        Member member = memberService.findById(id);
        log.debug("member get name : "+ member.getUsername());
        log.debug("login get name : "+ principal.getName());
        if(!member.getUsername().equals(principal.getName())){
            mav.addObject("msg","접근이 불가능합니다.");
            mav.addObject("url","/members");
            mav.setViewName("alert");
            return mav;
        }
        mav.addObject("member",member);
        mav.addObject("pageTitle", "Check Password");
        mav.setViewName("member/checkPwd");
        return mav;
    }
    @PostMapping("/members/{id}")
    public ModelAndView modify(@PathVariable("id") Long id, Model model, String password, MemberUpdateDto memberUpdateDto, Principal principal, ModelAndView mav) {
        Member member = memberService.findById(id);
        log.debug("member get name : "+ member.getUsername());
        log.debug("login get name : "+ principal.getName());
        if(!member.getUsername().equals(principal.getName())||password==null){
            mav.addObject("msg","접근이 불가능합니다.");
            mav.addObject("url","/members");
            mav.setViewName("alert");
            return mav;
        }
        if(!passwordEncoder.matches(password,member.getPassword())){
            mav.addObject("msg","잘못된 패스워드입니다.");
            mav.addObject("url","/members/checkPwd/%d".formatted(id));
            mav.setViewName("alert");
            return mav;
        }
        mav.addObject("member",member);
        mav.addObject("pageTitle", "Modify User");
        mav.setViewName("member/member_form");
        return mav;
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
        return "redirect:/members";
    }
    @PostMapping("/members/{id}/checkEmail")
    public ResponseEntity<ResultResponse> updateEmailCheck(@PathVariable long id, @RequestParam String email) {
        if(memberService.findById(id).getEmail().equals(email)){
            return ResponseEntity.ok(ResultResponse.of("UPDATE_EMAIL_NONE","변경사항이 없습니다.", "same"));
        } else{
            final boolean check = memberService.checkEmail(email);
            if (!check) {
                return ResponseEntity.ok(ResultResponse.of("CHECK_EMAIL_GOOD","사용할 수 있는 이메일입니다..", true));
            } else {
                return ResponseEntity.ok(ResultResponse.of("CHECK_EMAIL_BAD","중복된 이메일 입니다." ,false));
            }
        }
    }
    @GetMapping("/members/findPwd")
    public ModelAndView findPwd(ModelAndView mav){
        mav.addObject("pageTitle","Find Password");
        mav.setViewName("member/findPwd");
        return mav;
    }
    @PostMapping("/members/findPwd/send")
    public ResponseEntity<ResultResponse> sendVerificationCode(Model model, @RequestParam String email, @RequestParam String name){
        log.debug("[pw] email, name : "+email+name);
        Member member = memberService.findByEmail(email);
        log.debug("[pw] member id :"+member.getId());
        log.debug("[pw] member email :"+member.getEmail());
        String code = memberService.sendSimpleMessage(member);
//        String code = "42a79d3d1dcc40d0ace263ca492eea9c";
        log.debug("code: "+code);
        model.addAttribute("member",member);
        return ResponseEntity.ok(ResultResponse.of("SEND_VERIFICATION_CODE","인증코드를 전송하였습니다.", code+"_"+member.getId()));
    }
    @PostMapping("/members/findPwd/check")
    public ModelAndView checkVerificationCode(ModelAndView mav,@RequestParam("mid") String id, String inputCode, String code){
        Member member = memberService.findById(Long.parseLong(id));
        if(inputCode.equals(code)){
            mav.addObject("pageTitle","Change Password");
            mav.addObject("member",member);
            mav.setViewName("member/changePwd");
        }
        else{
            mav.addObject("msg","옳지 않은 인증코드입니다.");
            mav.addObject("url","/members/findPwd".formatted(id));
            mav.setViewName("alert");
        }
        return mav;
    }
    @GetMapping("/members/changePwd")
    public ModelAndView changePwd(ModelAndView mav, @RequestParam("mid") String id){
        Member member = memberService.findById(Long.parseLong(id));
        mav.addObject("pageTitle","Change Password");
        mav.addObject("member",member);
        mav.setViewName("member/changePwd");
        return mav;
    }
    @PostMapping("/members/changePwd/{id}")
    public String updatePwd(Model model,@PathVariable Long id, @Valid @ModelAttribute("password1") String password1, @Valid @ModelAttribute("password2") String password2, BindingResult bindingResult){

        if (!password1.equals(password2)) {
            bindingResult.rejectValue("memberPwd2", "passwordInCorrect",
                    "2개의 패스워드가 일치하지 않습니다.");
            return "member/changePwd";
        }
        memberService.update(memberService.findById(id), password1);
        model.addAttribute("pageTitle", "User Modify");
        return "redirect:/login";
    }
}