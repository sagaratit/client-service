package com.sg.ocbc.service;

import com.sg.ocbc.constants.Message;
import com.sg.ocbc.domain.Account;
import com.sg.ocbc.domain.Client;
import com.sg.ocbc.domain.ClientDebt;
import com.sg.ocbc.dto.TopUpDto;
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
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ClientService clientService;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ClientDebtRepository clientDebtRepository;

    /*
    * TopUp to load money
    * @param TopUpDto
    * @return String
    * */
    public String topUp(TopUpDto topUpDto) {
        String message;

        try {
            Client client = clientRepository.findAllByName(topUpDto.getUserName().trim());
            if (client == null) {
               return Message.REGISTER_CLIENT_USER_FIRST;
            }
            if (!client.isLoggedIn()) {
               return Message.PLEASE_LOGIN_FIRST + client.getName();
            }

            List<ClientDebt> clientDebtList= clientDebtRepository.findAllByPayerClientId(client.getName());
            Account account = accountRepository.findAllByClientId(client.getId());
           for(ClientDebt clientDebt:clientDebtList){
               if(clientDebt.getDebt() > 0){
                   account.setBalance(0);
                   clientDebt.setDebt(clientDebt.getDebt() - topUpDto.getAmount());
                   clientDebtRepository.save(clientDebt);
               }
           }
           if(clientDebtList.isEmpty()){
               account.setBalance(account.getBalance() + topUpDto.getAmount());
           }
            account = accountRepository.save(account);
            return  Message.YOUR_BALANCE_IS + account.getBalance();
        }
         catch (Exception e){
              log.info(e.getMessage());
             message = Message.ERROR_INPUT_MESSAGE;
         }

        return message;
    }
}
