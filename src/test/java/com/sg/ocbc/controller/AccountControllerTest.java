package com.sg.ocbc.controller;

import com.sg.ocbc.constants.Message;
import com.sg.ocbc.dto.PayDto;
import com.sg.ocbc.dto.TopUpDto;
import com.sg.ocbc.service.AccountService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AccountControllerTest {
    @Mock
    private AccountController accountController;

    @Mock
    private AccountService accountService;

    @Test
    public void testTopUp() {
        TopUpDto topUpDto = TopUpDto.builder().userName("sagar").amount(10).build();
        Mockito.when(accountController.topup(topUpDto)).thenReturn("your balance is 30");
        String message = accountController.topup(topUpDto);
        Assertions.assertEquals("your balance is 30", message);
    }

    @Test
    public void testPay() {
        PayDto payDto = PayDto.builder().userName("sagar").amount(10).build();
        Mockito.when(accountController.pay(payDto)).thenReturn("Transferred");
        String message = accountController.pay(payDto);
        Assertions.assertEquals("Transferred", message);
    }

    @Test
    public void testPayError() {
        PayDto payDto = PayDto.builder().userName(null).amount(10).build();
        Mockito.when(accountController.pay(payDto)).thenReturn(Message.ERROR_INPUT_MESSAGE);
        String message = accountController.pay(payDto);
        Assertions.assertEquals("Ops..error occurred check inputs or try one more time ", message);
    }
}
