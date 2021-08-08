package com.reservation.conference.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserPasswordUpdateDto {

    private String id;
    private String currentPassword;
    private String newPassword;

}
