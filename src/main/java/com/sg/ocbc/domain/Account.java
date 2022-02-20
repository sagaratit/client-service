package com.sg.ocbc.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ACCOUNT")
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Account implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private int accountId;
    @Column(name = "account_type")
    private String accountType;
    @Column(name = "balance")
    private double balance;
    @Column(name = "debt")
    private double debt;
    @Column(name = "client_id")
    private String clientId;
}
