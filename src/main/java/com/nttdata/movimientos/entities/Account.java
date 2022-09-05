package com.nttdata.movimientos.entities;

import lombok.Data;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "mov_taccount")
public class Account implements Serializable {

    private static final long serialVersionUUID = 1L;

    @Id
    @GeneratedValue(generator = "IDACCOUNT")
    @GenericGenerator(name = "pk_account", strategy = "native")
    @Comment("identifier primary key")
    @Column(name = "idAccount", nullable = false)
    private Long idAccount;

    @Comment("Identifier Type Account")
    @Column(name = "typeAccount", nullable = false, length = 1)
    private String typeAccount;

    @Comment("Initial Balance Client")
    @Column(name = "initialBalance", nullable = false, length = 10)
    private Long initialBalance;

    @Comment("Status Client ")
    @Column(name = "status", nullable = false)
    private Boolean status;

    @ManyToOne
    @JoinColumn(name = "client_id_person")
    private Client client;
}
