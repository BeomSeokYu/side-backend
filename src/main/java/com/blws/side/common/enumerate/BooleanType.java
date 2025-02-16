package com.blws.side.common.enumerate;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BooleanType {
    TURE("true", "TRUE", "T", "T", true),
    FALSE("false", "FALSE", "F", "F", false),
    YES("yes", "YES", "Y", "O", true),
    NO("no", "NO", "N", "X", false);

    private String name;
    private String subName;
    private String alias;
    private String subAlias;
    private Boolean boolValue;
}
