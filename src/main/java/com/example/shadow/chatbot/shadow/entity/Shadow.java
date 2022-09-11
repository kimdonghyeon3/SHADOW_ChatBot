package com.example.shadow.chatbot.shadow.entity;

import com.example.shadow.chatbot.member.entity.Member;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class Shadow {

    @Id // primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    private Long id;

    @Column
    private String name;

    @Column
    private String mainurl;

    @JsonIgnore
    @OneToMany(mappedBy = "shadow", cascade = {CascadeType.ALL})
    private List<Keyword> keywords;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Member member;
}