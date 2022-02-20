package com.sg.ocbc.dto;

import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PayDto {
    private String userName;
    private String pay;
    private double amount;
}
