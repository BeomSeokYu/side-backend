package com.blws.side.common.notification.entity;

import com.blws.side.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "notifications")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer notificationId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private Integer referenceId;

    @Column(nullable = false)
    private String referenceType;

    @Column(nullable = false, columnDefinition = "text")
    private String message;

    @Column(nullable = false)
    private String type; // EMAIL, PUSH, IN-APP 등

    @Column(nullable = false)
    private Boolean isRead = false;

    private LocalDateTime createdAt;
    private Integer createdBy;

    private LocalDateTime updatedAt;
    private Integer updatedBy;
}
