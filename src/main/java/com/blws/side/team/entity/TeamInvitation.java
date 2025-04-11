package com.blws.side.team.entity;

import com.blws.side.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "team_invitations")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TeamInvitation extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer invitationId;

    @ManyToOne
    @JoinColumn(name = "team_id", nullable = false)
    private Team team;

    @Column(nullable = false)
    private String invitedEmail;

    @Column(nullable = false, unique = true)
    private String invitationCode;

    @Column(nullable = false)
    private String status = "PENDING";
}
