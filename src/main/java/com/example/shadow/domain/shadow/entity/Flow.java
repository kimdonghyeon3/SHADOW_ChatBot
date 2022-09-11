package com.example.shadow.domain.shadow.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Flow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private String url;

    @JsonIgnore
    @OneToMany(mappedBy = "flow", cascade = {CascadeType.ALL})
    private List<Flowchart> flowCharts = new ArrayList<>();
}
