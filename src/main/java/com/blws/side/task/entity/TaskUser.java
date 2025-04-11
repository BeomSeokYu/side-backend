package com.blws.side.task.entity;

import com.blws.side.common.entity.BaseEntity;
import com.blws.side.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "task_users")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@IdClass(TaskUserId.class)
public class TaskUser extends BaseEntity {

    @Id
    @ManyToOne
    @JoinColumn(name = "task_id", nullable = false)
    private Task task;

    @Id
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
