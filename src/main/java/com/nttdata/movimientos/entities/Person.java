package com.nttdata.movimientos.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.experimental.Tolerate;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;


@Data
@MappedSuperclass
public class Person implements Serializable {

    private static final long serialVersionUUID = 1L;
    @Id
    @JsonIgnore
    @GeneratedValue(generator = "ID")
    @GenericGenerator(name = "pk_person", strategy = "native")
    @Comment("identifier primary key")
    @Column(name = "idPerson", nullable = false)
    private Long idPerson;

    @Comment("identification document")
    @Column(name = "identification_card", nullable = false, unique = true, length = 10)
    private String identificationCard;

    @Comment("Employee first name")
    @Column(name = "name", length = 200, nullable = false)
    private String name;

    @Comment("Employee gender")
    @Column(name = "gender", length = 1)
    private String gender;

    @Comment("Employee age")
    @Column(name = "age", length = 5)
    private Long age;

    @Comment("Employee Direction")
    @Column(name = "direction", length = 200, nullable = false)
    private String direction;

    @Comment("Employee Phone")
    @Column(name = "phone", length = 20)
    private String phone;

    @Tolerate
    public Person() {
        super();
    }

    public Person(String identificationCard, String name, String direction) {
        this.identificationCard = identificationCard;
        this.name = name;
        this.direction = direction;
    }
}
