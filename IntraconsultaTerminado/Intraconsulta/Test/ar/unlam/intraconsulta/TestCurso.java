package ar.unlam.intraconsulta;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestCurso {

	@Test
	public void queSePuedaCrearUnCurso() {
		//Preparacion
		Integer codigoCurso = 5557, horario = 1200, horasSemanales = 4;
		Materia matematicas = new Materia(1771, "Matematicas");
		Periodos periodo = Periodos.PRIMER_CUATRIMESTRE;
		Aula aula = new Aula(45);
		
		//Ejecucion
		Curso curso = new Curso(codigoCurso, horario, horasSemanales, matematicas, periodo, aula);
		
		
		//Validacion
		assertNotNull(curso);
	}
	
	@Test
	public void queSePuedanAsignarFechas() {
		//Preparacion
		Integer codigoCurso = 5557, horario = 1200, horasSemanales = 4;
		Materia matematicas = new Materia(1771, "Matematicas");
		Periodos periodo = Periodos.PRIMER_CUATRIMESTRE;
		Aula aula = new Aula(45);
		Integer fechaInicio = 0103, fechaFinalizacion = 2506, fechaInscripciones = 0102;
		
		//Ejecucion
		Curso curso = new Curso(codigoCurso, horario, horasSemanales, matematicas, periodo, aula);
		curso.asignarFechaInscripciones(fechaInscripciones);
		curso.asignarInicioYFinDePeriodo(fechaInicio, fechaFinalizacion);
		
		//Validacion
		assertNotNull(curso);
		assertEquals(fechaInicio, curso.getFechaInicial());
		assertEquals(fechaFinalizacion, curso.getFechaFinalizacion());
		assertEquals(fechaInscripciones, curso.getFechaInscripciones());
	}

	@Test
	public void queSePuedaAsignarUnProfesor() {
		//Preparacion
		Integer codigoCurso = 5557, horario = 1200, horasSemanales = 4;
		Materia matematicas = new Materia(1771, "Matematicas");
		Periodos periodo = Periodos.PRIMER_CUATRIMESTRE;
		Aula aula = new Aula(45);
		Profesor profeMati = new Profesor("Matias", 46364928);
		
		//Ejecucion
		Curso curso = new Curso(codigoCurso, horario, horasSemanales, matematicas, periodo, aula);
		curso.agregarProfe(profeMati);
		aula.asignarCurso(curso);
		
		//Validacion
		assertNotNull(curso);
		assertTrue(curso.existeProfe(profeMati.getDni()));
	}
	
	@Test
	public void queSePuedaAsignarUnAlumno() {
		//Preparacion
		Integer codigoCurso = 5557, horario = 1200, horasSemanales = 4;
		Materia matematicas = new Materia(1771, "Matematicas");
		Periodos periodo = Periodos.PRIMER_CUATRIMESTRE;
		Aula aula = new Aula(45);
		Alumno facundo = new Alumno(46364928, "Facundo", "Mamani Quispe");
		Profesor profeMati = new Profesor("Matias", 46364928);
		
		//Ejecucion
		Curso curso = new Curso(codigoCurso, horario, horasSemanales, matematicas, periodo, aula);
		curso.agregarProfe(profeMati);
		aula.asignarCurso(curso);
		curso.agregarAlumno(facundo);
		
		//Validacion
		assertNotNull(curso);
		assertTrue(curso.existeAlumno(facundo.getDni()));
	}
	
	@Test
	public void queSePuedaCrearUnaCursada() {
		//Preparacion
		Integer codigoCurso = 5557, horario = 1200, horasSemanales = 4;
		Materia matematicas = new Materia(1771, "Matematicas");
		Periodos periodo = Periodos.PRIMER_CUATRIMESTRE;
		Aula aula = new Aula(45);
		Alumno facundo = new Alumno(46364928, "Facundo", "Mamani Quispe");
		Profesor profeMati = new Profesor("Matias", 46364928);
		
		//Ejecucion
		Curso curso = new Curso(codigoCurso, horario, horasSemanales, matematicas, periodo, aula);
		curso.agregarProfe(profeMati);
		aula.asignarCurso(curso);
		curso.agregarAlumno(facundo);
		
		//Validacion
		assertNotNull(curso);
		assertTrue(curso.existeCursada(facundo.getDni()));
	}
	
	@Test
	public void queSePuedanObtenerLosResultadosDeExamenDeUnAlumnoYPromocione() {
		//Preparacion
		Integer codigoCurso = 5557, horario = 1200, horasSemanales = 4;
		Materia matematicas = new Materia(1771, "Matematicas");
		Periodos periodo = Periodos.PRIMER_CUATRIMESTRE;
		Aula aula = new Aula(45);
		Alumno facundo = new Alumno(46364928, "Facundo", "Mamani Quispe");
		Integer parcial1 = 10, parcial2 = 9;
		Profesor profeMati = new Profesor("Matias", 46364928);
		
		//Ejecucion
		Curso curso = new Curso(codigoCurso, horario, horasSemanales, matematicas, periodo, aula);
		curso.agregarProfe(profeMati);
		aula.asignarCurso(curso);
		curso.agregarAlumno(facundo);
		curso.obtenerResultadosPruebasDeAlumno(facundo.getDni(), parcial1, parcial2);
		
		//Validacion
		assertNotNull(curso);
		assertTrue(curso.existeAlumno(facundo.getDni()));
		assertEquals(Calificacion.PROMOCIONA, curso.verEstadoDelAlumno(facundo.getDni()));
	}
	
	@Test
	public void queHayaFinalDisponibleConRecuperatorio() {
		//Preparacion
		Integer codigoCurso = 5557, horario = 1200, horasSemanales = 4;
		Materia matematicas = new Materia(1771, "Matematicas");
		Periodos periodo = Periodos.PRIMER_CUATRIMESTRE;
		Aula aula = new Aula(45);
		Alumno facundo = new Alumno(46364928, "Facundo", "Mamani Quispe");
		Integer parcial1 = 10, parcial2 = 5;
		Integer notaRecup = 6;
		Profesor profeMati = new Profesor("Matias", 46364928);
		
		//Ejecucion
		Curso curso = new Curso(codigoCurso, horario, horasSemanales, matematicas, periodo, aula);
		curso.agregarProfe(profeMati);
		aula.asignarCurso(curso);
		curso.agregarAlumno(facundo);
		curso.obtenerResultadosPruebasDeAlumno(facundo.getDni(), parcial1, parcial2);
		curso.obtenerResultadosRecupAlumno(facundo.getDni(), notaRecup);
		
		//Validacion
		assertNotNull(curso);
		assertTrue(curso.existeAlumno(facundo.getDni()));
		assertTrue(curso.buscarCursada(facundo.getDni()).finalDisponible());
	}
	
	@Test
	public void queHayaFinalDisponibleSinRecuperatorio() {
		//Preparacion
		Integer codigoCurso = 5557, horario = 1200, horasSemanales = 4;
		Materia matematicas = new Materia(1771, "Matematicas");
		Periodos periodo = Periodos.PRIMER_CUATRIMESTRE;
		Aula aula = new Aula(45);
		Alumno facundo = new Alumno(46364928, "Facundo", "Mamani Quispe");
		Integer parcial1 = 10, parcial2 = 5;
		Profesor profeMati = new Profesor("Matias", 46364928);
		
		//Ejecucion
		Curso curso = new Curso(codigoCurso, horario, horasSemanales, matematicas, periodo, aula);
		curso.agregarProfe(profeMati);
		aula.asignarCurso(curso);
		curso.agregarAlumno(facundo);
		curso.obtenerResultadosPruebasDeAlumno(facundo.getDni(), parcial1, parcial2);
		
		//Validacion
		assertNotNull(curso);
		assertTrue(curso.existeAlumno(facundo.getDni()));
		assertTrue(curso.buscarCursada(facundo.getDni()).finalDisponible());
	}
	
	
	@Test
	public void queSeRecurseEnUnFinal() {
		//Preparacion
		Integer codigoCurso = 5557, horario = 1200, horasSemanales = 4;
		Materia matematicas = new Materia(1771, "Matematicas");
		Periodos periodo = Periodos.PRIMER_CUATRIMESTRE;
		Aula aula = new Aula(45);
		Alumno facundo = new Alumno(46364928, "Facundo", "Mamani Quispe");
		Integer parcial1 = 10, parcial2 = 5;
		Integer notaRecup = 6, notaDelFinal = 1;
		Profesor profeMati = new Profesor("Matias", 46364928);
		
		//Ejecucion
		Curso curso = new Curso(codigoCurso, horario, horasSemanales, matematicas, periodo, aula);
		curso.agregarProfe(profeMati);
		aula.asignarCurso(curso);
		curso.agregarAlumno(facundo);
		curso.obtenerResultadosPruebasDeAlumno(facundo.getDni(), parcial1, parcial2);
		curso.obtenerResultadosRecupAlumno(facundo.getDni(), notaRecup);
		curso.obtenerResultadosFinalDeAlumno(facundo.getDni(), notaDelFinal);
		
		//Validacion
		assertNotNull(curso);
		assertTrue(curso.existeAlumno(facundo.getDni()));
		assertTrue(curso.buscarCursada(facundo.getDni()).finalDisponible());
		assertEquals(Calificacion.RECURSA, curso.verEstadoDelAlumno(facundo.getDni()));
	}
	
	@Test
	public void queSeApruebeEnUnFinal() {
		//Preparacion
		Integer codigoCurso = 5557, horario = 1200, horasSemanales = 4;
		Materia matematicas = new Materia(1771, "Matematicas");
		Periodos periodo = Periodos.PRIMER_CUATRIMESTRE;
		Aula aula = new Aula(45);
		Alumno facundo = new Alumno(46364928, "Facundo", "Mamani Quispe");
		Integer parcial1 = 10, parcial2 = 5;
		Integer notaRecup = 6, notaDelFinal = 9;
		Profesor profeMati = new Profesor("Matias", 46364928);
		
		//Ejecucion
		Curso curso = new Curso(codigoCurso, horario, horasSemanales, matematicas, periodo, aula);
		curso.agregarProfe(profeMati);
		aula.asignarCurso(curso);
		curso.agregarAlumno(facundo);
		curso.obtenerResultadosPruebasDeAlumno(facundo.getDni(), parcial1, parcial2);
		curso.obtenerResultadosRecupAlumno(facundo.getDni(), notaRecup);
		curso.obtenerResultadosFinalDeAlumno(facundo.getDni(), notaDelFinal);
		
		//Validacion
		assertNotNull(curso);
		assertTrue(curso.existeAlumno(facundo.getDni()));
		assertTrue(curso.buscarCursada(facundo.getDni()).finalDisponible());
		assertEquals(Calificacion.APROBADO, curso.verEstadoDelAlumno(facundo.getDni()));
	}
	
	@Test
	public void saberSiHayEspacio() {
		//Preparacion
		Integer codigoCurso = 5557, horario = 1200, horasSemanales = 4;
		Materia matematicas = new Materia(1771, "Matematicas");
		Periodos periodo = Periodos.PRIMER_CUATRIMESTRE;
		Aula aula = new Aula(45);
		Profesor profeMati = new Profesor("Matias", 46364928);
		Alumno facundo = new Alumno(46364928, "Facundo", "Mamani Quispe");
		
		//Ejecucion
		Curso curso = new Curso(codigoCurso, horario, horasSemanales, matematicas, periodo, aula);
		curso.agregarProfe(profeMati);
		aula.asignarCurso(curso);
		curso.agregarAlumno(facundo);
		
		//Validacion
		assertNotNull(curso);
		assertTrue(curso.existeProfe(profeMati.getDni()));
		assertTrue(curso.existeAlumno(facundo.getDni()));
		assertTrue(curso.hayEspacio());
	}
	
	@Test
	public void saberSiNoHayEspacio() {
		//Preparacion
		Integer codigoCurso = 5557, horario = 1200, horasSemanales = 4;
		Materia matematicas = new Materia(1771, "Matematicas");
		Periodos periodo = Periodos.PRIMER_CUATRIMESTRE;
		Aula aula = new Aula(45);
		Alumno facundo = new Alumno(46364928, "Facundo", "Mamani Quispe");
		
		//Ejecucion
		Curso curso = new Curso(codigoCurso, horario, horasSemanales, matematicas, periodo, aula);
		aula.asignarCurso(curso);
		curso.agregarAlumno(facundo);
		
		//Validacion
		assertNotNull(curso);
		assertFalse(curso.existeAlumno(facundo.getDni()));
		assertFalse(curso.hayEspacio());
	}
}
