package com.nttdata.movimientos.repositories;

import com.nttdata.movimientos.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface AccountRepository extends JpaRepository<Account, Long> {

    Account findByIdAccountAndClient_IdPersonAndStatus(Long id, Long idClient, Boolean status);

    Account findByIdAccountAndClient_IdPersonAndStatusAndTypeAccount(Long id, Long idClient, Boolean status, String typeAccount);

    List<Account> findAllByClient_IdPersonAndStatus(Long idClient, Boolean status);
}
