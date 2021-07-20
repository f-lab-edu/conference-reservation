package com.reservation.conference.service;


import com.reservation.conference.dto.UserJoinDto;
import com.reservation.conference.dto.UserLoginDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;


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

    // Test Values
    private String testUserId = "testId1";
    private String testUserPassword = "12345";
    private String testUserWrongPassword = "99999";

    @Test
    @DisplayName("DB 유저 로그인 성공")
    void loginCheckSuccess() throws Exception {
        //when
        UserLoginDto tsetUserInfo = userService.login(testUserId, testUserPassword);

        //then
        assertThat(testUserId).isEqualTo(tsetUserInfo.getId());
    }

    @Test
    @DisplayName("DB 유저 로그인 실패_비밀번호 불일치")
    void loginCheckFail() throws Exception {

        assertThat(userService.login(testUserId, testUserWrongPassword)).isNull();
    }

    @Test
    @DisplayName("회원가입 성공")
    void joinSuccess() throws Exception {
        // given
        UserJoinDto userJoinDto = UserJoinDto.builder()
                .id("testUser3")
                .password("1234")
                .userName("heoella")
                .build();

        // when
        boolean result = userService.join(userJoinDto);

        // then
        assertThat(result).isEqualTo(true);

    }

    @Test
    @DisplayName("회원가입 실패")
    void joinFail() throws Exception {
        // given
        UserJoinDto userJoinDto = UserJoinDto.builder()
                .id(null)
                .password("12345")
                .userName("heoella")
                .build();

        // when
        boolean result = userService.join(userJoinDto);

        // then
        assertThat(result).isEqualTo(false);
    }

}
