package com.yanghj.basic.service;

import org.springframework.http.ResponseEntity;

public interface MainService {
    
    String getMethod();
    ResponseEntity<ResponseDto> postUser(PostUserRequestDto dt);

}
