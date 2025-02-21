package com.blws.side.sample.dto;

import com.blws.side.sample.entity.Sample;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(access = AccessLevel.PRIVATE)
public class ResponseSampleDTO {
    private Long id;
    private String title;
    private String content;

    public static ResponseSampleDTO of(Sample sample) {
        return builder()
                .id(sample.getId())
                .title(sample.getTitle())
                .content(sample.getContent())
                .build();
    }
}
