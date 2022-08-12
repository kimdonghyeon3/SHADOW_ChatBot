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
    private Long member_uid;

    @Column(name="member_name")
    private String member_name;

    @Column(unique = true,name="member_id")
    private String member_id;

    @Column(name="member_pwd")
    private String member_pwd;

    @Column(unique = true,name="member_email")
    private String member_email;

    @Column(name="member_login_status")
    private Boolean member_login_status;

}
