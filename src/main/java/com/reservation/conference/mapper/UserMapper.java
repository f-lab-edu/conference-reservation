package com.reservation.conference.mapper;


import com.reservation.conference.dto.UserJoinDto;
import com.reservation.conference.dto.UserLoginDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {

    UserLoginDto findUserByIdAndPassword(String id, String password);

    void insertUser(UserJoinDto userJoinDto);
}
