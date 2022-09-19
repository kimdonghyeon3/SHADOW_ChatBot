package com.example.shadow.domain.shadow.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class Flow {

    @Id // primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    private Long id;

    @Column(nullable = false, length = 20)
    private String name;

    @Column
    private String description;

    @Column
    private String url;

    @JsonIgnore
    @OneToMany(mappedBy = "flow")
    private List<Flowchart> flowcharts;

    @Column
    private Long keyword;
}
