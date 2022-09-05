package com.nttdata.movimientos.services;

import com.nttdata.movimientos.dto.ClientDTO;

import java.util.Map;

public interface ClientService {

    Map<String, Object> createClient(ClientDTO.createClient clientDTO);

    Map<String, Object> editClient(ClientDTO.editClient clientDTO);

    Map<String, Object> listClient(ClientDTO.clientIdent clientDTO);

    Map<String, Object> deleteClient(ClientDTO.clientIdent clientDTO);


}
