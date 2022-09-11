package com.example.shadow.domain.shadow.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
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
