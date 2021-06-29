package com.reservation.conference.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    private Long Id;
    private String userName;
    private String password;
}
