package com.nttdata.movimientos.entities;

import lombok.Data;
import org.hibernate.annotations.Comment;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Data
@Table(name = "mov_tclient")
public class Client extends Person implements Serializable {

    private static final long serialVersionUUID = 1L;


    @Comment("Cliente Identificacion")
    @Column(name = "clientidentification", nullable = false, unique = true, length = 200)
    private String clientidentification;

    @Comment("Client password")
    @Column(name = "password", nullable = false, length = 20)
    private String password;

    @Comment("Status Client ")
    @Column(name = "status", nullable = false)
    private Boolean status;


    public Client() {

    }
}
