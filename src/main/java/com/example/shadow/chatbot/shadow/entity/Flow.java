package com.example.shadow.chatbot.shadow.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
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

    @OneToMany(mappedBy = "flow", cascade = {CascadeType.ALL})
    private List<Flowchart> flowCharts = new ArrayList<>();
}
