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

@DataJpaTest
public class PersonaRepositoryTest {

	@Autowired
	private PersonaRepository personaRepository;

	/**
	 * Test para la operación de CREAR (CREATE).
	 * Se crea una nueva Persona y se verifica que se asigne un ID tras guardarla.
	 */
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
	}

	/**
	 * Test para la operación de LEER (READ).
	 * Se crea una persona, se guarda y luego se lee desde la base de datos verificando su existencia.
	 */
	@Test
	public void testReadPersona() {
		Persona persona = Persona.builder()
				.nombrePersona("Test Read")
				.edadPersona(30)
				.telefonoPersona("987654321")
				.sexoPersona("Femenino")
				.idOcupacion(2)
				.fechaNac(LocalDate.of(1993, 4, 20))
				.build();
		Persona saved = personaRepository.save(persona);
		// Buscar la persona guardada por su ID.
		Optional<Persona> found = personaRepository.findById(saved.getIdPersona());
		// Verificar que la persona se encuentre en la BD.
		assertTrue(found.isPresent(), "La persona debería existir en la base de datos");
		assertEquals("Test Read", found.get().getNombrePersona(), "El nombre de la persona debe coincidir");
	}

	/**
	 * Test para la operación de ACTUALIZAR (UPDATE).
	 * Se crea una persona, se actualiza un campo y se verifica que la actualización persista.
	 */
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
		saved.setNombrePersona("Test Update Modified");
		Persona updated = personaRepository.save(saved);

		// Buscar la persona actualizada y verificar que el cambio se haya realizado.
		Optional<Persona> found = personaRepository.findById(updated.getIdPersona());
		assertTrue(found.isPresent(), "La persona actualizada debería existir");
		assertEquals("Test Update Modified", found.get().getNombrePersona(), "El nombre de la persona debe estar actualizado");
	}

	/**
	 * Test para la operación de ELIMINAR (DELETE).
	 * Se crea una persona, se guarda y luego se elimina. Se verifica que la persona ya no exista en la base de datos.
	 */
	@Test
	public void testDeletePersona() {
		Persona persona = Persona.builder()
				.nombrePersona("Test Delete")
				.edadPersona(40)
				.telefonoPersona("444555666")
				.sexoPersona("Masculino")
				.idOcupacion(4)
				.fechaNac(LocalDate.of(1983, 1, 1))
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
	}
}
