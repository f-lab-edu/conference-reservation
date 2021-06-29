package com.reservation.conference.mapper;

import com.reservation.conference.dto.User;

import java.util.List;
import java.util.Optional;

public interface UserMapper {
    User save(User user);
    Optional<User> findById(Long userId);
    Optional<User> findByName(String userName);
    List<User> findAll();
}
