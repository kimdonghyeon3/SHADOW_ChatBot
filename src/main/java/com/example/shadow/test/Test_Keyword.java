package com.example.shadow.test;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Test_Keyword {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 200)
    private String question;

    @Column(length = 200)
    private String keyword;
}