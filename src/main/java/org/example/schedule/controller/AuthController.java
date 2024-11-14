package org.example.schedule.controller;

import com.google.gson.JsonObject;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.example.schedule.constant.Const;
import org.example.schedule.dto.LoginRequestDto;
import org.example.schedule.dto.UserRequestDto;
import org.example.schedule.dto.UserResponseDto;
import org.example.schedule.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor


@RestController
public class AuthController {

    private final UserService userService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "/signup")
    public String signUp(@RequestBody UserRequestDto userRequestDto) {

        JsonObject obj = new JsonObject();

        obj.addProperty("id", userService.saveUser(userRequestDto));

        return obj.toString();
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping(path = "/login")
    public void login(
            @RequestBody LoginRequestDto loginRequestDto,
            HttpServletRequest request
    ) {

        UserResponseDto userResponseDto = userService.login(loginRequestDto);

        HttpSession session = request.getSession();

        // Session에 로그인 회원 정보를 저장한다.
        session.setAttribute(Const.LOGIN_USER, userResponseDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping(path = "/logout")
    public void logout( HttpServletRequest request ) {

        HttpSession session = request.getSession(false);
        // 세션이 존재하면 -> 로그인이 된 경우
        if(session != null) {
            session.invalidate(); // 해당 세션(데이터)을 삭제한다.
        }

    }

}
