package com.reservation.conference.user.service;


<<<<<<< HEAD:src/test/java/com/reservation/conference/service/UserServiceTest.java
import com.reservation.conference.dto.User;
import com.reservation.conference.dto.UserPasswordUpdateDto;
import com.reservation.conference.exception.UserNotFoundException;
=======

import com.reservation.conference.user.dto.User;
import com.reservation.conference.user.dto.UserLoginResponseDto;
import com.reservation.conference.user.dto.UserPasswordUpdateDto;
>>>>>>> feature/9:src/test/java/com/reservation/conference/user/service/UserServiceTest.java
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

<<<<<<< HEAD:src/test/java/com/reservation/conference/service/UserServiceTest.java
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
=======
import static org.assertj.core.api.Assertions.assertThat;
>>>>>>> feature/9:src/test/java/com/reservation/conference/user/service/UserServiceTest.java


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

<<<<<<< HEAD:src/test/java/com/reservation/conference/service/UserServiceTest.java
    /**
     * 유저 테스트 변수
     */
    private String testUserId = "testId1";
    private String testUserPassword = "12345";
    private String testUserWrongPassword = "99999";
=======
    // Test Values
    private final String testUserId = "testId1";
    private final String testUserPassword = "12345";
    private final String testUserWrongPassword = "99999";
>>>>>>> feature/9:src/test/java/com/reservation/conference/user/service/UserServiceTest.java

    @Test
    @DisplayName("DB 유저 로그인 성공")
    void loginCheckSuccess() throws Exception {
<<<<<<< HEAD:src/test/java/com/reservation/conference/service/UserServiceTest.java

        User tsetUserInfo = userService.login(testUserId, testUserPassword);
=======
        //when
        UserLoginResponseDto tsetUserInfo = userService.login(testUserId, testUserPassword);
>>>>>>> feature/9:src/test/java/com/reservation/conference/user/service/UserServiceTest.java

        assertThat(testUserId).isEqualTo(tsetUserInfo.getId());
    }

    @Test
    @DisplayName("DB 유저 로그인 실패_비밀번호 불일치")
    void loginCheckFail() throws Exception {

        //"해당 유저의 로그인 정보가 존재하지 않습니다."
        assertThrows(UserNotFoundException.class, () -> userService.login(testUserId, testUserWrongPassword));
    }

    @Test
<<<<<<< HEAD:src/test/java/com/reservation/conference/service/UserServiceTest.java
    @DisplayName("DB 회원가입 성공_아이디 중복 없음")
    void joinSuccess() throws Exception {
        // given
        User testUser = User.builder()
                .id("heoella")
                .password("password1234")
                .userName("heo-ella")
                .userEmail("heo@f-lab.com")
                .userPhoneNumber("010-1111-1234")
                .userOrganization( "f-lab")
                .userGender("Woman")
                .userDateBirth("1995-03-07")
                .build();

        // when
        boolean result = userService.join(testUser);

        // then
        assertThat(result).isEqualTo(true);
    }

    @Test
    @DisplayName("DB 회원가입 실패_아이디 중복")
    void joinFail() throws Exception {
        // given
        User testUser = User.builder()
                .id("heo")
                .password("password1234")
                .userName("heo-ella")
                .userEmail("heo@f-lab.com")
                .userPhoneNumber("010-1111-1234")
                .userOrganization( "f-lab")
                .userGender("Woman")
                .userDateBirth("1995-03-07")
                .build();

        // when
        boolean result = userService.join(testUser);

        // then
        assertThat(result).isEqualTo(false);
    }

    @Test
=======
>>>>>>> feature/9:src/test/java/com/reservation/conference/user/service/UserServiceTest.java
    @DisplayName("DB 회원탈퇴 성공_비밀번호 일치")
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
    @DisplayName("DB 회원탈퇴 실패_비밀번호 불일치")
    void deleteUserFail() throws Exception {
        // given
        User testUser = User.builder()
                .id("heo")
                .password("password1234")
                .build();

        boolean result = userService.deleteUser(testUser, "wrongPassword");

        assertThat(result).isEqualTo(false);
    }

    @Test
    @DisplayName("DB 회원 정보수정 성공_모든 필드에 값이 입력 되었을 경우")
    void updateUserInfoSuccess(){
        // given
        User testUser = User.builder()
                .userName("updateElla")
                .userEmail("ella@gmail.com")
                .userPhoneNumber("010-0000-1111")
                .userOrganization("f-lab")
                .userGender("WOMAN")
                .userDateBirth("000101")
                .build();

        // when
        int result = userService.updateUserInfo("heo", testUser);

        // then
        assertThat(result).isEqualTo(1);
    }

    @Test
    @DisplayName("DB 비밀번호 수정 성공")
    void updatePasswordSucceess() throws Exception {
        // given
        UserPasswordUpdateDto userPasswordUpdateDto = UserPasswordUpdateDto.builder()
                .id("heo")
                .currentPassword("password1234")
                .newPassword("123456")
                .build();

        // when
        boolean result = userService.updatePassword("heo", userPasswordUpdateDto);

        // then
        assertThat(result).isEqualTo(true);
    }

}
