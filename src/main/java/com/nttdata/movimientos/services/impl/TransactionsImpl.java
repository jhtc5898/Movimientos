package com.nttdata.movimientos.services.impl;

import com.nttdata.movimientos.dto.TransactionsDTO;
import com.nttdata.movimientos.entities.Account;
import com.nttdata.movimientos.entities.Client;
import com.nttdata.movimientos.entities.Transactions;
import com.nttdata.movimientos.repositories.AccountRepository;
import com.nttdata.movimientos.repositories.ClientRepository;
import com.nttdata.movimientos.repositories.TransactionsRepository;
import com.nttdata.movimientos.services.TransactionsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

import static com.nttdata.movimientos.Utils.Util.parseDate;

@Slf4j
@Service
public class TransactionsImpl implements TransactionsService {
    @Autowired
    TransactionsRepository transactionsRepository;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    ClientRepository clientRepository;

    @Override
    @Transactional
    public Map<String, Object> createTransactions(TransactionsDTO.createTransactions createTransactions) {
        Map<String, Object> resp = new HashMap<>();
        //Obtenemos el cliente
        Client client = clientRepository.findByClientidentificationAndStatus(createTransactions.getClientidentification(), Boolean.TRUE);
        if (client != null) {
            Account account = accountRepository.findByIdAccountAndClient_IdPersonAndStatusAndTypeAccount(createTransactions.getIdAccount(), client.getIdPerson(), Boolean.TRUE, createTransactions.getTypeAccount());
            if (account != null) {


                if (createTransactions.getTypeTransaccion().equals("-")) {
                    if (account.getInitialBalance() - createTransactions.getTransaction() < 0) {
                        resp.put("Sin Fondos", createTransactions);
                        return resp;
                    }
                }

                Transactions trans = new Transactions();
                trans.setDate(new Date());
                trans.setTypeAccount(createTransactions.getTypeAccount());
                trans.setMovement(createTransactions.getTransaction());
                if (createTransactions.getTypeTransaccion().equals("+")) {
                    trans.setAvailableBalance(account.getInitialBalance() + createTransactions.getTransaction());
                }
                if (createTransactions.getTypeTransaccion().equals("-")) {
                    trans.setAvailableBalance(account.getInitialBalance() - createTransactions.getTransaction());
                }
                trans.setStatus(Boolean.TRUE);
                trans.setAccount(account);
                trans.setDescription(createTransactions.getTypeTransaccion());
                transactionsRepository.save(trans);

                account.setInitialBalance(trans.getAvailableBalance());
                accountRepository.save(account);

                resp.put("Save", trans);
                return resp;
            }
        }
        resp.put("Validates Account", createTransactions);
        return resp;
    }

    @Override
    public Map<String, Object> listTransactions(TransactionsDTO.listTransactions listTransactions) throws ParseException {
        Map<String, Object> resp = new HashMap<>();
        Date init = parseDate(listTransactions.getDateInit());
        Date fin = parseDate(listTransactions.getDateFin());
        List<Long> listDebito = new ArrayList<>();
        List<Long> listCredito = new ArrayList<>();


        //Obtenemos el cliente
        Client client = clientRepository.findByClientidentificationAndStatus(listTransactions.getClientidentification(), Boolean.TRUE);
        if (client != null) {
            List<Account> accounts = accountRepository.findAllByClient_IdPersonAndStatus(client.getIdPerson(), Boolean.TRUE);
            if (!accounts.isEmpty()) {
                List<Transactions> listTran = new ArrayList<>();
                AtomicInteger cont = new AtomicInteger();
                accounts.forEach(account -> {
                    List<Transactions> listTransaccion = transactionsRepository.findDateEmployee(init, fin, account.getIdAccount());
                    if (!listTransaccion.isEmpty()) {
                        listTransaccion.forEach(movement ->
                        {
                            cont.getAndIncrement();
                            if (movement.getDescription().equals("-")) {
                                listDebito.add(movement.getMovement());
                            }
                            if (movement.getDescription().equals("+")) {
                                listCredito.add(movement.getMovement());

                            }
                        });
                        listTran.addAll(listTransaccion);
                    }
                });

                resp.put("Debito", listDebito.stream().mapToLong(Long::longValue).sum());
                resp.put("Credito", listCredito.stream().mapToLong(Long::longValue).sum());
                resp.put("Transactions", listTran);
                return resp;
            }
            resp.put("Validates Credentials", listTransactions);
            return resp;
        }
        resp.put("Validates Credentials", listTransactions);
        return resp;
    }

    @Override
    public Map<String, Object> deleteTransactions(TransactionsDTO.deleteTransactions deleteTransactions) {
        Map<String, Object> resp = new HashMap<>();
        Transactions trans = transactionsRepository.findById(deleteTransactions.getIdTransactions()).get();
        if (trans != null) {
            trans.setIdTransactions(trans.getIdTransactions());
            trans.setStatus(Boolean.FALSE);
            transactionsRepository.save(trans);
            resp.put("Delete", Boolean.TRUE);
            return resp;
        }
        resp.put("Validates Credentials", deleteTransactions);
        return resp;
    }

}
