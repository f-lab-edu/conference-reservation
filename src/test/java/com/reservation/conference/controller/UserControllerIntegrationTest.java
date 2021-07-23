package com.reservation.conference.controller;

import com.reservation.conference.dto.UserLoginDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerIntegrationTest {

    @Autowired
    private TestRestTemplate testRestTemplate;


    @Test
    public void loginUserIntegrationTest() {
        UserLoginDto user = new UserLoginDto();
        user.setId("testId2");
        user.setPassword("12345");

        ResponseEntity result = testRestTemplate.postForObject("/users/login", user, ResponseEntity.class);
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
    }

}
