package org.example.schedule.entity;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "schedule_tbl")
@NoArgsConstructor
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
