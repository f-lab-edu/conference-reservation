package com.reservation.conference.controller;

import com.reservation.conference.dto.User;
import com.reservation.conference.dto.UserInfoUpdateDto;
import com.reservation.conference.dto.UserLoginDto;
import com.reservation.conference.dto.UserPasswordUpdateDto;
import com.reservation.conference.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import static com.reservation.conference.utils.HttpResponses.*;



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

    // 회원 가입
    @PostMapping("/join")
    public ResponseEntity join(@RequestBody User user) throws Exception {
       userService.join(user);
       return RESPONSE_CREATED; // 요청에 따른 새로운 리소스 생성 성공
    }

    // 유저 정보수정
    @PutMapping("/{id}/updateInfo")
    public ResponseEntity updateUserInfo(@RequestParam String id, @RequestBody UserInfoUpdateDto userInfoUpdateDto){

        userService.updateUserInfo(id, userInfoUpdateDto);
        return RESPONSE_OK; // 요청 성공

    }

    // 비밀번호 변경
    @PutMapping("/{id}/updatePassword")
    public ResponseEntity updatePassword(@RequestParam String id, @RequestBody UserPasswordUpdateDto userPasswordUpdateDto) throws Exception {

        userService.updatePassword(id, userPasswordUpdateDto);
        return RESPONSE_OK; // 요청 성공

    }

    // 회원탈퇴
    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@RequestBody User user, @RequestParam String password){
        userService.deleteUser(user, password);
        return RESPONSE_OK;
    }

}
