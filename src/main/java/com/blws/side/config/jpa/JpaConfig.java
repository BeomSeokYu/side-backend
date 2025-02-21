package com.blws.side.config.jpa;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing
public class JpaConfig {

    // @CreatedBy와 @ModifiedBy를 사용하기 위해 AuditorAware를 스프링 빈으로 등록
    public AuditorAware<String> auditorAware() {
        return new AuditorAwareImpl();
    }

}
