package com.nttdata.movimientos.repositories;

import com.nttdata.movimientos.entities.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface TransactionsRepository extends JpaRepository<Transactions, Long> {

    @Query(value = "SELECT * FROM movimientos.mov_ttransactions mtt WHERE mtt.date BETWEEN :dateinit AND :datefin AND mtt.account_id_account = :idAccount AND mtt.status=TRUE ", nativeQuery = true)
    List<Transactions> findDateEmployee(@Param("dateinit") Date dateinit, @Param("datefin") Date datefin, @Param("idAccount") Long idAccount);

    List<Transactions> findAllByAccount_IdAccount(Long idAccount);
}
