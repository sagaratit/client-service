package com.sg.ocbc.service;

import com.sg.ocbc.constants.Message;
import com.sg.ocbc.domain.Account;
import com.sg.ocbc.domain.Client;
import com.sg.ocbc.domain.ClientDebt;
import com.sg.ocbc.dto.PayDto;
import com.sg.ocbc.repository.AccountRepository;
import com.sg.ocbc.repository.ClientDebtRepository;
import com.sg.ocbc.repository.ClientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Slf4j
@Transactional
public class ClientDebtService {

    @Autowired
    private ClientDebtRepository clientDebtRepository;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    private ClientService clientService;

    @Autowired
    private ClientRepository clientRepository;

    /*
     * pay amount to other client
     * @param PayDto
     * @return String
     * */
    public String pay(PayDto payDto) {
        String message = "Please validate inputs";

        try {
            Client client = clientRepository.findAllByName(payDto.getUserName().trim());
            Client receiverClient = clientRepository.findAllByName(payDto.getPay().trim());
            if (client == null || receiverClient == null) {
                return Message.PLEASE_REGISTER_FIRST;
            }
            if (!client.isLoggedIn()) {
                return message = Message.PLEASE_LOGIN_FIRST + client.getName();
            }
            Account account = accountRepository.findAllByClientId(client.getId());
            Account receiverAccount = accountRepository.findAllByClientId(receiverClient.getId());
            if (account.getBalance() >= payDto.getAmount()) {
                account.setBalance(account.getBalance() - payDto.getAmount());
                receiverAccount.setBalance(receiverAccount.getBalance() + payDto.getAmount());
                accountRepository.saveAll(List.of(account, receiverAccount));
                return message = Message.TRANSFERRED  + payDto.getAmount() + " to " + payDto.getPay() +
                        "\n" + Message.YOUR_BALANCE_IS + account.getBalance();
            } else {
                ClientDebt clientDebt = clientDebtRepository.findAllByPayerClientIdAndPayeeClientId(payDto.getUserName().trim(),payDto.getPay().trim());
                if (clientDebt == null) {
                    clientDebt = ClientDebt.builder().payerClientId(payDto.getUserName().trim())
                            .payeeClientId(payDto.getPay().trim()).debt(payDto.getAmount() - account.getBalance()).build();
                } else {
                    double totalDebt = clientDebt.getDebt() + (payDto.getAmount() - account.getBalance());
                    clientDebt.setDebt(totalDebt);
                }
                clientDebt = clientDebtRepository.save(clientDebt);
                receiverAccount.setBalance(receiverAccount.getBalance() + account.getBalance());
                account.setBalance(0.0);
                accountRepository.saveAll(List.of(account, receiverAccount));
                return message = Message.TRANSFERRED + payDto.getAmount() + " to " + payDto.getPay() + "\n" +
                        Message.YOUR_BALANCE_IS + account.getBalance() + "\n" +
                        "Owing " + clientDebt.getDebt() + " to " + payDto.getPay() + ".";
            }

        } catch (Exception e) {
            log.info(e.getMessage());
            message = Message.ERROR_INPUT_MESSAGE;
        }

        return message;
    }
}
