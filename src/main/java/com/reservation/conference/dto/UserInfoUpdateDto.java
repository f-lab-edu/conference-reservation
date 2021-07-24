package com.reservation.conference.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class UserInfoUpdateDto {

    private String id;
    private String userName;
    private String email;
    private String phoneNumber;
    private String organization;
    private String gender;
    private String dateBirth;

}
