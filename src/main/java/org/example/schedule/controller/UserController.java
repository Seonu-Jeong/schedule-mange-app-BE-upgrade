package org.example.schedule.controller;

import com.google.gson.JsonObject;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.example.schedule.constant.Const;
import org.example.schedule.dto.UserRequestDto;
import org.example.schedule.dto.UserResponseDto;
import org.example.schedule.exception.NoAuthoriyException;
import org.example.schedule.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/users")
@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public String createUser(@RequestBody UserRequestDto userRequestDto) {

        JsonObject obj = new JsonObject();

        obj.addProperty("id", userService.saveSchedule(userRequestDto));

        return obj.toString();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> findUserById(
            @PathVariable Long id,
            HttpServletRequest request
    ) {

        if(hasAuthority(id, request)){
            throw new NoAuthoriyException();
        }

        return new ResponseEntity<>(userService.findUserById(id), HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/{id}")
    public void updateUser(
            @PathVariable Long id, @RequestBody UserRequestDto requestDto,
            HttpServletRequest request
    ) {

        if(hasAuthority(id, request)){
            throw new NoAuthoriyException();
        }

        userService.updateUser(id, requestDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id,
                           HttpServletRequest request
    ){

        if(hasAuthority(id, request)){
            throw new NoAuthoriyException();
        }

        userService.deleteUser(id);

    }

    boolean hasAuthority(Long id, HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        UserResponseDto userResponseDto = (UserResponseDto)session.getAttribute(Const.LOGIN_USER);

        if(!id.equals(userResponseDto.getId())) {
            return true;
        }

        return false;
    }
}
