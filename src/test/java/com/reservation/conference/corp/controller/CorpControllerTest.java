package com.reservation.conference.corp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.reservation.conference.corp.dto.CorpJoinDto;
import com.reservation.conference.corp.service.CorpService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(CorpController.class)
class CorpControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    CorpService corpService;

    @Autowired
    private ObjectMapper objectMapper;

    MockHttpSession mockHttpSession;


    @Test
    @DisplayName("회원가입 성공 테스트")
    void joinApiSuccess() throws Exception {
        CorpJoinDto corpJoinInfo = new CorpJoinDto();
        corpJoinInfo.setId("testId");
        corpJoinInfo.setPassword("12345");
        corpJoinInfo.setCorpName("testName");
        corpJoinInfo.setCorpEmail("testEmail");
        corpJoinInfo.setCorpPhoneNumber("051-466-6131");
        corpJoinInfo.setCorpRegNumber("051-466-604-81-13984");

        mockMvc.perform(post("/corp/join")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(corpJoinInfo)))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("회원 탈퇴 성공 테스트")
    void withdrawCorpApiSuccess() throws Exception {
        //세션에 있는 아이디와 사용자가 입력한 비밀번호를 받는다.
        mockHttpSession = new MockHttpSession();
        mockHttpSession.setAttribute("id", "testId1");

        mockMvc.perform(post("/corp/deleteCorp")
                .param("password","12345")
                .session(mockHttpSession))
                .andExpect(status().isOk());
    }

}
