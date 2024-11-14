package org.example.schedule.service.Impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.schedule.dto.LoginRequestDto;
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
    public Long saveUser(UserRequestDto userRequestDto) throws UserExistException {

        userRepository.findByEmailAndPassword(userRequestDto.getEmail(), userRequestDto.getPassword())
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

    @Override
    public void deleteUser(Long id) {
        userRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("No exist user")
        );

        userRepository.deleteById(id);
    }

    @Override
    public UserResponseDto login(LoginRequestDto loginRequestDto) throws NoSuchElementException{

        User user = userRepository.findByEmailAndPassword(
                loginRequestDto.getEmail(), loginRequestDto.getPassword()
        ).orElseThrow(NoSuchElementException::new);


        return new UserResponseDto(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getCreatedAt(),
                user.getModifiedAt()
        );
    }
}
