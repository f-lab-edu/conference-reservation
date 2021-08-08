package com.reservation.conference.user.mapper;


import com.reservation.conference.user.dto.User;
import com.reservation.conference.user.dto.UserLoginResponseDto;
import com.reservation.conference.user.dto.UserPasswordUpdateDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {

    int insertUser(User user);

    UserLoginResponseDto findUserByIdAndPassword(@Param("id") String id, @Param("password") String password);

    boolean isExistId(String id);

    int deleteUser(String id);

    int updatePassword(UserPasswordUpdateDto userPasswordUpdateDto);

    int updateUserInfo(User userUpdateInfo);

    String getPassword(String id);

}