package com.udb.deafio.repository.domain;


import jakarta.persistence.*;
import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Ocupacion")
public class Ocupacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idOcupacion;
    @Column (name = "nombre_ocupacion", nullable = false)
    private int nombreOcupacion;
}
