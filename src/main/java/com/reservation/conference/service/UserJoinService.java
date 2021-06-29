package com.reservation.conference.service;

import com.reservation.conference.dto.User;
import com.reservation.conference.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class UserJoinService {


    @Autowired
    private UserMapper userMapper;


    // 회원가입
   public Long join(User user){
        validateDuplicateCompany(user); // 검증
        userMapper.save(user);
        return user.getId();
    }

    // 중복 회원 체크
    private void validateDuplicateCompany(User user){
        userMapper.findByName(user.getUserName())
                .ifPresent(m->{
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }
}
