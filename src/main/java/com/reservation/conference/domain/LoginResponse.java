package com.reservation.conference.domain;

import com.reservation.conference.domain.enums.LoginStatus;
import com.reservation.conference.dto.User;
import lombok.Getter;

@Getter
public class LoginResponse {

    private LoginStatus status;
    private User user;

    public LoginResponse(LoginStatus status, User user) {
        this.status = status;
        this.user = user;
    }

    public LoginResponse(LoginStatus status) {

        this.status = status;
    }
}
