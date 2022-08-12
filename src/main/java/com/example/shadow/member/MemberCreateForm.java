package com.example.shadow.member;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
public class MemberCreateForm {

    @NotEmpty(message = "사용자이름은 필수항목입니다.")
    private String member_name;

    @Size(min = 3, max = 25)
    @NotEmpty(message = "사용자ID는 필수항목입니다.")
    private String member_id;

    @NotEmpty(message = "비밀번호는 필수항목입니다.")
    private String member_pwd1;

    @NotEmpty(message = "비밀번호 확인은 필수항목입니다.")
    private String member_pwd2;

    @NotEmpty(message = "이메일은 필수항목입니다.")
    @Email
    private String member_email;

    private Boolean member_login_status;
}