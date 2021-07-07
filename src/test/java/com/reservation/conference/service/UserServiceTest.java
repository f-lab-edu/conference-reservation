package com.reservation.conference.service;

import com.reservation.conference.dto.UserJoinDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class UserJoinServiceTest {

    UserJoinService userJoinService = new UserJoinService();

    private static Long seq= 0L;

    @DisplayName("성공적으로 회원가입이 된 경우")
    @Test
    void signUpSuccess() throws Exception {
        // given
        UserJoinDTO userJoinDTO = UserJoinDTO.builder()
                .Id(++seq)
                .userName("heoella")
                .password("hi")
                .build();

        // when
        boolean result = userJoinService.join(userJoinDTO);

        // then
        assertThat(result).isEqualTo(true);

    }



}
