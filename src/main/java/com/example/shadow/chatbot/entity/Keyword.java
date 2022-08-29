package com.example.shadow.chatbot.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Keyword {
//    @ManyToOne
//    @JoinColumn(foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
//    private Shadow shadow_uid;

    @Id // primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    private Long id;

    @Column
    private String name;

    @Column
    private Boolean favorite;

    @OneToMany(mappedBy = "keyword", cascade = {CascadeType.ALL})
    private List<FlowChart> flowcharts;

}