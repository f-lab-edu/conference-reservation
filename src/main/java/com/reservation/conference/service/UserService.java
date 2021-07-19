package com.reservation.conference.service;

import com.reservation.conference.dto.*;
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

    // 회원 정보 수정
    public void updateUserInfo(User currentUser, UserUpdateParam userUpdateParam){

        User updateUserInfo = User.builder()
                .id(currentUser.getId())
                .password(currentUser.getPassword())
                .userName(userUpdateParam.getUserName())
                .build();

        userMapper.updateUserInfo(updateUserInfo);

    }

    // 유저 패스워드 수정
    public boolean updatePassword(User currentUser, UserPasswordUpdateParam userPasswordUpdateParam) throws Exception {

        // 현재 유저의 패스워드 == 정보 수정을 위해 입력 받은 패스워드
        if(currentUser.getPassword().equals(userPasswordUpdateParam.getCurrentPassword())){

            // newPassword 암호화
            String encryptedNewPassword = SecurityUtil.encryptPassword(userPasswordUpdateParam.getNewPassword());

            User updatePasswordUser = User.builder()
                    .id(currentUser.getId())
                    .password(encryptedNewPassword)
                    .userName(currentUser.getUserName())
                    .build();

            userMapper.updatePassword(updatePasswordUser);
            return true;
        }else{
            return false;
        }
    }

}