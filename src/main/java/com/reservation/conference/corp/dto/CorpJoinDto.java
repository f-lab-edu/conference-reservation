package com.reservation.conference.corp.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CorpJoinDto {

    private String id;
    private String password;
    private String corpName;            //회사명
    private String corpEmail;           //회사 이메일
    private String corpPhoneNumber;     //회사 전화번호
    private String corpRegNumber;       //사업자 등록 번호

}
