package com.reservation.conference.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

/*
@Builder : setter 메소드가 없는 객체를 생성하여 변경 불가능하게 한다.
*/
@Getter
@Builder
@AllArgsConstructor
public class User {

    private String id;
    private String password;
    private String userName;
    private String userEmail;
    private String userPhoneNumber;
    private String userOrganization;
    private String userGender;
    private String userDateBirth;

}
