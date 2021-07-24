package com.reservation.conference.service;


import com.reservation.conference.dto.*;
import com.reservation.conference.utils.SecurityUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;


/**
 * @SpringBootTest
 * 애플리케이션의 설정, 모든 Bean을 모두 로드하기 때문에 전체적인 Flow 테스트 가능
 * 하지만, 모두 로드하기 때문에 시간이 오래 걸리고 무거움
 **/
@SpringBootTest
class UserServiceTest {

    /**
     * JUnit에서 생성자나 lombok방식으로 DI가 안되는 이유는
     * JUnit이 생성자에 다른 의존성을 주입하려고 먼저 개입하기 때문이다.
     * */
    @Autowired
    UserService userService;

    @Test
    @DisplayName("로그인 성공 테스트")
    void loginCheckSuccess() throws Exception {
        //given
        String userId = "testUser1";
        String userPassword = "1234";
        String testEncryptPassword = SecurityUtil.encryptPassword(userPassword); // 직접 암호화

        //when
        UserLoginDto userLoginDto = userService.login(userId, userPassword);

        //then
        Assertions.assertEquals(testEncryptPassword, userLoginDto.getPassword());
    }

    @Test
    @DisplayName("로그인 실패 테스트")
    void loginCheckFail() throws Exception {
        //given
        String userId = "testUser2";
        String userPassword = "1234";
        String testEncryptPassword = SecurityUtil.encryptPassword("5678");  //틀린 비밀번호 암호화

        //when
        UserLoginDto userLoginDto = userService.login(userId, userPassword);

        //then
        Assertions.assertNotEquals(testEncryptPassword, userLoginDto.getPassword());
    }

    @Test
    @DisplayName("id 중복 없음. 회원가입 성공")
    void joinSuccess() throws Exception {
        // given
        User testUser = User.builder()
                .id("heoella")
                .password("1234")
                .build();

        // when
        boolean result = userService.join(testUser);

        // then
        assertThat(result).isEqualTo(true);

    }

    @Test
    @DisplayName("id 중복. 회원가입 실패")
    void joinFail() throws Exception {
        // given
        User testUser = User.builder()
                .id("heo")
                .password("password1234")
                .build();

        // when
        boolean result = userService.join(testUser);

        // then
        assertThat(result).isEqualTo(false);
    }

    @Test
    @DisplayName("비밀번호를 올바르게 입력한 경우 회원 탈퇴 성공")
    void deleteUserSuccess() throws Exception {
        // given
        User testUser = User.builder()
                .id("heo")
                .password("password1234")
                .build();


        boolean result = userService.deleteUser(testUser, "password1234");

        assertThat(result).isEqualTo(true);
    }

    @Test
    @DisplayName("틀린 비밀번호를 입력한 경우 회원 탈퇴 실패")
    void deleteUserFail()  {
        // given
        User testUser = User.builder()
                .id("heo")
                .password("password1234")
                .build();

        boolean result = userService.deleteUser(testUser, "1234");

        assertThat(result).isEqualTo(false);
    }

    @Test
    @DisplayName("회원 정보 수정 성공")
    void updateUserInfoSuccess(){
        // given
        UserInfoUpdateDto testUser = UserInfoUpdateDto.builder()
                .userName("updateElla")
                .email("ella@gmail.com")
                .phoneNumber("010-0000-1111")
                .organization("f-lab")
                .gender("WOMAN")
                .dateBirth("000101")
                .build();

        // when
        boolean result = userService.updateUserInfo(testUser);

        // then
        assertThat(result).isEqualTo(true);

    }



    @Test
    @DisplayName("비밀번호 수정")
    void updatePasswordSucceess() throws Exception {
        // given
        UserPasswordUpdateDto userPasswordUpdateDto = UserPasswordUpdateDto.builder()
                .id("heo")
                .currentPassword("password1234")
                .newPassword("123456")
                .build();

        // when
        boolean result = userService.updatePassword(userPasswordUpdateDto);

        // then
        assertThat(result).isEqualTo(true);
    }
}