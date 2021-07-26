package com.reservation.conference.controller;

import com.reservation.conference.dto.UserLoginDto;
import com.reservation.conference.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

import static com.reservation.conference.utils.HttpResponses.RESPONSE_OK;
import static com.reservation.conference.utils.HttpResponses.RESPONSE_UNAUTHORIZED;


@RestController
@RequiredArgsConstructor    //초기화 되지 않는 final필드에 생성자 생성
@RequestMapping("/users")
public class UserController {

    private final UserService userService;


    @PostMapping("/login")
    public ResponseEntity login(@RequestBody UserLoginDto userLoginDto, HttpSession session) throws Exception {

        UserLoginDto userInfo = userService.login(userLoginDto.getId(), userLoginDto.getPassword());

        if(userInfo == null) {
            return RESPONSE_UNAUTHORIZED;
        }else {
            //로그인 성공 시, 세션에 userInfo 저장
            session.setAttribute("user", userInfo);
            return RESPONSE_OK;
        }
    }

    @GetMapping("/logout")
    public ResponseEntity logout(HttpSession session) {
        //"user" 세션만 삭제
        //session.invalidate() -> 모든 세션 초기화
        session.removeAttribute("user");

        return RESPONSE_OK;
    }

}
