package org.example.schedule.service;

import org.example.schedule.dto.LoginRequestDto;
import org.example.schedule.dto.UserRequestDto;
import org.example.schedule.dto.UserResponseDto;

public interface UserService {
    Long saveUser(UserRequestDto userRequestDto);

    UserResponseDto findUserById(Long id);

    void updateUser(Long id, UserRequestDto requestDto);

    void deleteUser(Long id);

    UserResponseDto login(LoginRequestDto loginRequestDto);
}
