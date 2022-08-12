package com.example.shadow.member;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long member_id;

    @Column(unique = true)
    private String member_name;

    private String member_pwd;

    @Column(unique = true)
    private String member_email;

    private Boolean login_status;

}
