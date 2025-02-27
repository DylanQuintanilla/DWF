package sv.edu.udb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sv.edu.udb.Controller.Reserva;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {
}
