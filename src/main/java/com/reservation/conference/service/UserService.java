package com.reservation.conference.service;

import com.reservation.conference.dto.*;
import com.reservation.conference.exception.DeleteErrorException;
import com.reservation.conference.exception.DuplicationIdException;
import com.reservation.conference.exception.InsertErrorException;
import com.reservation.conference.exception.WrongPasswordException;
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

        boolean checkId = checkUserIdExist(user.getId());
        if(checkId) {
            throw new DuplicationIdException("중복된 아이디 입니다.");
        }

        String encryptedPassword = SecurityUtil.encryptPassword(user.getPassword());

        User newUser = user.builder()
                .id(user.getId())
                .password(encryptedPassword)
                .userName(user.getUserName())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .organization(user.getOrganization())
                .gender(user.getGender())
                .dateBirth(user.getDateBirth())
                .build();

        int insertResult = userMapper.insertUser(newUser);
        if(insertResult != 1) {
            throw new InsertErrorException("insert하는 도중에 에러가 발생하였습니다.");
        }

        return true;
    }

    // 중복 회원 검증
    public boolean checkUserIdExist(String id) {

        return userMapper.isExistId(id);
    }

    // 회원 탈퇴
    public boolean deleteUser(User currentUser, String inputPassword) throws Exception {
        //입력한 비밀번호 확인
        String encryptPassword = SecurityUtil.encryptPassword(inputPassword);
        String getPassword = userMapper.getPassword(currentUser.getId());

        int deleteResult = 0;
        if(!encryptPassword.equals(getPassword)) {  // 틀린 비밀번호 입력
            throw new WrongPasswordException("잘못된 비밀번호 입니다.");
        }

        deleteResult = userMapper.deleteUser(currentUser.getId());
        if(deleteResult != 1) {
            throw new DeleteErrorException("Delete하는 도중에 에러가 발생하였습니다.");
        }

        return true;
    }

    // 회원 정보 수정
    public int updateUserInfo(String id, UserInfoUpdateDto currentUser){

        UserInfoUpdateDto updateUser = UserInfoUpdateDto.builder()
                .id(id)
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
    public boolean updatePassword(String id, UserPasswordUpdateDto userPasswordUpdateDto) throws Exception {

        String currentPassword = userMapper.getPassword(id);

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