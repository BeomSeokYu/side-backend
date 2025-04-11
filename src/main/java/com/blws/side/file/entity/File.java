package com.blws.side.file.entity;

import com.blws.side.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "file")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class File extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer fileId;

    private String fileName;
    private String filePath;
    private String fileExtension;
    private String fileType;
    private String mimeType;

    private Long fileSize;
}
