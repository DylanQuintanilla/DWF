package com.udb.deafio.repository;

import com.udb.deafio.repository.domain.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//La clase que nos ayudara a hacer los metodos para los crud
@Repository
public interface PersonaRepository extends JpaRepository<Persona, Integer> {
}
