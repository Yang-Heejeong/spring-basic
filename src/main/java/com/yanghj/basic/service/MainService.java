package com.yanghj.basic.service;

import org.springframework.http.ResponseEntity;

import com.yanghj.basic.dto.request.PatchNicknameRequestDto;
import com.yanghj.basic.dto.request.PostUserRequestDto;
import com.yanghj.basic.dto.response.DeleteUserResponseDto;
import com.yanghj.basic.dto.response.PatchNicknameResponseDto;
import com.yanghj.basic.dto.response.PostUserResponseDto;

public interface MainService {
    
    String getMethod();
    ResponseEntity<? super PostUserResponseDto> postUser(PostUserRequestDto dto);
    ResponseEntity<? super PatchNicknameResponseDto> patchNickname(PatchNicknameRequestDto dto);
    ResponseEntity<? super DeleteUserResponseDto> deleteUser(String email);

}
