package com.sg.ocbc.dto;

import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterClient {
    private String clientName;
    private String email;
    private String password;
}
