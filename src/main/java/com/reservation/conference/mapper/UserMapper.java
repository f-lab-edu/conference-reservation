package com.reservation.conference.mapper;


import com.reservation.conference.dto.UserJoinDto;
import com.reservation.conference.dto.UserLoginDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {

    void insertUser(UserJoinDto userJoinDto);

    UserLoginDto findUserByIdAndPassword(@Param("id") String id, @Param("password") String password);

}
