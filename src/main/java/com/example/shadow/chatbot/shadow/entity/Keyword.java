package com.example.shadow.chatbot.shadow.entity;

import com.example.shadow.chatbot.member.entity.Member;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;


@Entity
@Getter
@Setter
public class Keyword {

    @Id // primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    private Long id;

    @Column
    private String name;

    @Column
    private Boolean favorite;

    @JsonIgnore
    @OneToMany(mappedBy = "keyword", cascade = {CascadeType.ALL})
    private List<Flowchart> flowcharts;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Shadow shadow;

    @JsonIgnore
    @OneToMany(mappedBy = "keyword", cascade = {CascadeType.ALL})
    private List<Question> questions;
}