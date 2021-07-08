package com.reservation.conference.service;

import com.reservation.conference.dto.UserJoinDTO;
import com.reservation.conference.mapper.UserMapper;
import com.reservation.conference.utils.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.reservation.conference.dto.UserJoinDTO.*;

@Service
public class UserService {

//    @Autowired
//    private UserMapper userMapper;

    // 회원가입
    public boolean join(UserJoinDTO userJoinDTO) throws Exception {
        String encryptedPassword = SecurityUtil.encryptPassword(userJoinDTO.getPassword());

        if(checkUserIdExist(userJoinDTO.getUserName())){
            UserJoinDTO newUser = builder()
                    .Id(userJoinDTO.getId())
                    .userName(userJoinDTO.getUserName())
                    .password(encryptedPassword)
                    .build();

//            userMapper.insertUser(newUser);
            return true;
        }else{
            return false;
        }
    }

    // 중복 회원 검증
    // 이지만 일단은 username이 null인지 아닌지 판단
   public boolean checkUserIdExist(String userName){

        if(userName != null){
            return true;
        }else{
            return false;
        }
    }
}