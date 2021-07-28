package com.reservation.conference.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

/*
@Getter
: 자동으로 접근자 메소드를 생성한다.

@Builder
: setter 메소드가 없는 객체를 생성하여 변경 불가능하게 한다.
*/
@Getter
@Builder
@AllArgsConstructor
public class User {

    private String id;
    private String password;
    private String userName;
    private String email;
    private String phoneNumber;
    private String organization;
    private String gender;
    private String dateBirth;

}
