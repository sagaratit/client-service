package com.sg.ocbc.service;

import com.sg.ocbc.CLIENT_TYPE;
import com.sg.ocbc.constants.Message;
import com.sg.ocbc.domain.Account;
import com.sg.ocbc.domain.Client;
import com.sg.ocbc.domain.ClientDebt;
import com.sg.ocbc.dto.Login;
import com.sg.ocbc.dto.Logout;
import com.sg.ocbc.dto.RegisterClient;
import com.sg.ocbc.repository.AccountRepository;
import com.sg.ocbc.repository.ClientDebtRepository;
import com.sg.ocbc.repository.ClientRepository;
import com.sg.ocbc.utility.RandomUtility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Locale;

@Service
@Slf4j
@Transactional
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    ClientDebtRepository clientDebtRepository;

    /*
     * valid login and user
     * @param Login
     * @return String
     * */
    public String valid(Login login) {
        String message ;
        try {
            Client client = clientRepository.findAllByName(login.getUserName().trim());
            if (client == null) {
                return Message.PLEASE_REGISTER_FIRST;
            }

            if (client.getPassword().equals(RandomUtility.encodePassword(login.getPassword().trim())) && client.isLoggedIn()) {
                Account account = accountRepository.findAllByClientId(client.getId());
                 message =Message.HELLO + client.getName() +" "+ getOwingReceiverMessage(client.getName())  + "\n"+Message.YOUR_BALANCE_IS + account.getBalance() +"\n" +getOwingSederMessage(client.getName());
            } else {
                if (client.getPassword().trim().equals(RandomUtility.encodePassword(login.getPassword()))) {
                    client.setLoggedIn(true);
                    Account account = accountRepository.findAllByClientId(client.getId());
                    message =Message.HELLO + client.getName() +" "+getOwingSederMessage(client.getName())  + "\n"+Message.YOUR_BALANCE_IS + account.getBalance() +  getOwingReceiverMessage((client.getName()));
                    clientRepository.save(client);
                } else {
                    message = Message.INVALID_PASSWORD;
                }

            }
        } catch (Exception e) {
            log.error(e.getMessage());
            return Message.ERROR_INPUT_MESSAGE;
        }
        return message;
    }

    private String getOwingSederMessage(String userName){

        List<ClientDebt> clientDebtList = clientDebtRepository.findAllByPayerClientId(userName.trim());
        if(clientDebtList.isEmpty()){
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("\n Owing ");

        clientDebtList.forEach(elm->{
            stringBuffer.append(elm.getDebt()).append(" to ").append(elm.getPayeeClientId()).append("\n");
        });
        return stringBuffer.toString();
    }

    private String getOwingReceiverMessage(String userName){
        List<ClientDebt> clientDebtList = clientDebtRepository.findAllByPayeeClientId(userName.trim());
        if(clientDebtList.isEmpty()){
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Owing ");

        clientDebtList.forEach(elm->{
            stringBuffer.append(elm.getDebt()).append(" from ").append(elm.getPayerClientId()).append("\n");
        });
        return stringBuffer.toString();
    }

    /*
     * Logout to client
     * @param Logout
     * @return String
     * */
    public String logut(Logout logout) {
        String message = null;
        try {
            Client client = clientRepository.findAllByName(logout.getUserName());
            if (client == null) {
                return message = Message.PLEASE_REGISTER_FIRST;
            }
            client.setLoggedIn(false);
            clientRepository.save(client);
            message = Message.LOGOUT + client.getName();

        } catch (Exception e) {
            log.error(e.getMessage());
            return Message.ERROR_INPUT_MESSAGE;
        }
        return message;
    }

    /*
     * save new registered client
     * @param Logout
     * @return String
     * */
    public String save(RegisterClient client) {
        String message;
        try {
            Client clientExist = clientRepository.findAllByName(client.getClientName().trim());
            if (clientExist != null) {
                return Message.CLIENT_USERNAME_ALREADY_TAKEN;
            }


            Client newClient = Client.builder().id(RandomUtility.getUUID())
                    .loggedIn(false)
                    .email(client.getEmail())
                    .name(client.getClientName())
                    .password(RandomUtility.encodePassword(client.getPassword()))
                    .build();
            // password is encrypted
            newClient = clientRepository.save(newClient);
            Account account = Account.builder().clientId(newClient.getId()).accountType(CLIENT_TYPE.RETAIL.getCode()).balance(0.0).debt(0.0).build();
            account = accountRepository.save(account); // H2 not supporting having some issues hence break it down one by one
            message = Message.HELLO + newClient.getName() + " " + Message.YOUR_BALANCE_IS + account.getBalance();
        } catch (Exception e) {
            log.error(e.getMessage());
            return Message.ERROR_INPUT_MESSAGE;
        }

        return message;
    }
}
