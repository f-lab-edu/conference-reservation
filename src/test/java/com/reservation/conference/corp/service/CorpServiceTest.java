package com.reservation.conference.corp.service;

import com.reservation.conference.corp.dto.CorpJoinDto;
import com.reservation.conference.corp.service.CorpService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
class CorpServiceTest {

    @Autowired
    CorpService corpService;

    private CorpJoinDto corpJoinTest;


    //테스트 데이터 생성
    @BeforeEach
    public void createTestCorp() {
        corpJoinTest = new CorpJoinDto();
        corpJoinTest.setId("testId1");
        corpJoinTest.setPassword("12345");
        corpJoinTest.setCorpName("testName1");
        corpJoinTest.setCorpEmail("testEmail1");
        corpJoinTest.setCorpPhoneNumber("051-466-6131");
        corpJoinTest.setCorpRegNumber("604-81-13984");
    }


    @Test
    @DisplayName("기업 회원가입 성공")
    void corpJoinSuccess() throws Exception {
        boolean result = corpService.join(corpJoinTest);

        assertThat(result).isEqualTo(true);
    }

    @Test
    @DisplayName("기업 회원가입 실패 - id 미입력")
    void corpJoinFail() throws Exception {
        corpJoinTest.setId(null);

        assertThrows(RuntimeException.class, () -> corpService.join(corpJoinTest));
    }

    @Test
    @DisplayName("기업 회원 탈퇴 성공")
    void corpDeleteMemberSuccess() throws Exception {

        assertThat(corpService.deleteCorp(corpJoinTest.getId(), corpJoinTest.getPassword())).isTrue();
    }

    @Test
    @DisplayName("기업 회원 탈퇴 실패 - 잘못된 비밀번호 입력")
    void corpDeleteMemberFail() throws Exception {

        assertThat(corpService.deleteCorp(corpJoinTest.getId(), "wrongPassword123")).isFalse();
    }

}
