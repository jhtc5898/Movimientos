package com.nttdata.movimientos.entities;

import lombok.Data;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@Table(name = "mov_ttransactions")
public class Transactions implements Serializable {

    private static final long serialVersionUUID = 1L;

    @Id
    @GeneratedValue(generator = "ID")
    @GenericGenerator(name = "pk_transactions", strategy = "native")
    @Comment("identifier primary key")
    @Column(name = "idTransactions", nullable = false)
    private Long idTransactions;

    @Temporal(TemporalType.DATE)
    @Comment("Date transactions")
    @Column(name = "date", nullable = false)
    private Date date;

    @Comment("Identifier Type transactions")
    @Column(name = "typeAccount", nullable = false, length = 1)
    private String typeAccount;

    @Comment("Movement transactions")
    @Column(name = "movement", nullable = false, length = 1)
    private Long movement;

    @Comment("Decriptions transactions")
    @Column(name = "description", nullable = false, length = 1)
    private String description;

    @Comment("available balance")
    @Column(name = "availableBalance", nullable = false)
    private Long availableBalance;

    @Comment("Status transaction")
    @Column(name = "status", nullable = false)
    private Boolean status;

    @ManyToOne
    @JoinColumn(name = "account_id_account")
    private Account account;

}
