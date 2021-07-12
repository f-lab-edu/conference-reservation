package com.reservation.conference.dto;

import lombok.Builder;
import lombok.Getter;

/*
@Getter
: 자동으로 접근자 메소드를 생성한다.
*/
@Getter
public class UserJoinDto {
    private String id;
    private String password;
    private String userName;

    /*
    @Builder
    : setter 메소드가 없는 객체를 생성하여 변경 불가능하게 한다.
    */
    @Builder
    public UserJoinDto(String id, String password, String userName){
        this.id  = id;
        this.password = password;
        this.userName = userName;
    }

}
