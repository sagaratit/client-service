package com.sg.ocbc.dto;

import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TopUpDto {
    private String userName;
    private double amount;
}
