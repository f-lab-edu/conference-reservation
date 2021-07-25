package com.reservation.conference.service;

import com.reservation.conference.dto.*;
import com.reservation.conference.mapper.UserMapper;
import com.reservation.conference.utils.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;

    /***
     * 유저 로그인 메서드
     */
    public UserLoginDto login(String id, String password) throws Exception {

        String encryptPassword = SecurityUtil.encryptPassword(password);
        UserLoginDto userLoginDto = userMapper.findUserByIdAndPassword(id, encryptPassword);

        return userLoginDto;
    }


    // 회원가입
    public boolean join(User user) throws Exception {
        String encryptedPassword = SecurityUtil.encryptPassword(user.getPassword());

        if(!checkUserIdExist(user.getId())){
            User newUser = user.builder()
                    .id(user.getId())
                    .password(encryptedPassword)
                    .build();
            userMapper.insertUser(newUser);
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
    public boolean deleteUser(User currentUser, String inputPassword ) {

        if(!inputPassword.equals(currentUser.getPassword())){
            return false;
        }else{
            userMapper.deleteUser(currentUser.getId());
            return true;
        }

    }

    // 회원 정보 수정
    public int updateUserInfo(UserInfoUpdateDto currentUser){

        UserInfoUpdateDto updateUser = UserInfoUpdateDto.builder()
                .userName(currentUser.getUserName())
                .email(currentUser.getEmail())
                .phoneNumber(currentUser.getPhoneNumber())
                .organization(currentUser.getOrganization())
                .gender(currentUser.getGender())
                .dateBirth(currentUser.getDateBirth())
                .build();

        return userMapper.updateUserInfo(updateUser);

    }

    // 비밀번호 수정
    public boolean updatePassword(UserPasswordUpdateDto userPasswordUpdateDto) throws Exception {

        String currentPassword = userMapper.getPassword(userPasswordUpdateDto.getId());

        // 현재 유저의 패스워드 == 정보 수정을 위해 입력 받은 패스워드
        if(currentPassword.equals(userPasswordUpdateDto.getCurrentPassword())){

            // newPassword 암호화
            String encryptedNewPassword = SecurityUtil.encryptPassword(userPasswordUpdateDto.getNewPassword());

            UserPasswordUpdateDto updatePasswordUser = UserPasswordUpdateDto.builder()
                    .newPassword(encryptedNewPassword)
                    .build();

            userMapper.updatePassword(updatePasswordUser);
            return true;
        }else{
            return false;
        }
    }

}