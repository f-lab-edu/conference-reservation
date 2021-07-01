package com.reservation.conference.service;

import com.reservation.conference.utils.SecurityUtil;
import org.springframework.stereotype.Service;

@Service
public class UserLoginService {

    /***
     * 유저 로그인 메서드
     */
    public String login(Long id, String password) throws Exception {

        String userPw = SecurityUtil.changePw(password);

        return userPw;
    }


}
