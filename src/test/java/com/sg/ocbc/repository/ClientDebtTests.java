package com.sg.ocbc.repository;

import com.sg.ocbc.domain.ClientDebt;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ClientDebtTests {

    @Mock
    private ClientDebtRepository repo;

    @Test
    public void testCreateUser() {
        ClientDebt clientDebt = ClientDebt.builder()
                .debt(100)
                .payeeClientId("sagar")
                .payerClientId("ashu")
                .build();
        Mockito.when(repo.save(clientDebt)).thenReturn(clientDebt);
        ClientDebt savedUser = repo.save(clientDebt);
        assertThat(savedUser.getPayeeClientId()).isEqualTo(clientDebt.getPayeeClientId());
    }

    @Test
    public void testCreateUserErrorNoId() {
        ClientDebt clientDebt = ClientDebt.builder()
                .debt(100).payeeClientId("sagar")
                .payerClientId("ashu")
                .build();
        Mockito.when(repo.save(clientDebt))
                .thenThrow(new NullPointerException("Error occurred"));
    }

    @Test
    public void testFindPayerClient() {
        ClientDebt clientDebt = ClientDebt.builder()
                .debt(100).payeeClientId("sagar")
                .payerClientId("ashu")
                .build();
        List<ClientDebt> debtList = List.of(clientDebt);
        Mockito.when(repo.findAllByPayerClientId("ashu"))
                .thenReturn(debtList);
        List<ClientDebt> debts = repo.findAllByPayerClientId("ashu");
        assertThat("ashu").isEqualTo(debts.get(0).getPayerClientId());
    }
}