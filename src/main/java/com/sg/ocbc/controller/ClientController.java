
package com.sg.ocbc.controller;

import com.sg.ocbc.constants.Message;
import com.sg.ocbc.dto.Login;
import com.sg.ocbc.dto.Logout;
import com.sg.ocbc.dto.RegisterClient;
import com.sg.ocbc.service.ClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Slf4j
public class ClientController {

    @Autowired
    private ClientService clientService;

    /*
     * login and check the balance
     * @param Login
     * @return String
     * */
    @PostMapping("/login")
    public String login(@RequestBody Login login) {
        String message = Message.INVALID_USER_PASSWORD;
        try {
            if (login != null) {
                if (!login.getUserName().isEmpty() || !login.getPassword().isEmpty()) {
                    message = clientService.valid(login);
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            return message = Message.ERROR_INPUT_MESSAGE;
        }

        return message;
    }

    /*
     * logout to stop transaction
     * @param Logout
     * @return String
     * */
    @PostMapping("/logout")
    public String logout(@RequestBody Logout logout) {
        String message = Message.INVALID_USER_PASSWORD;
        try {
            if (logout != null) {
                if (!logout.getUserName().isEmpty()) {
                    message = clientService.logut(logout);
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            return  Message.ERROR_INPUT_MESSAGE;
        }

        return message;
    }


    /*
     * Register for new client
     * @param RegisterClient
     * @return String
     * */
    @PostMapping("/registerClient")
    public String registerClient(@RequestBody RegisterClient client) {
        String message = Message.ALL_FIELDS_MANDATORY;
        if (client == null || client.getClientName().isEmpty() || client.getEmail().isEmpty() || client.getPassword().isEmpty()) {
            return message;
        }
        try {
            message = clientService.save(client);
        } catch (Exception e) {
            log.error(e.getMessage());
            return Message.ERROR_INPUT_MESSAGE;
        }

        return message;
    }
}
