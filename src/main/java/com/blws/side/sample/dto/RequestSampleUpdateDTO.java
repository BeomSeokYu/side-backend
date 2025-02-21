package com.blws.side.sample.dto;

import com.blws.side.sample.entity.Sample;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RequestSampleUpdateDTO {
    private String title;
    private String content;

    public Sample toEntity() {
        return Sample.builder()
                .title(this.title)
                .content(this.content)
                .build();
    }
}
