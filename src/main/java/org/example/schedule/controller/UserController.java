package org.example.schedule.controller;

import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import org.example.schedule.dto.UserRequestDto;
import org.example.schedule.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

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
}
