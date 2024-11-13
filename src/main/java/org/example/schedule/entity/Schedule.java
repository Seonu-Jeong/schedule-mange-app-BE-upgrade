package org.example.schedule.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "schedule_tbl")
public class Schedule extends TimeBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 30)
    private String title;

    @Column(columnDefinition = "tinytext")
    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
