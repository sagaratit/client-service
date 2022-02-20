package com.sg.ocbc.repository;

import com.sg.ocbc.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
 Client findAllByName(String name);
}