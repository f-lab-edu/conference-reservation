package com.reservation.conference.user.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserPasswordUpdateDto {

    private String id;
    private String currentPassword;
    private String newPassword;

}
