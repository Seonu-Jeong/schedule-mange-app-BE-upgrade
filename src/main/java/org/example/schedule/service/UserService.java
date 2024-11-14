package org.example.schedule.service;

import org.example.schedule.dto.UserRequestDto;
import org.example.schedule.dto.UserResponseDto;

public interface UserService {
    Long saveSchedule(UserRequestDto userRequestDto);

    UserResponseDto findUserById(Long id);
}
