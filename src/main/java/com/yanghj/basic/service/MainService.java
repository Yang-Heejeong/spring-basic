package com.yanghj.basic.service;

import org.springframework.http.ResponseEntity;

import com.yanghj.basic.dto.request.PostUserRequestDto;
import com.yanghj.basic.dto.response.PostUserResponseDto;

public interface MainService {
    
    String getMethod();
    ResponseEntity<? super PostUserResponseDto> postUser(PostUserRequestDto dt);

}
