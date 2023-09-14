package com.yanghj.basic.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// description: DTO (Data Transfer Object) : 서로 다른 레이어 간에 데이터를 전송하고자 할 때 사용하는 객체 //

// description: Lombok 라이브러리 - 클래스 생성시에 반복적으로 작성하는 메서드를 간편하게 생성해주는 라이브러리 //
@Getter // getter //
@Setter // setter //
@NoArgsConstructor // 매게변수 없는 생성자 //
@AllArgsConstructor // 모두 포함된 생성자 //
public class PostRequestBodyDto {
    private String name;
    private Integer age;
}
