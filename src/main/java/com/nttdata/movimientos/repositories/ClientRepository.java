package com.nttdata.movimientos.repositories;

import com.nttdata.movimientos.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    Client findByClientidentificationAndStatus(String clientIdentification, Boolean status);

    Client findByClientidentification(String clientIdentification);
}
