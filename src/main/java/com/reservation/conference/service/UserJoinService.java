package com.reservation.conference.service;

import com.reservation.conference.dto.UserJoinDTO;
import org.springframework.stereotype.Service;

@Service
public class UserJoinService {

    // 회원가입
    public UserJoinDTO join(UserJoinDTO userJoinDTO){
        if (checkUserIdExist(userJoinDTO.getUserName())){
            return userJoinDTO;
        }else{
            return null;
        }
    }

    // 중복 회원 검증
    public boolean checkUserIdExist(String userName){

        // boolean isExistUserId = userMapper.isExistUserId(userId);
        if(userName != null){ //isExistUserId
            return true;
        }else{
            return false;
        }
    }
}