package com.example.shadow.domain.shadow.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
public class Flowchart implements Serializable {

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
    //keyword flow order(column)
    //반품    홈버튼    1
    //반품    마이페이지  2
    //배송조회 홈버튼 1
    //배송조회 조회페이지 2
}
