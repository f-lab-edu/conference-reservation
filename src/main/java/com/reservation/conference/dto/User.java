package com.reservation.conference.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class User {
    private String id;
    private String password;
    private String userName;
}
