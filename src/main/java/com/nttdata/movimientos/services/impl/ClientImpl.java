package com.nttdata.movimientos.services.impl;

import com.nttdata.movimientos.dto.ClientDTO;
import com.nttdata.movimientos.entities.Client;
import com.nttdata.movimientos.repositories.AccountRepository;
import com.nttdata.movimientos.repositories.ClientRepository;
import com.nttdata.movimientos.repositories.PersonRepository;
import com.nttdata.movimientos.services.ClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;


@Slf4j
@Service
public class ClientImpl implements ClientService {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    AccountRepository accountRepository;


    @Override
    @Transactional
    public Map<String, Object> createClient(ClientDTO.createClient clientDTO) {
        Map<String, Object> resp = new HashMap<>();
        log.debug("Service to save an client : {}", clientDTO);

        Client client = new Client();
        client.setIdentificationCard(clientDTO.getIdentificationCard());
        client.setName(clientDTO.getName());
        client.setGender(clientDTO.getGender());
        client.setAge(clientDTO.getAge());
        client.setDirection(clientDTO.getDirection());
        client.setPhone(clientDTO.getPhone());
        client.setClientidentification(clientDTO.getClientidentification());
        client.setPassword(clientDTO.getPassword());
        client.setStatus(Boolean.TRUE);
        clientRepository.save(client);

        resp.put("Save Client", clientDTO);
        return resp;
    }

    @Override
    @Transactional
    public Map<String, Object> editClient(ClientDTO.editClient clientDTO) {
        Map<String, Object> resp = new HashMap<>();
        log.debug("Edit client : {}", clientDTO);
        if (clientRepository.findByClientidentificationAndStatus(clientDTO.getClientidentification(), Boolean.TRUE) != null) {
            Client client = clientRepository.findByClientidentificationAndStatus(clientDTO.getClientidentification(), Boolean.TRUE);
            client.setIdPerson(client.getIdPerson());
            client.setName(clientDTO.getName());
            client.setGender(clientDTO.getGender());
            client.setAge(clientDTO.getAge());
            client.setDirection(clientDTO.getDirection());
            client.setPhone(clientDTO.getPhone());
            client.setClientidentification(clientDTO.getClientidentification());
            clientRepository.save(client);
            resp.put("Edit Client", clientDTO);
            return resp;
        }
        if (clientRepository.findByClientidentificationAndStatus(clientDTO.getClientidentification(), Boolean.FALSE) != null) {
            resp.put("Disabled Client", clientDTO);
            return resp;
        }
        resp.put("Check credentials", clientDTO);
        return resp;
    }

    @Override
    public Map<String, Object> listClient(ClientDTO.clientIdent clientDTO) {
        Map<String, Object> resp = new HashMap<>();
        log.debug("Info client : {}", clientDTO);
        if (clientRepository.findByClientidentificationAndStatus(clientDTO.getClientidentification(), Boolean.TRUE) != null) {
            Client client = clientRepository.findByClientidentificationAndStatus(clientDTO.getClientidentification(), Boolean.TRUE);

            if (accountRepository.findAllByClient_IdPersonAndStatus(client.getIdPerson(),Boolean.TRUE)!=null)
            {
                resp.put("Accounts",accountRepository.findAllByClient_IdPersonAndStatus(client.getIdPerson(),Boolean.TRUE));
            }
            else
            {
                resp.put("Null Accounts",null);
            }

            resp.put("Info Client", clientRepository.findByClientidentificationAndStatus(clientDTO.getClientidentification(), Boolean.TRUE));
            return resp;
        }
        resp.put("Check credentials", clientDTO);
        return resp;
    }

    @Override
    @Transactional
    public Map<String, Object> deleteClient(ClientDTO.clientIdent clientDTO) {
        Map<String, Object> resp = new HashMap<>();
        log.debug("Delete client : {}", clientDTO);

        if (clientRepository.findByClientidentificationAndStatus(clientDTO.getClientidentification(), Boolean.TRUE) != null) {
            Client client = clientRepository.findByClientidentificationAndStatus(clientDTO.getClientidentification(), Boolean.TRUE);
            client.setStatus(Boolean.FALSE);
            resp.put("Delete", Boolean.TRUE);
            return resp;
        }
        if (clientRepository.findByClientidentificationAndStatus(clientDTO.getClientidentification(), Boolean.FALSE) != null) {
            resp.put("Client Status", Boolean.FALSE);
            return resp;
        }
        resp.put("Check credentials", clientDTO);
        return resp;
    }

}
