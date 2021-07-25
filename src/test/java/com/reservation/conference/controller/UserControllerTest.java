package com.reservation.conference.controller;


import com.reservation.conference.service.UserService;
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


@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    UserService userService;


    @Test
    @DisplayName("로그인 API 진입 성공 테스트")
    void loginSuccess() throws Exception {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("id", "test");
        map.add("password", "1234");

        mockMvc.perform(post("/users/login")
                .params(map))       // 키=값의 파라미터 전달(여러 개는 params(), 한 개는 param())
                .andExpect(status().isUnauthorized());  // 해당 테스트 데이터가 없으므로 401 반환
    }

    @Test
    @DisplayName("로그인 API 진입 실패 테스트")
    void loginFail() throws Exception {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("id", "test");

        mockMvc.perform(post("/users/login")
                .params(map))
                .andExpect(status().isBadRequest());    //password 미입력으로 400 반환
    }

}

