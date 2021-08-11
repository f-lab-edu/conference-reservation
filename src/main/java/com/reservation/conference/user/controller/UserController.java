package com.reservation.conference.user.controller;

import com.reservation.conference.domain.LoginResponse;
import com.reservation.conference.domain.enums.LoginStatus;
<<<<<<< HEAD:src/main/java/com/reservation/conference/controller/UserController.java
import com.reservation.conference.dto.User;
import com.reservation.conference.dto.UserLoginDto;
import com.reservation.conference.dto.UserPasswordUpdateDto;
import com.reservation.conference.service.UserService;
=======
import com.reservation.conference.user.dto.User;
import com.reservation.conference.user.dto.UserLoginRequestDto;
import com.reservation.conference.user.dto.UserLoginResponseDto;
import com.reservation.conference.user.dto.UserPasswordUpdateDto;
import com.reservation.conference.user.service.UserService;
import lombok.NonNull;
>>>>>>> feature/9:src/main/java/com/reservation/conference/user/controller/UserController.java
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

import static com.reservation.conference.utils.HttpResponses.RESPONSE_CREATED;
import static com.reservation.conference.utils.HttpResponses.RESPONSE_OK;


@RestController
@RequiredArgsConstructor    //초기화 되지 않는 final필드에 생성자 생성
@RequestMapping("/users")
public class UserController {

    private final UserService userService;


    @PostMapping("/login")
<<<<<<< HEAD:src/main/java/com/reservation/conference/controller/UserController.java
    public ResponseEntity<LoginResponse> login(@RequestBody UserLoginDto userLoginInfo, HttpSession session) throws Exception {

        LoginResponse loginResponse;
        ResponseEntity<LoginResponse> responseEntity;
        User userInfo = userService.login(userLoginInfo.getId(), userLoginInfo.getPassword());
=======
    public ResponseEntity<LoginResponse> login(@RequestBody @NonNull UserLoginRequestDto userLoginRequestDto, HttpSession session) throws Exception {

        LoginResponse loginResponse;
        ResponseEntity<LoginResponse> responseEntity;
        UserLoginResponseDto userInfo = userService.login(userLoginRequestDto.getId(), userLoginRequestDto.getPassword());
>>>>>>> feature/9:src/main/java/com/reservation/conference/user/controller/UserController.java

        if(userInfo == null) {
            loginResponse = new LoginResponse(LoginStatus.FAIL);
            responseEntity = new ResponseEntity<>(loginResponse, HttpStatus.UNAUTHORIZED);
        }else {
            //로그인 성공 시, 세션에 userInfo 저장
            session.setAttribute("user", userInfo);
            loginResponse = new LoginResponse(LoginStatus.SUCCESS, userInfo);
            responseEntity = new ResponseEntity<>(loginResponse, HttpStatus.OK);
        }

        return responseEntity;
    }

    @GetMapping("/logout")
    public ResponseEntity logout(@NonNull HttpSession session) {
        //"user" 세션만 삭제
        //session.invalidate() -> 모든 세션 초기화
        session.removeAttribute("user");

        return RESPONSE_OK;
    }

    @PostMapping("/join")
    public ResponseEntity join(@RequestBody User user) throws Exception {
       userService.join(user);

       return RESPONSE_CREATED;
    }


    @PutMapping("/{id}/updateInfo")
    public ResponseEntity updateUserInfo(@RequestParam String id, @RequestBody User user){
        userService.updateUserInfo(id, user);

        return RESPONSE_OK;

    }

    @PutMapping("/{id}/updatePassword")
    public ResponseEntity updatePassword(@RequestParam String id, @RequestBody UserPasswordUpdateDto userPasswordUpdateDto) throws Exception {
        userService.updatePassword(id, userPasswordUpdateDto);

        return RESPONSE_OK;

    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@RequestBody User user, @RequestParam String password) throws Exception {
        userService.deleteUser(user, password);

        return RESPONSE_OK;
    }

}
