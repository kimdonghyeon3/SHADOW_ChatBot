package com.example.shadow.chatbot.shadow.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 200)
    private String question;

    @Column(length = 200)
    private String keyword;
}