package sv.edu.udb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sv.edu.udb.Controller.Habitacion;
import sv.edu.udb.repository.HabitacionRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class HabitacionService {
    @Autowired
    private HabitacionRepository habitacionRepo; // Inyección del bean

    public List<Habitacion> habitacionesDisponibles() {
        return habitacionRepo.findByEstado("Disponible");
    }

    public List<Habitacion> habitacionesDisponiblesPorFecha(LocalDate fecha) {
        return habitacionRepo.buscarDisponiblesPorFecha(fecha);
    }

    public Habitacion guardarHabitacion(Habitacion habitacion) {
        return habitacionRepo.save(habitacion);
    }

    public void eliminarHabitacion(Long id) {
        habitacionRepo.deleteById(id);
    }
}