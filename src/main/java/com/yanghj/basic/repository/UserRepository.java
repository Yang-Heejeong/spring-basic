package com.yanghj.basic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.yanghj.basic.entity.UserEntity;

// description: Repository - 데이터 접근 계층으로 데이터베이스에 접근하여 데이터베이스 작업을 하고, 그 결과를 반환하는 영역 //

// description: @Repository - @Component의 역할을 하며, Repository임을 명시하기 위해 이름만 Repository 이다 //
@Repository
// description: JpaRepository 인터페이스 - JPA 기반의 Repository 인터페이스를 구현하는데 사용되는 인터페이스 //
// description: JpaRepository<T, ID> - T: 해당 리포지토리에서 사용될 엔터티 클래스, ID: 해당 엔터티 클래스에서 지정한 기본키 필드의 타입 //
public interface UserRepository extends JpaRepository<UserEntity, String> { // 테이블 이름 + Repository

    // 쿼리 메소드 명 사용으로 생성

    // SELECT * FROM user WHERE email = '??';  // 무조건 값이 0~1개가 나온다.
    // 유저테이블만 사용하기 때문에 findBy에 SELECT * FROM user WHERE가 포함되어 있다.
    // findBy -> SELECT * FROM user WHERE
    // findByEmail -> SELECT * FROM user WHERE email
    UserEntity findByEmail(String email);
    // SELECT * FROM user WHERE email = '??' AND nickname ='??';
    UserEntity findByEmailAndNickname(String email, String nickname);
    // SELECT * FROM user WHERE addres_Detail = '??' ORDER BY address DESC;  // n개, 무한대
    // List - 자바 유틸꺼
    List<UserEntity> findByAddressDetailOrderByAddressDesc(String addressDetail);

    // 존재하는지 존재하지 않은지 파악할 수 있다.
    boolean existsByEmail(String email);
    // SELECT * FROM user WHERE email = '?' OR nickname = '?' OR tel_numver = "?" -> 레코드가 존재 하는지
    boolean existsByEmailOrNicknameOrTelNumber(String email, String nickname, String telNumber);

    // 개수를 세어준다.
    long countByAddress (String address);

    // description: @Query - 쿼리 메서드 만으로 데이터베이스 작업을 수행할 수 없을 때 사용하는 어노테이션 //
    // description: JPQL (Java Persistence Query Languege) - SQL과 문법은 유사하지만 데이터베이스 테이블이 아닌 엔터티 클래스 기준으로 쿼리를 작성 //
    // description: Native Qury - SQL //

    // JPQL 방법
    // SELECT * FROM user WHERE email = '??'
    @Query(value="SELECT u FROM user u WHERE u.email = ?1")
    UserEntity findByEmailJPQL(String email);
    // SQL 작성 방법
    @Query(value="SELECT * FROM user WHERE email = ?1", nativeQuery=true)
    UserEntity findByEmailSQL(String email);

    // SELECT * FROM user WHERE email = '??' AND nickname ='??';
    // JPQL
    @Query(value="SELECT u FROM user u WHERE u.email =?1 AND u.nickname = ?2")
    UserEntity findByEmailAndNicknameJPQL(String email, String nickname);

    // == @Query(value="SELECT * FROM user WHERE email IN(SELECT DISTINCT writer_email FROM board), nativeQuery=true)
    @Query(value=
        "SELECT * " +  
        "FROM user " + 
        "WHERE email IN ( " + 
        "   SELECT DISTINCT writer_email " + 
        "   FROM board " + 
        ") "
    , nativeQuery = true)
    List<UserEntity> getBoardWriterUserlist();
    
}
