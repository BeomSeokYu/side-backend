package com.blws.side.task.entity;

import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskUserId implements Serializable {
    private Integer task;
    private Integer user;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TaskUserId)) return false;
        TaskUserId that = (TaskUserId) o;
        return Objects.equals(task, that.task) &&
                Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(task, user);
    }
}
