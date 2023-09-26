package ar.unlam.intraconsulta;

import static org.junit.Assert.*;
import java.util.ArrayList;

import org.junit.Test;

public class TestMateria {

	@Test
	public void queSePuedaCrearUnaMateria() {
		//preparacion
		Integer codigo = 2423;
		String nombre = "Programacion";
		
		//ejecucion
		Materia programacion = new Materia(codigo, nombre);
		
		//validacion
		assertNotNull(programacion);
	}
	
	@Test
	public void queSePuedaCrearAgregarUnaCorrelativa() {
		//preparacion
		Integer codigo = 2423;
		String nombre = "Programacion";
		ArrayList<Materia> correlativas = new ArrayList<Materia>();
		Materia programacionBasica1 = new Materia(2112, "programacionBasica");
		
		//ejecucion
		Materia programacion = new Materia(codigo, nombre);
		programacion.agregarCorrelativa(programacionBasica1);
		
		//validacion
		assertEquals(programacionBasica1, programacion.buscarCorrelativa(programacionBasica1));	
	}
}
