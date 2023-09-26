package ar.unlam.intraconsulta;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestAula {

	@Test
	public void queSePuedaCrearUnAula() {
		//Preparacion
		Integer numero = 45;
		
		//Ejecucion
		Aula aula = new Aula(numero);
		
		//Verificacion
		assertNotNull(aula);
	}
	
	@Test
	public void queSePuedaAsignarCursoAUnAula() {
		//Preparacion
		Integer numero = 45, codCurso = 1234, horario = 1200, horasSemanales = 4;
		Materia materia = new Materia(1223, "Matematicas");
		Periodos cuatri = Periodos.SEGUNDO_CUATRIMESTRE;
		
		//Ejecucion
		Aula aula = new Aula(numero);
		Curso curso = new Curso(codCurso, horario, horasSemanales, materia, cuatri, aula);
		aula.asignarCurso(curso);
		
		//Verificacion
		assertNotNull(aula);
		assertEquals(curso, aula.getCurso());
	}
	
	@Test
	public void queSePuedaConocerLaMateria() {
		//Preparacion
		Integer numero = 45, codCurso = 1234, horario = 1200, horasSemanales = 4;
		Materia materia = new Materia(1223, "Matematicas");
		Periodos cuatri = Periodos.SEGUNDO_CUATRIMESTRE;
		
		//Ejecucion
		Aula aula = new Aula(numero);
		Curso curso = new Curso(codCurso, horario, horasSemanales, materia, cuatri, aula);
		aula.asignarCurso(curso);
		
		
		//Verificacion
		assertNotNull(aula);
		assertEquals(curso, aula.getCurso());
		assertEquals(materia, aula.conocerMateria());
	}
	
	@Test
	public void queSePuedaEncontrarUnProfe() {
		//Preparacion
		Integer numero = 45, codCurso = 1234, horario = 1200, horasSemanales = 4;
		Materia materia = new Materia(1223, "Matematicas");
		Periodos cuatri = Periodos.SEGUNDO_CUATRIMESTRE;
		Profesor juan = new Profesor("Juan", 46366928);
		
		//Ejecucion
		Aula aula = new Aula(numero);
		Curso curso = new Curso(codCurso, horario, horasSemanales, materia, cuatri, aula);
		curso.agregarProfe(juan);
		aula.asignarCurso(curso);
		
		//Verificacion
		assertNotNull(aula);
		assertEquals(curso, aula.getCurso());
		assertEquals(juan, aula.buscarProfesor(juan.getDni()));
	}
	
	@Test
	public void queSePuedaConocerCapacidadDelAula() {
		//Preparacion
		Integer numero = 45, codCurso = 1234, horario = 1200, horasSemanales = 4;
		Materia materia = new Materia(1223, "Matematicas");
		Periodos cuatri = Periodos.SEGUNDO_CUATRIMESTRE;
		Profesor juan = new Profesor("Juan", 46366928);
		Integer capacidadEsperada = 20;
		
		//Ejecucion
		Aula aula = new Aula(numero);
		Curso curso = new Curso(codCurso, horario, horasSemanales, materia, cuatri, aula);
		curso.agregarProfe(juan);
		aula.asignarCurso(curso);
		
		//Verificacion
		assertNotNull(aula);
		assertEquals(curso, aula.getCurso());
		assertEquals(capacidadEsperada, aula.getCapacidad());
	}
}
