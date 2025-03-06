package com.udb.deafio.repository.domain;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "persona")
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_persona")
    private Integer idPersona;

    @Column(name = "nombre_persona", nullable = false, length = 100)
    private String nombrePersona;

    @Column(name = "edad_persona", nullable = false)
    private int edadPersona;

    @Column(name = "telefono_persona", nullable = false, length = 9)
    private String telefonoPersona;

    @Column(name = "sexo_persona", nullable = false, length = 50)
    private String sexoPersona;

    @Column(name = "id_ocupacion", nullable = false)
    private int idOcupacion;

    @Column(name = "fecha_nac", nullable = false)
    private LocalDate fechaNac;
}

