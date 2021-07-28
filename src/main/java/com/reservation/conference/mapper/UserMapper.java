package com.reservation.conference.mapper;


import com.reservation.conference.dto.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {

    int insertUser(User user);

    UserLoginDto findUserByIdAndPassword(@Param("id") String id, @Param("password") String password);

    boolean isExistId(String id);

    int deleteUser(String id);

    int updatePassword(UserPasswordUpdateDto userPasswordUpdateDto);

    int updateUserInfo(UserInfoUpdateDto userInfoUpdateDto);

    String getPassword(String id);

}