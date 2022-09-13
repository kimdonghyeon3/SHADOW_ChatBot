package com.example.shadow.domain.member.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
//@NoArgsConstructor(access = AccessLevel.PROTECTED) // 기본생성자 제한으로 누락 방지
@EntityListeners(AuditingEntityListener.class) // entity crud 전, 후 이벤트 처리
@Table(name = "member")
public class Member {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20, unique = true)
    private String username;

    @Column
    @Enumerated(EnumType.STRING)
    private MemberRole role;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, length = 20)
    private String name;

    @Column(unique = true)
    private String email;

    @Builder
    public Member(String username, String password, String name, String email) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
    }

    public void updateUsername(String username){
        this.username = username;
    }
    public void updateName(String name){
        this.name = name;
    }
    public void updateEmail(String email){
        this.email = email;
    }
    public void setEncryptedPassword(String encryptedPassword) {
        this.password = encryptedPassword;
    }
    public void setRole(MemberRole role) {this.role=role;}
}