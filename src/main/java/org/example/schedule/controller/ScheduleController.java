package org.example.schedule.controller;

import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import org.example.schedule.dto.ScheduleRequestDto;
import org.example.schedule.service.ScheduleService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

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
}
