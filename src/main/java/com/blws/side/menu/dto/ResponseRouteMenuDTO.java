package com.blws.side.menu.dto;

import com.blws.side.menu.entity.Menu;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(access = AccessLevel.PRIVATE)
public class ResponseRouteMenuDTO {
    private String path;
    private String layoutType;

    public static ResponseRouteMenuDTO of(Menu menu) {
        return builder()
                .path(menu.getUrl())
                .layoutType(menu.getLayoutType())
                .build();
    }

}
