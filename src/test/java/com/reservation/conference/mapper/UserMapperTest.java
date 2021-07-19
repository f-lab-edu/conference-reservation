package com.reservation.conference.mapper;

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
  @DisplayName("테스트데이터 확인")
  public void m1() {
    UserLoginDto user = userMapper.findUserByIdAndPassword("heo", "password1234");
    assertThat(user.getUserName()).isEqualTo("helo-ella");
  }

}