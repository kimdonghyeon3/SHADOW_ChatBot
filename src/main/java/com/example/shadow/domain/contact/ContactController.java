package com.example.shadow.domain.contact;

import com.example.shadow.domain.contact.dto.ContactDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class ContactController {
    private final ContactService contactService;

//    @RequestMapping()
//    public String createInquiry(){
//        return "contact/contact_form";
//    }

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping("/shadow/contact")
    public String sendMail() {
        return "contact/contact_form";
    }

    @PostMapping("/shadow/contact")
    public String sendMail(ContactDto contactDto) {
        contactService.sendSimpleMessage(contactDto);
        System.out.println("메일 전송 완료");
        return "redirect:/";
    }
}
