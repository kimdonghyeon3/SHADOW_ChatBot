package com.example.shadow.domain.shadow.entity;

import com.example.shadow.domain.member.entity.Member;
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

    @OneToMany(mappedBy = "keyword", cascade = {CascadeType.ALL})
    //@OneToMany(mappedBy = "keyword")
    private List<Flowchart> flowcharts;

//    @ManyToOne
//    private Flowchart flowchart;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Shadow shadow;

    //반품
    //배송조회

}
