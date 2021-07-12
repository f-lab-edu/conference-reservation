package com.reservation.conference.service;

import com.reservation.conference.dto.UserJoinDto;
import com.reservation.conference.dto.UserLoginDto;
import com.reservation.conference.mapper.UserMapper;
import com.reservation.conference.utils.SecurityUtil;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserMapper userMapper;

    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    /***
     * 유저 로그인 메서드
     */
    public UserLoginDto login(String id, String password) throws Exception {

        String encryptPassword = SecurityUtil.encryptPassword(password);
        UserLoginDto userLoginDto = userMapper.findUserByIdAndPassword(id, encryptPassword);

        return userLoginDto;
    }

    // 회원가입
    public boolean join(UserJoinDto userJoinDto) throws Exception {
        String encryptedPassword = SecurityUtil.encryptPassword(userJoinDto.getPassword());

        if(checkUserIdExist(userJoinDto.getId())){
            UserJoinDto newUser = userJoinDto.builder()
                    .id(userJoinDto.getId())
                    .password(encryptedPassword)
                    .userName(userJoinDto.getUserName())
                    .build();
            userMapper.insertUser(newUser);
            return true;
        }else{
            return false;
        }
    }

    // 중복 회원 검증
    // 이지만 일단은 username이 null인지 아닌지 판단
   public boolean checkUserIdExist(String id){

        if(id != null){
            return true;
        }else{
            return false;
        }
    }
  
}
