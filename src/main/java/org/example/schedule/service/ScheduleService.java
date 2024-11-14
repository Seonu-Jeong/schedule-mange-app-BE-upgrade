package org.example.schedule.service;

import org.example.schedule.dto.ScheduleRequestDto;
import org.example.schedule.dto.ScheduleResponseDto;

import java.util.List;

public interface ScheduleService {

    public Long saveSchedule(ScheduleRequestDto scheduleRequestDto);

    public ScheduleResponseDto findScheduleById(Long id);

    public List<ScheduleResponseDto> findAll(Long id);
}
