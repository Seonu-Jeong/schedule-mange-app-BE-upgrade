package org.example.schedule.service;

import lombok.RequiredArgsConstructor;
import org.example.schedule.dto.ScheduleRequestDto;
import org.example.schedule.dto.ScheduleResponseDto;
import org.example.schedule.entity.Schedule;
import org.example.schedule.repository.ScheduleRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<ScheduleResponseDto> findAll(Long id) {
        Schedule[] schedules = scheduleRepository.findAllByUserId(id);

        if(schedules==null || schedules.length == 0)
            return null;

        return Arrays.stream(schedules).map( schedule ->
            new ScheduleResponseDto(
                    schedule.getId(),
                    schedule.getTitle(),
                    schedule.getContent(),
                    schedule.getCreatedAt(),
                    schedule.getModifiedAt()
                    )
        ).collect(Collectors.toList());
    }
}
