package com.blws.side.project.entity;

import com.blws.side.common.entity.BaseEntity;
import com.blws.side.team.entity.Team;
import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "project_teams")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@IdClass(ProjectTeamId.class)
public class ProjectTeam extends BaseEntity {

    @Id
    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @Id
    @ManyToOne
    @JoinColumn(name = "team_id", nullable = false)
    private Team team;
}
