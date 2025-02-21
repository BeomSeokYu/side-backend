package com.blws.side.menu.dto;

import com.blws.side.menu.entity.Menu;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RequestMenuUpdateDTO {
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

    public Menu toEntity() {
        return Menu.builder()
                .id(this.id)
                .memuName(this.memuName)
                .menuCode(this.menuCode)
                .parentId(this.parentId)
                .menuType(this.menuType)
                .url(this.url)
                .sortOrder(this.order)
                .iconClass(this.iconClass)
                .isUsed(this.isUsed)
                .layoutType(this.layoutType)
                .build();
    }
}
