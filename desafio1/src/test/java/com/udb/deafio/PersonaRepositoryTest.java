package com.udb.deafio;

import com.udb.deafio.repository.PersonaRepository;
import com.udb.deafio.repository.domain.Persona;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest //Esta anotacion nos sirve le da entender a Spring que es para hacer test con una base de datos real
//@DataJpaTest Esta nos sirve tambien para hacer test, pero con una base de datos embebida
public class PersonaRepositoryTest {

	@Autowired
	private PersonaRepository personaRepository;

	@Test
	public void testCreatePersona() {
		Persona persona = Persona.builder()
				.nombrePersona("Test Create")
				.edadPersona(28)
				.telefonoPersona("123456789")
				.sexoPersona("Masculino")
				.idOcupacion(1)
				.fechaNac(LocalDate.of(1997, 8, 15))
				.build();
		Persona saved = personaRepository.save(persona);
		// Verificar que la persona tenga un ID asignado (no nulo) después de guardarla.
		assertNotNull(saved.getIdPersona(), "El ID debe asignarse tras guardar la persona");

		/*Este test empieza con la creacion de un objeto de la clase Persona con sus respectivos datos,
		para luego con los metodos que incluye JPARespository que se encunetra en la interfaz PersonaRepository
		proceder a guardarlo y luego por ultimo verificar que este no sea nulo, es decir que el objeto haya sido
		creado correctamente*/
	}


	@Test
	public void testReadPersona() {
		Persona persona = Persona.builder()
				.nombrePersona("Test Read")
				.edadPersona(28)
				.telefonoPersona("123456789")
				.sexoPersona("Masculino")
				.idOcupacion(1)
				.fechaNac(LocalDate.of(1997, 8, 15))
				.build();
		Persona saved = personaRepository.save(persona);
		// Buscar la persona guardada por su ID.
		Optional<Persona> found = personaRepository.findById(saved.getIdPersona());
		// Verificar que la persona se encuentre en la BD.
		assertTrue(found.isPresent(), "La persona debería existir en la base de datos");
		assertEquals("Test Read", found.get().getNombrePersona(), "El nombre de la persona debe coincidir");

		/*Empezamos con la creacion de un objeto de la clase Persona, y lo guardamos en la BD, procedemos con una busqueda
		en la BD con la el id propio que se le haya dado para luego probar que sea el mismo nombre del cual le habiamos
		dado al principio del test*/

	}

	@Test
	public void testUpdatePersona() {
		Persona persona = Persona.builder()
				.nombrePersona("Test Update")
				.edadPersona(32)
				.telefonoPersona("111222333")
				.sexoPersona("Femenino")
				.idOcupacion(3)
				.fechaNac(LocalDate.of(1991, 3, 10))
				.build();
		Persona saved = personaRepository.save(persona);

		// Actualizar el nombre de la persona
		saved.setNombrePersona("Test Update Modificado");
		Persona updated = personaRepository.save(saved);

		// Buscar la persona actualizada y verificar que el cambio se haya realizado.
		Optional<Persona> found = personaRepository.findById(updated.getIdPersona());
		assertTrue(found.isPresent(), "La persona actualizada debería existir");
		assertEquals("Test Update Modificado", found.get().getNombrePersona(), "El nombre de la persona debe estar actualizado");

		/*Procedemos con la creacion de un objeto Persona, para luego guardarlo, como siguiente procedemos a actualizar el nombre
		* verificamos que el onjeto exista en la BD y comprobamos que el nombre si haya sido actualizado correctamente*/
	}

	@Test
	public void testDeletePersona() {
		Persona persona = Persona.builder()
				.nombrePersona("Test Delete")
				.edadPersona(28)
				.telefonoPersona("123456789")
				.sexoPersona("Masculino")
				.idOcupacion(1)
				.fechaNac(LocalDate.of(1997, 8, 15))
				.build();
		Persona saved = personaRepository.save(persona);

		// Confirmar que la persona fue guardada y existe en la base de datos.
		Optional<Persona> found = personaRepository.findById(saved.getIdPersona());
		assertTrue(found.isPresent(), "La persona debe existir antes de eliminarla");

		// Eliminar la persona.
		personaRepository.delete(saved);

		// Verificar que la persona ya no exista.
		Optional<Persona> deleted = personaRepository.findById(saved.getIdPersona());
		assertFalse(deleted.isPresent(), "La persona debe haber sido eliminada de la base de datos");

		/*Igual que las otras esta empieza con la creacion de una persona, luego comprobamos que se haya creado correctamente
		procedemos a insertarla y probamos que de falso el hecho que encuentre el objeto por id, ya que este no existe*/
	}
}
