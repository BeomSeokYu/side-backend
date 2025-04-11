package com.blws.side.task.entity;

import com.blws.side.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "task_tags")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@IdClass(TaskTagId.class)
public class TaskTag extends BaseEntity {

    @Id
    @ManyToOne
    @JoinColumn(name = "task_id", nullable = false)
    private Task task;

    @Id
    private String tagName;
}
