package com.reservation.conference.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class UserUpdateParam {
    private String id;
    private String userName;
}
