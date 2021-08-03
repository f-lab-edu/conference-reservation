package com.reservation.conference.service;

import com.reservation.conference.dto.*;
import com.reservation.conference.exception.*;
import com.reservation.conference.mapper.UserMapper;
import com.reservation.conference.utils.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;


    /***
     * 유저 로그인
     */
    public User login(String id, String password) throws Exception {

        String encryptPassword = SecurityUtil.encryptPassword(password);
        User userLoginInfo = userMapper.findUserByIdAndPassword(id, encryptPassword);

        if(userLoginInfo == null) {
            throw new UserNotFoundException("해당 유저의 로그인 정보가 존재하지 않습니다.");
        }

        return userLoginInfo;
    }

    /***
     * 유저 회원가입
     */
    public boolean join(User user) throws Exception {

        if(checkUserIdExist(user.getId())) {
            throw new DuplicationIdException("회원가입이 불가합니다. " + user.getId() + " 는 중복된 아이디 입니다.");
        }

        String encryptedPassword = SecurityUtil.encryptPassword(user.getPassword());

        User newUser = user.builder()
                .id(user.getId())
                .password(encryptedPassword)
                .userName(user.getUserName())
                .userEmail(user.getUserEmail())
                .userPhoneNumber(user.getUserPhoneNumber())
                .userOrganization(user.getUserOrganization())
                .userGender(user.getUserGender())
                .userDateBirth(user.getUserDateBirth())
                .build();

        int insertResult = userMapper.insertUser(newUser);
        if(insertResult != 1) {
            throw new FailToInsertException("insert하는 도중에 에러가 발생하였습니다.");
        }

        return true;
    }

    /***
     * 중복 유저 검증
     */
    public boolean checkUserIdExist(String id) {

        return userMapper.isExistId(id);
    }

    /***
     * 유저 탈퇴
     */
    public boolean deleteUser(User currentUser, String inputPassword) throws Exception {

        String encryptPassword = SecurityUtil.encryptPassword(inputPassword);
        String getPassword = userMapper.getPassword(currentUser.getId());

        if(!encryptPassword.equals(getPassword)) {
            throw new WrongPasswordException("잘못된 비밀번호 입니다.");
        }

        int deleteResult = userMapper.deleteUser(currentUser.getId());
        if(deleteResult != 1) {
            throw new FailToDeleteException("Delete하는 도중에 에러가 발생하였습니다.");
        }

        return true;
    }

    /***
     * 유저 정보 수정
     */
    public int updateUserInfo(String id, User currentUser){

        User updateUser = User.builder()
                .id(id)
                .userName(currentUser.getUserName())
                .userEmail(currentUser.getUserEmail())
                .userPhoneNumber(currentUser.getUserPhoneNumber())
                .userOrganization(currentUser.getUserOrganization())
                .userGender(currentUser.getUserGender())
                .userDateBirth(currentUser.getUserDateBirth())
                .build();

        int updateResult = userMapper.updateUserInfo(updateUser);
        if(updateResult != 1) {
            throw new FailToUpdateException("정보 수정 중 에러가 발생하였습니다.");
        }

        return updateResult;
    }

    /***
     * 유저 비밀번호 수정
     */
    public boolean updatePassword(String id, UserPasswordUpdateDto userPasswordUpdateDto) throws Exception {

        String currentPassword = userMapper.getPassword(id);

        // 현재 유저의 패스워드 == 정보 수정을 위해 입력 받은 패스워드
        if(!currentPassword.equals(userPasswordUpdateDto.getCurrentPassword())){
            throw new WrongPasswordException("잘못된 비밀번호를 입력하셨습니다.");
        }
        // newPassword 암호화
        String encryptedNewPassword = SecurityUtil.encryptPassword(userPasswordUpdateDto.getNewPassword());

        UserPasswordUpdateDto updatePasswordUser = UserPasswordUpdateDto.builder()
                .newPassword(encryptedNewPassword)
                .build();

        if(userMapper.updatePassword(updatePasswordUser) != 1) {
            throw new FailToUpdateException("비밀번호 업데이트 중 오류가 발생하였습니다.");
        }

        return true;
    }

}
