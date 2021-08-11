package com.reservation.conference.user.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.reservation.conference.user.dto.User;
import com.reservation.conference.user.dto.UserLoginRequestDto;
import com.reservation.conference.user.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(UserController.class) //-> @Service, @Component, @Repository 사용 불가
public class UserControllerTest {

    @Autowired
    MockMvc mockMvc;    //웹API를 테스트하기 위해서 사용

    @MockBean
    UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    MockHttpSession httpSession = new MockHttpSession();


    @Test
    @DisplayName("로그인 API 성공 테스트")
    void loginSuccess() throws Exception {
        UserLoginRequestDto userLoginInfo = new UserLoginRequestDto();
        userLoginInfo.setId("testId1");
        userLoginInfo.setPassword("12345");

        mockMvc.perform(post("/users/login")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userLoginInfo))
                .session(httpSession))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("로그아웃 성공 테스트 - 200(HttpStatus) 반환")
    void logoutSuccess() throws Exception {

        mockMvc.perform(get("/users/logout")
                .session(httpSession))
                .andExpect(status().isOk());

        //"user" 세션이 아직 존재하는지 확인
        Assertions.assertNull(httpSession.getAttribute("user"));
    }

}
