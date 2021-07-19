package com.reservation.conference.service;


import com.reservation.conference.dto.*;
import com.reservation.conference.utils.SecurityUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
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

    User testUser;

    User encryptedUser;

    @BeforeEach
    public void setUp() throws Exception {
        testUser = User.builder()
                .id("testUser1")
                .password("1234")
                .userName("heoella")
                .build();

        encryptedUser = User.builder()
                .id("testUser2")
                .password(SecurityUtil.encryptPassword("1234"))
                .userName("heoella")
                .build();
    }

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
        UserJoinDto userJoinDto = UserJoinDto.builder()
                .id("testUser2")
                .password("1234")
                .userName("heoella")
                .build();

        // when
        boolean result = userService.join(userJoinDto);

        // then
        assertThat(result).isEqualTo(true);

    }

    @Test
    @DisplayName("id 중복. 회원가입 실패")
    void joinFail() throws Exception {
        // given
        UserJoinDto userJoinDto = UserJoinDto.builder()
                .id("testUser1")
                .password("1234")
                .userName("heoella")
                .build();

        // when
        boolean result = userService.join(userJoinDto);

        // then
        assertThat(result).isEqualTo(false);
    }

    @Test
    @DisplayName("비밀번호를 올바르게 입력한 경우 회원 탈퇴 성공")
    void deleteUserSuccess() throws Exception {

        boolean result = userService.deleteUser(encryptedUser, "1234");

        assertThat(result).isEqualTo(true);
    }

    @Test
    @DisplayName("틀린 비밀번호를 입력한 경우 회원 탈퇴 실패")
    void deleteUserFail() throws Exception {
        boolean result = userService.deleteUser(encryptedUser, "12345");

        assertThat(result).isEqualTo(false);
    }

    @Test
    @DisplayName("회원 정보 수정 성공")
    void updateUserInfoSuccess(){
        // given
        UserUpdateParam userUpdateParam = UserUpdateParam.builder()
                .id(testUser.getId())
                .userName("updateElla")
                .build();

        // when
        userService.updateUserInfo(testUser, userUpdateParam);

    }

    // 회원 정보 수정 테스트가 실패할 경우
    // 1. null인 필드가 있을 경우


    @Test
    @DisplayName("비밀번호 수정")
    void updatePasswordSucceess() throws Exception {
        // given
        UserPasswordUpdateParam userPasswordUpdateParam = UserPasswordUpdateParam.builder()
                .id(testUser.getId())
                .currentPassword(testUser.getPassword())
                .newPassword("123456")
                .build();

        // when
        boolean result = userService.updatePassword(testUser, userPasswordUpdateParam);

        // then
        assertThat(result).isEqualTo(true);
    }
}