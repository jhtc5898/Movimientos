package com.nttdata.movimientos.services;

import com.nttdata.movimientos.dto.AccountDTO;

import java.util.Map;

public interface AccountService {
    Map<String, Object> createAccount(AccountDTO.createAccount createAccount);

    Map<String, Object> editAccount(AccountDTO.editAccount editAccount);

    Map<String, Object> infoAccount(AccountDTO.infoAccount infoAccount);

    Map<String, Object> deleteAccount(AccountDTO.deleteAccount deleteAccount);


}
