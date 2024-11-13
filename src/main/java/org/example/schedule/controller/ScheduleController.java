package org.example.schedule.controller;

import com.google.gson.JsonObject;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.example.schedule.constant.Const;
import org.example.schedule.dto.ScheduleRequestDto;
import org.example.schedule.dto.ScheduleResponseDto;
import org.example.schedule.dto.UserResponseDto;
import org.example.schedule.service.ScheduleService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/schedules")
@Controller
@RequiredArgsConstructor
public class ScheduleController {

    final private ScheduleService scheduleService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public String createSchedule(@RequestBody ScheduleRequestDto scheduleRequestDto) {

        JsonObject obj = new JsonObject();

        obj.addProperty("id", scheduleService.saveSchedule(scheduleRequestDto));

        return obj.toString();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<ScheduleResponseDto> findScheduleList(HttpServletRequest request) {

        HttpSession session = request.getSession(false);

        UserResponseDto userResponseDto = (UserResponseDto)session.getAttribute(Const.LOGIN_USER);

        return scheduleService.findAll(userResponseDto.getId());
    }
}
