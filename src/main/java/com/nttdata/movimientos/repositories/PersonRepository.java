package com.nttdata.movimientos.repositories;

import com.nttdata.movimientos.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
