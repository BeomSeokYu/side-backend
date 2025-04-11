package com.blws.side.task.entity;

import com.blws.side.common.entity.BaseEntity;
import com.blws.side.project.entity.Project;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "recurring_tasks")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecurringTask extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer recurringTaskId;

    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    @Column(nullable = false)
    private String title;

    private String description;

    @Column(nullable = false)
    private String intervalUnit; // DAILY, WEEKLY, etc.

    private Integer intervalValue;
    private Integer dayOfWeek;
    private Integer dayOfMonth;
    private Integer monthOfYear;
    private Integer weekOfMonth;

    @Column(nullable = false)
    private LocalDateTime nextOccurrence;

    private Integer repeatCount;
    private LocalDateTime endDate;
}
