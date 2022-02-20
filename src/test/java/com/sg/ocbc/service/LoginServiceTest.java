package com.sg.ocbc.service;

import com.sg.ocbc.dto.Login;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class LoginServiceTest {
    @Mock
    ClientService clientService;

    @Test
    public void validLoginTest() {
        Login login = Login.builder().userName("sagar").password("pass").build();
        Mockito.when(clientService.valid(login)).thenReturn("logged in");
        String message = clientService.valid(login);
        Assertions.assertEquals("logged in", message);
    }

    @Test
    public void invalidLoginTest() {
        Login login = Login.builder().userName("sagar").password("pass1").build();
        Mockito.when(clientService.valid(login)).thenReturn("incorrect username or password");
        String message = clientService.valid(login);
        Assertions.assertEquals("incorrect username or password", message);
    }

}
