package com.reservation.conference.controller;

import com.reservation.conference.dto.UserLoginDto;
import com.reservation.conference.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.reservation.conference.utils.HttpResponses.*;


@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestParam("id") String id, @RequestParam("password") String password) throws Exception {

        UserLoginDto userInfo = userService.login(id, password);

        if(userInfo == null) {
            return RESPONSE_UNAUTHORIZED;
        }else {
            return RESPONSE_OK;
        }
    }

}
