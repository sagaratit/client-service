package com.sg.ocbc.controller;

import com.sg.ocbc.constants.Message;
import com.sg.ocbc.dto.PayDto;
import com.sg.ocbc.dto.TopUpDto;
import com.sg.ocbc.service.AccountService;
import com.sg.ocbc.service.ClientDebtService;
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
public class AccountController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private ClientDebtService clientDebtService;


    /*
     * topup load money to ac
     * @param TopUpDto
     * @return String
     * */
    @PostMapping("/topup")
    public String topup(@RequestBody TopUpDto topUpDto) {
        String message = Message.INVALID_CLIENT_NAME_AMOUNT;

        try {
            if (topUpDto != null) {
                if (topUpDto.getAmount() < 0) {
                    return message = Message.AMOUNT_POSITIVE_NUMBER;
                }
                if (!topUpDto.getUserName().isEmpty()) {
                    message = accountService.topUp(topUpDto);
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            return message = Message.ERROR_INPUT_MESSAGE;
        }

        return message;
    }

    /*
     * pay amount to other client
     * @param PayDto
     * @return String
     * */
    @PostMapping("/pay")
    public String pay(@RequestBody PayDto payDto) {
        String message = Message.INVALID_CLIENT_NAME_AMOUNT;

        try {
            if (payDto != null) {
                if (payDto.getUserName().isEmpty() || payDto.getPay().isEmpty() || payDto.getAmount() < 0) {
                    return message =Message.AMOUNT_POSITIVE_NUMBER;
                }
                    return  message =clientDebtService.pay(payDto);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            return message = Message.ERROR_INPUT_MESSAGE;
        }

        return message;
    }
}
