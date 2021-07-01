package com.reservation.conference.service;

import com.reservation.conference.utils.SecurityUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @SpringBootTest
 * 애플리케이션의 설정, 모든 Bean을 모두 로드하기 때문에 전체적인 Flow 테스트 가능
 * 하지만, 모두 로드하기 때문에 시간이 오래 걸리고 무거움
 * */
@SpringBootTest
class UserLoginServiceTest {

    @Autowired
    UserLoginService userLoginService;


    @Test
    @DisplayName("로그인 성공 테스트")
    void loginCheckSuccess() throws Exception {
        //given
        Long userId = 1L;
        String userPw = "1234";
        String chkSHA256Pw = SecurityUtil.changePw(userPw); // 직접 암호화

        //when
        String userSHA256Pw = userLoginService.login(userId, userPw);

        //then
        Assertions.assertEquals(chkSHA256Pw, userSHA256Pw);
    }

    @Test
    @DisplayName("로그인 실패 테스트")
    void loginCheckFail() throws Exception {
        //given
        Long userId = 1L;
        String userPw = "1234";
        String chkSHA256Pw = SecurityUtil.changePw("5678");  //틀린 비밀번호 암호화

        //when
        String userSHA256Pw = userLoginService.login(userId, userPw);

        //then
        Assertions.assertNotEquals(chkSHA256Pw, userSHA256Pw);
    }

}