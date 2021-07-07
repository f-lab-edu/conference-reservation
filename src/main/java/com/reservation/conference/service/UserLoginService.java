package com.reservation.conference.service;

import com.reservation.conference.utils.SecurityUtil;
import org.springframework.stereotype.Service;

@Service
public class UserLoginService {

    /***
     * 유저 로그인 메서드 (암호화)
     * TODO : 로그인 기능 추가 예정
     */
    public String login(Long id, String password) throws Exception {

        String encryptPassword = SecurityUtil.encryptPassword(password);

        return encryptPassword;
    }


}
