package com.blws.side.task.entity;

import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskTagId implements Serializable {
    private Integer task;
    private String tagName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TaskTagId)) return false;
        TaskTagId that = (TaskTagId) o;
        return Objects.equals(task, that.task) &&
                Objects.equals(tagName, that.tagName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(task, tagName);
    }
}
