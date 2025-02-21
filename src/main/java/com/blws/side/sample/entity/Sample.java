package com.blws.side.sample.entity;

import com.blws.side.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Table(name = "TB_SAMPLE")
@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)          // JPA는 프레임워크가 엔티티 클래스를 리플렉션을 통해 인스턴스화할 수 있도록 기본 생성자를 필요로 함
@EntityListeners(value = {AuditingEntityListener.class})    // created by, created at, last updated by, last updated at 자동 입력 처리
public class Sample extends BaseEntity {                    // BaseEntity -> 생성, 수정 관련 공통 사항

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content")
    private String content;

    public Sample update(Sample sample) {
        this.title = sample.getTitle();
        this.content = sample.getContent();
        return this;
    }

}
