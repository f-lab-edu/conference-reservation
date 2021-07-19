package com.reservation.conference.service;

import com.reservation.conference.dto.User;
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

        if(!checkUserIdExist(userJoinDto.getId())){
            UserJoinDto newUserJoinDto = userJoinDto.builder()
                    .id(userJoinDto.getId())
                    .password(encryptedPassword)
                    .userName(userJoinDto.getUserName())
                    .build();
            userMapper.insertUser(newUserJoinDto);
            return true;
        }else{
            return false;
        }
    }

    // 중복 회원 검증
    public boolean checkUserIdExist(String id){
        boolean isExistId = userMapper.isExistId(id);

        if(isExistId){
            return true;
        }else{
            return false;
        }
    }

    // 회원 탈퇴
    public boolean deleteUser(User currentUserJoinDto, String inputPassword ) throws Exception {
        String encryptedInputPassword = SecurityUtil.encryptPassword(inputPassword);

        if(!encryptedInputPassword.equals(currentUserJoinDto.getPassword())){
            return false;
        }else{
            userMapper.deleteUser(currentUserJoinDto.getId());
            return true;
        }

    }


}