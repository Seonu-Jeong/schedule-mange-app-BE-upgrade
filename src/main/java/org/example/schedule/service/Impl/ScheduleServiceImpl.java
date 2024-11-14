package org.example.schedule.service.Impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.schedule.dto.ScheduleRequestDto;
import org.example.schedule.dto.ScheduleResponseDto;
import org.example.schedule.entity.Schedule;
import org.example.schedule.repository.ScheduleRepository;
import org.example.schedule.service.ScheduleService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

    final private ScheduleRepository scheduleRepository;

    @Override
    public Long saveSchedule(ScheduleRequestDto scheduleRequestDto) {
        return scheduleRepository.save(
                Schedule.builder()
                        .title(scheduleRequestDto.getTitle())
                        .content(scheduleRequestDto.getContent())
                        .build()
        ).getId();
    }

    @Override
    public List<ScheduleResponseDto> findAll(Long id) {
        Schedule[] schedules = scheduleRepository.findAllByUserId(id);

        if(schedules==null || schedules.length == 0)
            return null;

        return Arrays.stream(schedules).map( schedule ->
            new ScheduleResponseDto(
                    schedule.getId(),
                    schedule.getTitle(),
                    schedule.getContent(),
                    schedule.getUser().getName(),
                    schedule.getCreatedAt(),
                    schedule.getModifiedAt()
                    )
        ).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void updateSchedule(Long id, ScheduleRequestDto requestDto) throws NoSuchElementException {

        Schedule schedule = scheduleRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("No exist schedule")
        );

        if( requestDto.getTitle() != null)
            schedule.setTitle(requestDto.getTitle());


        if( requestDto.getContent() != null)
            schedule.setContent(requestDto.getContent());
    }

    @Override
    public void deleteSchedule(Long id) throws NoSuchElementException {

        scheduleRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("No exist schedule")
        );

        scheduleRepository.deleteById(id);
    }

    @Override
    public ScheduleResponseDto findScheduleById(Long id) throws NoSuchElementException {
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("No exist schedule")
        );

        return new ScheduleResponseDto(
                schedule.getId(),
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getUser().getName(),
                schedule.getCreatedAt(),
                schedule.getModifiedAt()
        );
    }
}
