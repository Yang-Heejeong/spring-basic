package com.yanghj.basic.dto.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// 인스턴스를 생성하기 위해서 반드시 있어야 한다.
@Setter
@NoArgsConstructor

@Getter // 빈 인스턴스를 생성하기 위해, 작업하기 위해 Getter 사용
public class PostUserRequestDto {
    @NotBlank
    @Email
    private String email;
    @NotBlank
    @Length(min=8, max=20)
    private String password;
    @NotBlank
    private String nickname;
    @NotBlank
    @Pattern(regexp="^[0-9]{11,13}$")
    private String telNumber;
    @NotBlank
    private String address;
    private String addressDetail;
}
