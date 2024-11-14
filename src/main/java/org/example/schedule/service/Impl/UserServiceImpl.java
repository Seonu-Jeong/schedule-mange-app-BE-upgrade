package org.example.schedule.service.Impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.schedule.dto.UserRequestDto;
import org.example.schedule.dto.UserResponseDto;
import org.example.schedule.entity.User;
import org.example.schedule.exception.UserExistException;
import org.example.schedule.repository.UserRepository;
import org.example.schedule.service.UserService;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;


    @Override
    public Long saveSchedule(UserRequestDto userRequestDto) throws UserExistException {

        userRepository.findByEmail(userRequestDto.getEmail())
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

    @Override
    public UserResponseDto findUserById(Long id) throws NoSuchElementException {

        User user = userRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("User not found")
        );

        return new UserResponseDto(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getCreatedAt(),
                user.getModifiedAt()
        );
    }

    @Override
    @Transactional
    public void updateUser(Long id, UserRequestDto requestDto) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("User not found")
        );

        user.setName(requestDto.getName());
        user.setEmail(requestDto.getEmail());
        user.setPassword(requestDto.getPassword());
    }
}
