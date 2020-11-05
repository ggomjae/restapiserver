package com.ggomjae.restapiserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/*
    java.net.URI 클래스를 통해 Java 프로그램에서 URI 클래스를 선언 할 수 있다.

    URI 클래스는 리소스를 식별하고 URI을 분석하는 기능만 제공한다.
    URI 클래스는 URI가 참조하는 리소스를 가져오는 메소드를 제공하지 않는다.
    URI 클래스는 URL 클래스보다 표준사항을 더 잘 따른다.
    URI 객체는 상대적 URI를 표현할 수 있다.
 */
import javax.validation.Valid;
import java.net.URI;
import java.util.Locale;

@RestController
public class ApiController {

    @Autowired
    private MessageSource messageSource;

    @GetMapping("/users/{id}")
    public User findUser(@PathVariable int id){
        /*
            만약 이렇게 null 을 하지 않는다면 client는 200이라는 status를 받고 아무런 데이터를 받지 못한다.
         */
        if(service.findUser(id) == null){
            throw new UserNotFoundException(String.format("I[%s] not found",id));
        }
        return service.findUser(id);
    }

    /*
        ResponseEntity는 HttpEntity를 상속받음으로써 HttpHeader와 body를 가질 수 있다
     */
    @PostMapping("/users")
    public ResponseEntity<User> statusEx(@Valid @RequestBody User user){
        User savedUser = service.save(user);

        /*
            Spring Boot RestController에서 요청 URL을 얻는 방법
            public class ServletUriComponentsBuilder extends UriComponentsBuilder

            UriComponentsBuilder with additional static factory methods to create links based on the current HttpServletRequest.
         */
        URI location = ServletUriComponentsBuilder.fromCurrentContextPath()
                            .path("/{id}")
                            .buildAndExpand(savedUser.getId())
                            .toUri();

        /* HTTP Header에 Location 정보  status 201 created 가 나옴 */
        return ResponseEntity.created(location);
    }

    @GetMapping(path = "/example/interationalized")
    public String interationalizedMethod(
            @RequestHeader(name = "Accept-Language", required=false) Locale locale){
            return messageSource.getMessage("example.message", null,locale);
    }
}
