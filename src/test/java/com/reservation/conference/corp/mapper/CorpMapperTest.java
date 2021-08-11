package com.reservation.conference.corp.mapper;

import com.reservation.conference.corp.dto.CorpJoinDto;
import com.reservation.conference.corp.mapper.CorpMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


@MybatisTest
class CorpMapperTest {

    @Autowired
    CorpMapper corpMapper;

    private CorpJoinDto corpJoinTest;


    //테스트 데이터 생성
    @BeforeEach
    public void createTestCorp() {
        corpJoinTest = new CorpJoinDto();
        corpJoinTest.setId("minCorp");
        corpJoinTest.setPassword("corporation");
        corpJoinTest.setCorpName("minkeun");
        corpJoinTest.setCorpEmail("min@gmail.com");
        corpJoinTest.setCorpPhoneNumber("051-466-6131");
        corpJoinTest.setCorpRegNumber("604-81-13984");
    }

    @Test
    @DisplayName("기업 회원가입 성공 테스트")
    public void corpJoinSuccessTest() {
        //회원가입 성공 시, true 반환함
        assertThat(corpMapper.insertCorp(corpJoinTest)).isTrue();
    }

    @Test
    @DisplayName("기업 회원가입 실패 테스트 - id 미입력")
    public void corpJoinFailTest() {
        corpJoinTest.setId(null);

        //무결성 제약 조건이 위반될 때 해당 예외가 발생함.
        //RuntimeException의 상속을 받고 있음.
        assertThrows(DataIntegrityViolationException.class, () -> corpMapper.insertCorp(corpJoinTest));
    }

    @Test
    @DisplayName("기업 회원 탈퇴 성공 테스트")
    public void corpDeleteSuccess() {
        assertThat(corpMapper.deleteCorp(corpJoinTest.getId())).isTrue();
    }

    @Test
    @DisplayName("기업 회원 탈퇴 실패 테스트 - id 없음")
    public void corpDeleteFail() {
        assertThat(corpMapper.deleteCorp(null)).isFalse();
    }

}
