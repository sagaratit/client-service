package com.sg.ocbc.domain;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "CLIENT")
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Client implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "id",nullable = false, length = 50)
    private String id;

    @Column(name = "email", nullable = false, unique = true, length = 50)
    private String email;

    @Column(name = "password", nullable = false, length = 64)
    private String password;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "loggedIn")
    private boolean loggedIn;

    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name="account_id")
    private List<Account> accounts;
}