package com.yanghj.basic.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.yanghj.basic.filter.JwtAuthenticationFilter;

import lombok.RequiredArgsConstructor;

// description: 인증 및 인가 처리와 관련된 여러 설정을 지정하는 클래스 //

// description: @Configurable - Spring 설정 변경이 가능한 클래스로 지정하는 어노테이션 //
@Configurable
// description: @EnableWebSecurity - Spring Security 설정 변경 클래스로 지정하는 어노테이션 //
@EnableWebSecurity
@RequiredArgsConstructor 
public class WebSecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    // description: Security 설정 변경 메서드 작성 (오타가 있으면 안된다.) //
    @Bean // 메소드를 자비 빈으로 등록하기 때문에 @Component 이 아닌 @Bean 을 사용한다.
    protected SecurityFilterChain configure(HttpSecurity httpSecurity) throws Exception {

        httpSecurity
            // 서버 생성 //
            // description: CORS 정책은 기본 정책으로 사용 (CorsConfig를 따르게 함. 만들어 놓은 기본 값을 사용하겠다.) //
            .cors().and()
            // description: CSRF 보안 설정은 사용하지 않음 (섹션을 관리하는 것이 아니기 때문에) //
            .csrf().disable()
            // description: basic 인증 사용하지 않음 //
            .httpBasic().disable()
            // description: 세션 생성 전략을 세션을 생성하지 않음으로 설정 //
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()

            // 요청 //
            // description: 어떤 요청에 대해서 인증을 수행할지 지정하는 설정 (방화벽) //
            .authorizeRequests()
            // description: antMatcher() - 특정 요청 지정 (url 패턴에 따른 지정, http method에 따른 지정, http method + url 패턴에 따른 지정) //
            // description: url 패턴에 따른 지정 (/user 로 시작하는 모든 요청에 대하여 허용) //
            .antMatchers("/user/**").permitAll()
            // description: http method에 따른 지정 (모든 GET 요청에 대해서 허용) //
            .antMatchers(HttpMethod.GET).permitAll()
            // description: http method + url 패턴에 따른 지정 (POST/board 로 시작하는 모든 요청에 대하여 허용) //
            .antMatchers(HttpMethod.POST, "/board/**").permitAll()
            // description: 나머지 모든 요청에 대하여 인증을 수행 //
            .anyRequest().authenticated().and()
            // description: 인증 과정에서 발생한 예외를 직접 처리 //
            .exceptionHandling().authenticationEntryPoint(new FailedAuthenticationEntryPooint());

        httpSecurity.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        // build하여 필터에 등록.
        return httpSecurity.build();

    }

}

// description: 인증 과정에서 발생하는 예외에 대한 Response를 직접 처리하는 클래스 //
// 하나의 자바 클래스 안에는 하나의 public만 쓸 수 있다.
// description: AuthenticationEntryPoint 인터페이스 구현 //
class FailedAuthenticationEntryPooint implements AuthenticationEntryPoint {

    // commence를 사용함으로써 request와 response를 직접 지정할 수 있다.
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException authException) throws IOException, ServletException {
        
        // description: Response의 컨텐츠 타입을 json으로 지정 //
        response.setContentType("application/json");
        // description: Response의 Http 상태 코드를 지정 //
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        // description: Response의 Body를 작성 //
        // getWriter()- Body를 작성해줌 // 반환해줄 제이슨 형태를 만들어 줌 // 스프링부트를 쓰지 않고 사용할 때의 식. 클래스로 적을 때는 손이 많이 간다!
        response.getWriter().write("{ \"code\": \"AF\", \"message\": \"Authorization Failed\" }");
                                    //{ "code": "AF", "message": "Authorization Failed" }
        
    }

}
