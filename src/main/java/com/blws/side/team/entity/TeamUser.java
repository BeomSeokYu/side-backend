package com.blws.side.team.entity;

import com.blws.side.common.entity.BaseEntity;
import com.blws.side.user.entity.User;
import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "team_users")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@IdClass(TeamUserId.class)
public class TeamUser extends BaseEntity {

    @Id
    @ManyToOne
    @JoinColumn(name = "team_id", nullable = false)
    private Team team;

    @Id
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private String role;
}
