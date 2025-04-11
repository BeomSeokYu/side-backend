package com.blws.side.task.entity;

import com.blws.side.common.code.entity.Code;
import com.blws.side.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "task_history")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskHistory extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer historyId;

    @ManyToOne
    @JoinColumn(name = "task_id", nullable = false)
    private Task task;

    @Column(nullable = false)
    private String actionType;

    private String title;
    private String description;

    private LocalDateTime startDate;
    private LocalDateTime endDate;

    private String priority;
    private String status;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Code category;

    private LocalDateTime changedAt;
    private Integer changedBy;
}
