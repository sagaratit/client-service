package com.sg.ocbc.service;

import com.sg.ocbc.constants.Message;
import com.sg.ocbc.domain.Client;
import com.sg.ocbc.dto.TopUpDto;
import com.sg.ocbc.repository.ClientRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AccountServiceTest {

    @Mock
    private AccountService accountService;

    @InjectMocks
    private ClientRepository clientRepository;

    /*@Test
    public void topUpInValidUser() {
        TopUpDto topUpDto = TopUpDto.builder()
                .userName("sagar")
                .amount(10).build();

        Client client = Mockito.mock(Client.class);
        Mockito.when(clientRepository.findAllByName(topUpDto.getUserName().trim())).thenReturn(client);
        Mockito.when(accountService.topUp(topUpDto)).thenReturn(Message.ERROR_INPUT_MESSAGE);
        String message = accountService.topUp(topUpDto);
        Assertions.assertEquals("Ops..error occurred check inputs or try one more time ", message);
    }

    @Test
    public void topUpUser() {
        TopUpDto topUpDto = TopUpDto.builder()
                .userName("sagar")
                .amount(10).build();

        Mockito.when(accountService.topUp(topUpDto)).thenReturn(Message.YOUR_BALANCE_IS);
        String message = accountService.topUp(topUpDto);
        Assertions.assertEquals("Your balance is 30", message);
    }*/
}
