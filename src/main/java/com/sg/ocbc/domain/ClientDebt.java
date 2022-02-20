package com.sg.ocbc.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "CLIENT_DEBT")
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClientDebt implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "client_debt_id")
    private int clientDebtId;
    @Column(name = "payer_client_id")
    private String payerClientId;
    @Column(name = "payee_client_id")
    private String payeeClientId;
    @Column(name = "debt")
    private double debt;
}
