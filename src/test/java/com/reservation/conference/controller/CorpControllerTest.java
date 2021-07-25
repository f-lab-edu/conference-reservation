package com.reservation.conference.controller;

import com.reservation.conference.service.CorpService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(CorpController.class)
class CorpControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    CorpService corpService;


    @Test
    @DisplayName("회원가입 API 진입 성공 테스트")
    void joinApiSuccess() throws Exception {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("id", "testId");
        map.add("password", "1234");

        mockMvc.perform(post("/corp/join")
                .params(map))
                .andExpect(status().isBadRequest());
    }

}
