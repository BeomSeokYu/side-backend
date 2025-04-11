package com.blws.side.common.code.entity;

import com.blws.side.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "codes")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Code extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codeId;

    @Column(nullable = false)
    private String codeGroup;

    @Column(nullable = false)
    private String code;

    @Column(nullable = false)
    private String codeName;

    private Integer parentCodeId;
    private Integer sortOrder;

    private String description;
}
