package com.blws.side.project.entity;

import lombok.*;
import java.io.Serializable;
import java.util.Objects;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectTeamId implements Serializable {
    private Integer project;
    private Integer team;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProjectTeamId)) return false;
        ProjectTeamId that = (ProjectTeamId) o;
        return Objects.equals(project, that.project) &&
                Objects.equals(team, that.team);
    }

    @Override
    public int hashCode() {
        return Objects.hash(project, team);
    }
}
