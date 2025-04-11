package com.blws.side.file.entity;

import com.blws.side.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "file_relations")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FileRelation extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer relationId;

    @ManyToOne
    @JoinColumn(name = "file_id", nullable = false)
    private File file;

    @Column(nullable = false)
    private String relationType; // TASK, PROJECT, etc.

    @Column(nullable = false)
    private Integer referenceId;
}
