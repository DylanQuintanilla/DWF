package sv.edu.udb.Controller;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Habitacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String numero;

    @Column(nullable = false)
    private String tipo;// "Individual", "Doble", "Suite"

    @Column(nullable = false)
    private String estado; // "Disponible", "Reservada"
}
