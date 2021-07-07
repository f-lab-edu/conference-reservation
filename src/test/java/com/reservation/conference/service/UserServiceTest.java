package com.reservation.conference.service;

import com.reservation.conference.dto.UserJoinDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class UserServiceTest {

    UserService userService = new UserService();

    private static Long seq= 0L;

    @DisplayName("회원가입 성공")
    @Test
    void joinSuccess() throws Exception {
        // given
        UserJoinDTO userJoinDTO = UserJoinDTO.builder()
                .Id(++seq)
                .userName("heoella")
                .password("hi")
                .build();

        // when
        boolean result = userService.join(userJoinDTO);

        // then
        assertThat(result).isEqualTo(true);

    }

    @DisplayName("회원가입 실패")
    @Test
    void joinFail() throws Exception {
        // given
        UserJoinDTO userJoinDTO = UserJoinDTO.builder()
                .Id(++seq)
                .userName(null)
                .password("hi")
                .build();

        // when
        boolean result = userService.join(userJoinDTO);

        // then
        assertThat(result).isEqualTo(false);
    }

}
