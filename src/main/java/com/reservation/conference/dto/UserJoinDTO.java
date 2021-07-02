package com.reservation.conference.dto;

import lombok.Builder;
import lombok.Getter;

/*
@Getter
: 자동으로 접근자 메소드를 생성한다.
*/
@Getter
public class UserJoinDTO {
    private Long Id; // 자동으로 생성하는 systemId
    private String userName;
    private String password;

    /*
    @Builder
    : setter 메소드가 없는 객체를 생성하여 변경 불가능하게 한다.
    */
    @Builder
    public UserJoinDTO(Long Id, String userName, String password){
        this.Id  = Id;
        this.userName = userName;
        this.password = password;
    }

}
