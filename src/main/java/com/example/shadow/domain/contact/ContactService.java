package com.example.shadow.domain.contact;

import com.example.shadow.domain.contact.dto.ContactDto;
import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ContactService {
    private JavaMailSender emailSender;

    public void sendSimpleMessage(ContactDto contactDto) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("a2346532@gmail.com");
        message.setTo("a2346532@gmail.com");
        String text = "응답받으실 이메일 : " + contactDto.getAddress() + "\n질문사항 : " + contactDto.getContent();
        message.setSubject(contactDto.getTitle());
        message.setText(text);
        emailSender.send(message);
    }
}
