package com.reservation.conference.controller;

import com.reservation.conference.dto.UserLoginDto;
import com.reservation.conference.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.reservation.conference.utils.HttpResponses.RESPONSE_OK;
import static com.reservation.conference.utils.HttpResponses.RESPONSE_UNAUTHORIZED;


@RestController
@RequiredArgsConstructor    //초기화 되지 않는 final필드에 생성자 생성
@RequestMapping("/users")
public class UserController {

    private final UserService userService;


    @PostMapping("/login")
    public ResponseEntity login(@RequestBody UserLoginDto userLoginDto) throws Exception {

        UserLoginDto userInfo = userService.login(userLoginDto.getId(), userLoginDto.getPassword());

        if(userInfo == null) {
            return RESPONSE_UNAUTHORIZED;
        }else {
            return RESPONSE_OK;
        }
    }

}
