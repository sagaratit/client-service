package com.sg.ocbc;

import lombok.Getter;
import lombok.Setter;


@Getter
public enum CLIENT_TYPE {
    CORPORATE("CORPORATE","CORPORATE CUSTOMER"),
    RETAIL("RETAIL","RETAIL CUSTOMER");
    @Setter
    String code;
    @Setter
    String desc;
    CLIENT_TYPE(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
