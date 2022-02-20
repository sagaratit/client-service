package com.sg.ocbc.repository;

import com.sg.ocbc.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    Account findAllByClientId(String name);
}