package com.example.shadow.chatbot.shadow.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Flowchart {
    @Id // primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    private Long id;

    @Column
    private int seq;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Keyword keyword;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Flow flow;
}
