package com.sg.ocbc.controller;

import com.sg.ocbc.dto.Login;
import com.sg.ocbc.dto.Logout;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ClientControllerTest {

    @Mock
    ClientController clientController;

    @Test
    public void testLogout() {
        Logout logout = Logout.builder().userName("sagar").build();
        Mockito.when(clientController.logout(logout)).thenReturn("logged out");
        String message = clientController.logout(logout);
        Assertions.assertEquals("logged out", message);
    }

    @Test
    public void testInvalidUserLogout() {
        Logout logout = Logout.builder().userName("sagar").build();
        Mockito.when(clientController.logout(logout)).thenReturn("please register");
        String message = clientController.logout(logout);
        Assertions.assertEquals("please register", message);
    }

    @Test
    public void testValidUserLogin() {
        Login login = Login.builder().userName("sagar").password("pass").build();
        Mockito.when(clientController.login(login)).thenReturn("success");
        String message = clientController.login(login);
        Assertions.assertEquals("success", message);
    }

    @Test
    public void testInvalidUserLogin() {
        Login login = Login.builder().userName("sagar").password("n634m4").build();
        Mockito.when(clientController.login(login)).thenReturn("failed");
        String message = clientController.login(login);
        Assertions.assertEquals("failed", message);
    }
}
