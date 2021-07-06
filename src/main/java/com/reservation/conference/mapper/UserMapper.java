package com.reservation.conference.mapper;

import com.reservation.conference.dto.UserLoginDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    UserLoginDto findUserByIdAndPassword(String id, String password);
}
