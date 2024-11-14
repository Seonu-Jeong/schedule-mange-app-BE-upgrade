package org.example.schedule.service.Impl;

import lombok.RequiredArgsConstructor;
import org.example.schedule.dto.UserRequestDto;
import org.example.schedule.entity.User;
import org.example.schedule.exception.UserExistException;
import org.example.schedule.repository.UserRepository;
import org.example.schedule.service.UserService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;


    @Override
    public Long saveSchedule(UserRequestDto userRequestDto) throws UserExistException {

        userRepository.findByUsername(userRequestDto.getName())
                .ifPresent((user) -> {
                    throw new UserExistException();
                });

        return userRepository.save(
                User.builder()
                        .name(userRequestDto.getName())
                        .email(userRequestDto.getEmail())
                        .password(userRequestDto.getPassword())
                        .build()
        ).getId();

    }
}
