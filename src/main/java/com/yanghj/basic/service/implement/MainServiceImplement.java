package com.yanghj.basic.service.implement;

import org.springframework.stereotype.Component;

import com.yanghj.basic.service.MainService;

@Component
public class MainServiceImplement implements MainService {

    @Override
    public String getMethod() {
        return "This method is GET method.";
    }
    
}
