package com.nttdata.movimientos.services.impl;

import com.nttdata.movimientos.dto.AccountDTO;
import com.nttdata.movimientos.entities.Account;
import com.nttdata.movimientos.entities.Client;
import com.nttdata.movimientos.repositories.AccountRepository;
import com.nttdata.movimientos.repositories.ClientRepository;
import com.nttdata.movimientos.services.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

import static com.nttdata.movimientos.Utils.Constants.SALDO_INICIAL;

@Slf4j
@Service
public class AccountImpl implements AccountService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    ClientRepository clientRepository;

    @Override
    @Transactional
    public Map<String, Object> createAccount(AccountDTO.createAccount createAccount) {
        Map<String, Object> resp = new HashMap<>();
        Client client = clientRepository.findByClientidentification(createAccount.getClientidentification());

        if (client != null) {
            if (client.getStatus() == Boolean.TRUE) {

                Account account = new Account();
                account.setStatus(Boolean.TRUE);
                account.setInitialBalance(SALDO_INICIAL);
                account.setTypeAccount(createAccount.getTypeAccount());
                account.setClient(client);
                accountRepository.save(account);
                resp.put("Create Account", account);
                return resp;
            }
            resp.put("Disabled Client", createAccount);
            return resp;
        }
        resp.put("Revisar Credenciales", createAccount);
        return resp;
    }

    /*
    We add the type of account
     */
    @Override
    @Transactional
    public Map<String, Object> editAccount(AccountDTO.editAccount editAccount) {
        Map<String, Object> resp = new HashMap<>();
        Client client = clientRepository.findByClientidentification(editAccount.getClientidentification());
        if (client != null) {
            if (client.getStatus() == Boolean.TRUE)  //Validamos que el empleado esta activo
            {
                Account account = accountRepository.findByIdAccountAndClient_IdPersonAndStatus(editAccount.getIdAccount(), client.getIdPerson(), Boolean.TRUE);
                if (account != null) {
                    account.setIdAccount(account.getIdAccount());
                    account.setTypeAccount(editAccount.getTypeAccount());
                    accountRepository.save(account);
                    resp.put("Save", account);
                    return resp;
                }
                resp.put("Validated credentials", account);
                return resp;
            }

        }
        resp.put("Validated credentials", editAccount);
        return resp;
    }


    @Override
    public Map<String, Object> infoAccount(AccountDTO.infoAccount infoAccount) {
        Map<String, Object> resp = new HashMap<>();
        Client client = clientRepository.findByClientidentification(infoAccount.getClientidentification());
        if (client != null) {
            if (client.getStatus() == Boolean.TRUE)  //Validamos que el empleado esta activo
            {
                Account account = accountRepository.findByIdAccountAndClient_IdPersonAndStatus(infoAccount.getIdAccount(), client.getIdPerson(), Boolean.TRUE);
                if (account != null) {
                    resp.put("Info", account);
                    return resp;
                }
                resp.put("Validated credentials", account);
                return resp;
            }
        }
        resp.put("Validated credentials", infoAccount);
        return resp;

    }

    @Override
    @Transactional
    public Map<String, Object> deleteAccount(AccountDTO.deleteAccount deleteAccount) {
        Map<String, Object> resp = new HashMap<>();
        Client client = clientRepository.findByClientidentification(deleteAccount.getClientidentification());
        if (client != null) {
            if (client.getStatus() == Boolean.TRUE)  //Validamos que el empleado esta activo
            {
                Account account = accountRepository.findByIdAccountAndClient_IdPersonAndStatus(deleteAccount.getIdAccount(), client.getIdPerson(), Boolean.TRUE);
                if (account != null) {
                    account.setIdAccount(account.getIdAccount());
                    account.setStatus(Boolean.FALSE);
                    accountRepository.save(account);
                    resp.put("Delete", account);
                    return resp;
                }
                resp.put("Validated Account", deleteAccount);
                return resp;
            }
        }
        resp.put("Validated credentials", deleteAccount);
        return resp;

    }
}
