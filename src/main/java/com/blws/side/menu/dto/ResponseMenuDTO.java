package com.blws.side.menu.dto;

import com.blws.side.menu.entity.Menu;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder(access = AccessLevel.PRIVATE)
public class ResponseMenuDTO {
    private Long id;
    private String memuName;
    private String menuCode;
    private Long parentId;
    private String menuType;
    private String url;
    private Long order;
    private String iconClass;
    private String isUsed;
    private String layoutType;
    private String createdBy;
    private String updatedBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<ResponseMenuDTO> children;

    public static ResponseMenuDTO of(Menu menu) {
        return builder()
                .id(menu.getId())
                .memuName(menu.getMemuName())
                .menuCode(menu.getMenuCode())
                .parentId(menu.getParentId())
                .menuType(menu.getMenuType())
                .url(menu.getUrl())
                .order(menu.getSortOrder())
                .iconClass(menu.getIconClass())
                .isUsed(menu.getIsUsed())
                .layoutType(menu.getLayoutType())
                .createdBy(menu.getCreatedBy())
                .updatedBy(menu.getUpdatedBy())
                .createdAt(menu.getCreatedAt())
                .updatedAt(menu.getUpdatedAt())
                .children(menu.getChildren() != null
                        ? menu.getChildren().stream().map(ResponseMenuDTO::of).collect(Collectors.toList())
                        : null)
                .build();
    }

}
