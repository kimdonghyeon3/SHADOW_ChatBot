package com.example.shadow.chatbot.shadow.entity;

import com.example.shadow.chatbot.member.entity.Member;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Keyword {

    @Id // primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    private Long id;

    @Column
    private String name;

    @Column
    private Boolean favorite;

    @OneToMany(mappedBy = "keyword", cascade = {CascadeType.ALL})
    private List<Flowchart> flowcharts;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Shadow shadow;
}