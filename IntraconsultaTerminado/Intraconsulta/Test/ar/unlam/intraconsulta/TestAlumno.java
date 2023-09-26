package ar.unlam.intraconsulta;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestAlumno {

	@Test
	public void queSePuedaCrearUnAlumno() {
		//preparacion
		Integer dni = 29867073;
		String nombre = "Vicky";
		String apellido = "Gavagnin";
		
		//ejecucion
		Alumno vicky = new Alumno(dni, nombre, apellido);
		
		//validacion
		assertNotNull(vicky);
	}
	
	@Test
	public void queSePuedaComprobarUnID() {
		//preparacion
		Integer dni = 29867073, dni2 = 46364928, id1 = 1, id2 = 2;
		String nombre = "Vicky", nombre2 = "Lucas";
		String apellido = "Gavagnin", apellido2 = "Croce";
		
		
		//ejecucion
		Alumno vicky = new Alumno(dni, nombre, apellido);
		Alumno lucas = new Alumno(dni2, nombre2, apellido2);
		
		//validacion
		assertNotNull(vicky);
		assertEquals(id1, vicky.getId());
		assertEquals(id2, lucas.getId());
	}
}
