package com.reservation.conference.mapper;

import com.reservation.conference.dto.User;
import com.reservation.conference.dto.UserLoginDto;
import static org.assertj.core.api.Assertions.assertThat;
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

}
