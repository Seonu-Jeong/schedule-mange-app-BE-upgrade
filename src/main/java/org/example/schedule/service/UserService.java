package org.example.schedule.service;

import org.example.schedule.dto.UserRequestDto;

public interface UserService {
    Long saveSchedule(UserRequestDto userRequestDto);
}
