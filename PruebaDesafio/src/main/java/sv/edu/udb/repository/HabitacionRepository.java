package sv.edu.udb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sv.edu.udb.Controller.Habitacion;

import java.time.LocalDate;
import java.util.List;

public interface HabitacionRepository extends JpaRepository<Habitacion, Long> {

    // Método automático de Spring Data JPA (no requiere implementación)
    List<Habitacion> findByEstado(String estado);

    // Consulta personalizada con JPQL usando @Query
    @Query("SELECT h FROM Habitacion h WHERE h.estado = 'Disponible' AND h.id NOT IN " +
            "(SELECT r.habitacion.id FROM Reserva r WHERE :fecha BETWEEN r.fechaInicio AND r.fechaFin)")
    List<Habitacion> buscarDisponiblesPorFecha(@Param("fecha") LocalDate fecha);
}
