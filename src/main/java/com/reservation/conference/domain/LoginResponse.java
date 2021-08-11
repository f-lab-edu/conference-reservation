package com.reservation.conference.domain;

import com.reservation.conference.domain.enums.LoginStatus;
import com.reservation.conference.user.dto.UserLoginResponseDto;
import lombok.Getter;

@Getter
public class LoginResponse {

    private LoginStatus status;
    private UserLoginResponseDto user;

    public LoginResponse(LoginStatus status, UserLoginResponseDto user) {
        this.status = status;
        this.user = user;
    }

    public LoginResponse(LoginStatus status) {
        this.status = status;
    }

}
