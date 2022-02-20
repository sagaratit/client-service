package com.sg.ocbc.dto;

import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Login {
    private String userName;
    private String password;
}
