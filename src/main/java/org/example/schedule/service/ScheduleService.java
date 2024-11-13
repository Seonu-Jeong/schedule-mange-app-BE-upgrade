package org.example.schedule.service;

import lombok.RequiredArgsConstructor;
import org.example.schedule.dto.ScheduleRequestDto;
import org.example.schedule.entity.Schedule;
import org.example.schedule.repository.ScheduleRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    final private ScheduleRepository scheduleRepository;

    public Long saveSchedule(ScheduleRequestDto scheduleRequestDto) {
        return scheduleRepository.save(
                Schedule.builder()
                        .title(scheduleRequestDto.getTitle())
                        .content(scheduleRequestDto.getContent())
                        .build()
        ).getId();
    }
}
