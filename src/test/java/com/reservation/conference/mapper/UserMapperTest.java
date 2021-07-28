package com.reservation.conference.mapper;

import com.reservation.conference.dto.User;
import com.reservation.conference.dto.UserInfoUpdateDto;
import com.reservation.conference.dto.UserLoginDto;
import static org.assertj.core.api.Assertions.assertThat;

import com.reservation.conference.dto.UserPasswordUpdateDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;

@MybatisTest
class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    @DisplayName("테스트 데이터 확인 성공")
    public void testConfirmSuccess() {
        UserLoginDto user = userMapper.findUserByIdAndPassword("heo", "password1234");
        assertThat(user.getUserName()).isEqualTo("helo-ella");
    }

    @Test
    @DisplayName("테스트 데이터 확인 실패 - 유저이름 불일치")
    public void testConfirmFail1() {
        UserLoginDto user = userMapper.findUserByIdAndPassword("min", "conference");
        assertThat(user.getUserName()).isNotEqualTo("helo-ella");
    }

    @Test
    @DisplayName("테스트 데이터 확인 실패 - 비밀번호 불일치")
    public void testConfirmFail2() {
        UserLoginDto user = userMapper.findUserByIdAndPassword("min", "password1234");
        assertThat(user).isNull();
    }

    @Test
    @DisplayName("회원가입 성공 - 중복 아이디 없음")
    public void insertUserSuccess(){
        User testUser = User.builder()
                .id("yeseul")
                .password("1234")
                .userName("heoella")
                .email("heo@gmail.com")
                .phoneNumber("010-1111-2222")
                .organization("f-lab")
                .gender("WOMAN")
                .dateBirth("950307")
                .build();

        int result = userMapper.insertUser(testUser);

        assertThat(result).isEqualTo(1);

    }

    @Test
    @DisplayName("회원탈퇴 성공")
    public void deleteUserSuccess(){
        int result = userMapper.deleteUser("heo");
        assertThat(result).isNotEqualTo(0);
    }

    @Test
    @DisplayName("회원탈퇴 실패")
    public void deleteUserFail(){
        int result = userMapper.deleteUser("yeseul");
        assertThat(result).isEqualTo(0);
    }

    @Test
    @DisplayName("패스워드 수정 성공")
    public void updatePasswordSuccess(){
        // given
        UserPasswordUpdateDto updatePasswordUser = UserPasswordUpdateDto.builder()
                .id("heo")
                .currentPassword("password1234")
                .newPassword("1234")
                .build();

        int result = userMapper.updatePassword(updatePasswordUser);
        assertThat(result).isNotEqualTo(0);

    }

    @Test
    @DisplayName("회원정보 수정 성공")
    public void updateUserInfoSuccess(){
        // given
        UserInfoUpdateDto updateUserInfo = UserInfoUpdateDto.builder()
                .id("heo")
                .userName("ella")
                .email("heo@naver.com")
                .phoneNumber("010-9999-8888")
                .organization("f-lab")
                .gender("WOMAN")
                .dateBirth("950227")
                .build();

        int result = userMapper.updateUserInfo(updateUserInfo);
        assertThat(result).isNotEqualTo(0);

    }
}
