package com.reservation.conference.user.controller;

import com.reservation.conference.user.dto.UserLoginRequestDto;
import com.reservation.conference.utils.HttpResponses;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;


    @Test
    public void loginUserIntegrationTest() {
        final String url = "http://localhost:" + port + "/users/login";
        UserLoginRequestDto user = new UserLoginRequestDto();
        user.setId("testId2");
        user.setPassword("12345");

        ResponseEntity result = testRestTemplate.postForObject(url, user, ResponseEntity.class);
        Assertions.assertEquals(result.getStatusCode(), HttpResponses.RESPONSE_OK);
    }

}
