package com.sg.ocbc.dto;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Logout {
    @Setter
    @Getter
    private String userName;
}
