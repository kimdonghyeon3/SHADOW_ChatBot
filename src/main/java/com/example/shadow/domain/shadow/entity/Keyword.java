package com.example.shadow.domain.shadow.entity;

import com.example.shadow.domain.member.entity.Member;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

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
    @OneToMany(mappedBy = "keyword", fetch = FetchType.EAGER)
    private List<Flowchart> flowcharts;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Shadow shadow;

    @JsonIgnore
    @OneToMany(mappedBy = "keyword")
    private List<Question> questions;
}
