package com.ggomjae.restapiserver.exception;

import com.ggomjae.restapiserver.controller.UserNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

// AOP 로 핸들링
@RestController
@ControllerAdvice // 이 Bean은 모든 컨트롤러가 시작할 때 실행되는 것을 뜻하기 위한 어노테이션
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    // exception <- 어떤 에러인지, WebRequest <- 어디서 발생한 요청
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request){
        ExceiptionResponse exceiptionResponse =
                new ExceiptionResponse(new Date(),ex.getMessage(), request.getDescription(false));

        return new ResponseEntity(exceiptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity<Object> handleUserNotFoundExceptions(Exception ex, WebRequest request){
        ExceiptionResponse exceiptionResponse =
                new ExceiptionResponse(new Date(),ex.getMessage(), request.getDescription(false));

        return new ResponseEntity(exceiptionResponse, HttpStatus.NOT_FOUND);
    }

    /*
        ResponseEntityExceptionHandler 에 Ctrl을 누르고 왼쪽 마우스 눌러서 handleMethodArgumentNotValid 찾고 오버라이딩
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        // message가 너무 길어서 저렇게 바꿔줬음. "Validation Error" 로.
        ExceiptionResponse exceiptionResponse =
                new ExceiptionResponse(new Date(),"Validation Error", ex.getBindingResult().toString());

        return new ResponseEntity(exceiptionResponse,HttpStatus.BAD_REQUEST);
    }
}
