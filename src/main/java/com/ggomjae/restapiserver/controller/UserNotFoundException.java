package com.ggomjae.restapiserver.controller;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// 2XX : 성공
// 4XX : Client
// 5XX : Server

// 이 ResponseStatus를 사용하지 않으면 500 status를 보낸다. 그건 명확하지 않은 에러 전달이다.
@ResponseStatus(HttpStatus.NOT_FOUND) // 404 Error로 존재하지않는걸 표현
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String msg){
        super(meg);
    }
}
