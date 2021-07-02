package com.reservation.conference.service;

import com.reservation.conference.dto.UserJoinDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.*;

class UserJoinServiceTest {

    @Autowired
    UserJoinService userJoinService = new UserJoinService();

    private static Long seq= 0L;

    @DisplayName("아이디가 중복이 아닐 경우")
    @Test
    void checkUserIdDuplicate(){
        // given
        UserJoinDTO user1 = new UserJoinDTO(++seq, "heoella", "heoella");

        // when
        UserJoinDTO result = userJoinService.join(user1);

        // then
        assertThat(result).isEqualTo(user1);

    }

}
