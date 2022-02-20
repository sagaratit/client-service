package com.sg.ocbc.repository;

import com.sg.ocbc.domain.Client;
import com.sg.ocbc.domain.ClientDebt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientDebtRepository extends JpaRepository<ClientDebt, Long> {
 ClientDebt findAllByPayerClientIdAndPayeeClientId(String payerClientId,String payeeClientId);
 List<ClientDebt> findAllByPayerClientId(String payerClientId);
 List<ClientDebt> findAllByPayeeClientId(String clientId);
}