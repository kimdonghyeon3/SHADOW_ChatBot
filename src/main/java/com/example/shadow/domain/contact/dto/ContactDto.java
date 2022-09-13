package com.example.shadow.domain.contact.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ContactDto {
    private String address;
    private String title;
    private String content;
}
