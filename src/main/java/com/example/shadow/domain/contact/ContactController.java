package com.example.shadow.domain.contact;

import com.example.shadow.domain.contact.dto.ContactDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @GetMapping("/contact")
    public String sendMail(Model model) {
        model.addAttribute("pageTitle", "Contact");
        return "contact/contact_form";
    }

    @PostMapping("/contact")
    public String sendMail(Model model, ContactDto contactDto) {
        contactService.sendSimpleMessage(contactDto);
        model.addAttribute("pageTitle", "Contact");

        return "redirect:/contact";
    }
}
