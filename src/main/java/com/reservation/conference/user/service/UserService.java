package com.reservation.conference.user.service;

<<<<<<< HEAD:src/main/java/com/reservation/conference/service/UserService.java

import com.reservation.conference.dto.UserLoginDto;
import com.reservation.conference.mapper.UserMapper;
import com.reservation.conference.utils.SecurityUtil;
=======
import com.reservation.conference.user.dto.*;
import com.reservation.conference.exception.*;
import com.reservation.conference.user.mapper.UserMapper;
import com.reservation.conference.utils.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
>>>>>>> feature/9:src/main/java/com/reservation/conference/user/service/UserService.java
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserMapper userMapper;

<<<<<<< HEAD:src/main/java/com/reservation/conference/service/UserService.java
    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
=======

    /***
     * 유저 로그인
     */
    public UserLoginResponseDto login(String id, String password) throws Exception {

        String encryptPassword = SecurityUtil.encryptPassword(password);
        UserLoginResponseDto userLoginInfo = userMapper.findUserByIdAndPassword(id, encryptPassword);

        if(userLoginInfo == null) {
            throw new UserNotFoundException("해당 유저의 로그인 정보가 존재하지 않습니다.");
        }

        return userLoginInfo;
>>>>>>> feature/9:src/main/java/com/reservation/conference/user/service/UserService.java
    }

    /***
     * 유저 로그인 메서드
     */
<<<<<<< HEAD:src/main/java/com/reservation/conference/service/UserService.java
    public UserLoginDto login(String id, String password) throws Exception {

        String encryptPassword = SecurityUtil.encryptPassword(password);
        UserLoginDto userLoginDto = userMapper.findUserByIdAndPassword(id, encryptPassword);
=======
    public void join(User user) throws Exception {

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

        try {
            userMapper.insertUser(newUser);
        }catch(DataAccessException e) {
            throw new FailToInsertException("insert하는 도중에 에러가 발생하였습니다.", e);
        }

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
>>>>>>> feature/9:src/main/java/com/reservation/conference/user/service/UserService.java

        return userLoginDto;
    }

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
