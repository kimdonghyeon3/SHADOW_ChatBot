package com.example.shadow.domain.shadow.entity;


import com.example.shadow.domain.member.entity.Member;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    @OneToMany(mappedBy = "shadow")
    private List<Keyword> keywords;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Member member;

    @Column
    private String apiKey;

    @Column
    private Long apicall;

    @Column
    private Long dbcall;

    public void addApiCall(){
        apicall += 1;
    }

    public void addDbCall(){
        dbcall += 1;
    }
}
