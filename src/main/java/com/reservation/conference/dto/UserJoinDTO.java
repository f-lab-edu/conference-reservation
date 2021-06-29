package com.reservation.conference.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class UserJoinDTO {

    private Long Id; // system에서 부여
    private String userName;
    private String password;

    @Builder
    public UserJoinDTO(Long Id, String userName, String password){
        this.Id = Id;
        this.userName = userName;
        this.password = password;
    }


}
