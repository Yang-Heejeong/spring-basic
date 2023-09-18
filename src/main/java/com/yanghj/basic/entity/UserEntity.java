package com.yanghj.basic.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.yanghj.basic.dto.request.PostUserRequestDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

// description: Entity - JPA를 사용할 때 데이터베이스의 테이블과 매핑되는 Java 객체 //

@Getter
@NoArgsConstructor
@AllArgsConstructor
// description: @Entity - 해당 클래스를 JPA Entity로 사용하겠다고 지정하는 어노테이션 //
@Entity(name="user") // user 엔터티를 사용할 때 user 이름을 쓰겠다. 쿼리에 적을 적에 사용되는 이름. 데이터베이스 테이블에 썼던 이름과는 무관하다.
// description: @Table - 해당 Entity 클래스가 데이터베이스의 어떤 테이블과 매핑될지를 명시하는 어노테이션 //
@Table(name="user") // 데이터베이스 테이블에 있는 이름과 반드시 동일한 이름으로 지정해야 한다.
public class UserEntity { // UserEntity가 아니라 User라고 적으면 테이블에 있는 user와 같다고 생각하기 때문에 테이블 이름을 따로 적지 않아도 된다.
    // 명명 규칙 때문에 데이터베이스를 만들 때 테이블 컬럼 명에 띄어 쓰기는 '_'을 사용해야지 자바에서 카멜케이스로 적을 수 있다.
    // description: @ID - Entity 클래스에서 Primary Key 필드를 표시할 때 사용되는 어노테이션 //
    @Id 
    private String email;
    private String password;
    private String nickname;
    private String telNumber;
    private String address;
    private String addressDetail;
    private boolean agreedPersonal;
    private String profileImageUrl;

    // description: Entity 클래스의 필드와 데이터베이스 테이블의 컬럼을 명시적으로 매핑하는 어노테이션 //
    // @Column(name="profile_image_url") 
    // private String profile;

    public UserEntity(PostUserRequestDto dto) {
        this. email = dto.getEmail();
        this. password = dto.getPassword();
        this. nickname = dto.getNickname();
        this. telNumber = dto.getTelNumber();
        this. address = dto.getAddress();
        this. addressDetail = dto.getAddressDetail();
        this. agreedPersonal = true;
    }
}
