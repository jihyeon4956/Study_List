package com.sparta.springauth.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequestDto {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @Pattern(regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    @NotBlank
    private String email;
    private boolean admin = false;
    private String adminToken = "";

    /** 정규표현식_Email
     * // @ 기호를 확인합니다. 기호 앞과 뒤 문자는 신경쓰지 않습니다.
     * String regx1 = "^(.+)@(.+)$";

     * // @ 기호 앞에 오는 방식에 제한을 추가합니다.
     * // A-Z, a-z, 0-9, ., _ 를 사용할 수 있습니다.
     * String regx2 = "^[A-Za-z0-9+_.-]+@(.+)$";

     * // 이메일 형식에 허용되는 문자를 모두 사용할 수 있습니다.
     * String regx3 = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
     */
}