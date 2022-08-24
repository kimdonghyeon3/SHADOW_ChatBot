//package com.example.shadow.domain.shadow.entity;
//
//
//import com.example.shadow.domain.member.entity.Member;
//import lombok.Getter;
//import lombok.Setter;
//
//import javax.persistence.*;
//import java.util.List;
//
//@Entity
//@Getter
//@Setter
//public class Shadow {
//
//    @Id // primary key
//    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
//    private Long id;
//
//    @Column
//    private String name;
//
//    @OneToMany(mappedBy = "shadow", cascade = {CascadeType.ALL})
//    private List<Member> member;
//
//}
