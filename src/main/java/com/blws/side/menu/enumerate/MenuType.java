package com.blws.side.menu.enumerate;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MenuType {
    CATEGORY("category", "01"),
    PAGE("page", "02");

    private final String name;
    private final String code;
}
