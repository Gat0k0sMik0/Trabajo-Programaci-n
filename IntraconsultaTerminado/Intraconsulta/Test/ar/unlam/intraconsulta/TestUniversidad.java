package ar.unlam.intraconsulta;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestUniversidad {

	@Test
	public void queSePuedaCrearUnaUniversidad() {
		// Preparacion
		String nombre = "UNLAM";

		// Ejecucion
		Universidad unlam = new Universidad(nombre);

		// Verificacion
		assertNotNull(unlam);
	}

	@Test
	public void queSePuedaRegistrarYBuscarUnProfe() {
		// Preparacion
		String nombre = "UNLAM", nombreProfe = "Pablo";
		Integer dni = 12345678;

		// Ejecucion
		Universidad unlam = new Universidad(nombre);
		Profesor pablito = new Profesor(nombreProfe, dni);
		unlam.registrarProfesor(pablito);

		// Verificacion
		assertNotNull(unlam);
		assertEquals(pablito, unlam.buscarProfePorDni(dni));
	}

	@Test
	public void queSePuedaRegistrarMateriaYBuscar() {
		// Preparacion
		String nombre = "UNLAM", nombreMateria = "PB1";
		Integer codigo = 1234;

		// Ejecucion
		Universidad unlam = new Universidad(nombre);
		Materia pb1 = new Materia(codigo, nombreMateria);
		unlam.registrarMateria(pb1);

		// Verificacion
		assertNotNull(unlam);
		assertEquals(pb1, unlam.buscarMateriaPorCodigo(codigo));
	}

	@Test
	public void queSePuedaRegistrarMateriaConCorrelativasYBuscar() {
		// Preparacion
		String nombre = "UNLAM", nombreMateria = "PB1", nombreCorrelativa = "PB2";
		Integer codigo = 1234, codCorrelativa = 4321;

		// Ejecucion
		Universidad unlam = new Universidad(nombre);
		Materia pb1 = new Materia(codigo, nombreMateria);
		Materia pb2 = new Materia(codCorrelativa, nombreCorrelativa);
		unlam.registrarMateria(pb1);
		unlam.agregarCorrelativa(pb1, pb2);

		// Verificacion
		assertNotNull(unlam);
		assertEquals(pb1, unlam.buscarMateriaPorCodigo(codigo));
		assertEquals(pb2, unlam.buscarCorrelativa(pb1, pb2));
	}

	@Test
	public void queSePuedaAgregarUnAula() {
		// Preparacion
		String nombre = "UNLAM";
		Integer numAula = 602;

		// Ejecucion
		Universidad unlam = new Universidad(nombre);
		Aula aula = new Aula(numAula);
		unlam.agregarAulaLibre(aula);

		// Verificacion
		assertNotNull(unlam);
		assertEquals(aula, unlam.buscarAulaPorNumero(aula.getNumero()));
	}

	@Test
	public void queSePuedaAsignarUnCurso() {
		// Preparacion
		String nombre = "UNLAM";
		Integer horario = 1200, horasSemanales = 8, codigoCurso = 1524;
		Periodos periodo = Periodos.SEGUNDO_CUATRIMESTRE;
		Materia pb1 = new Materia(1223, "Ingles");
		Aula aula = new Aula(602);
		Profesor pablito = new Profesor("Pablo", 46364646);

		// Ejecucion
		Universidad unlam = new Universidad(nombre);
		unlam.registrarMateria(pb1);
		unlam.registrarProfesor(pablito);
		Curso curso = unlam.asignarCurso(codigoCurso, pb1.getCodigo(), horario, horasSemanales, periodo, aula,
				pablito.getDni());

		// Verificacion
		assertNotNull(unlam);
		assertEquals(pablito, unlam.buscarProfePorDni(pablito.getDni()));
		assertEquals(pb1, unlam.buscarMateriaPorCodigo(pb1.getCodigo()));
		assertEquals(curso, unlam.buscarCurso(codigoCurso));
	}

	@Test
	public void queSePuedaRegistrarUnAlumno() {
		// Preparacion
		String nombre = "UNLAM", nombreAlumno = "Pablo", apellido = "Gutierrez";
		Integer dni = 46265819;

		// Ejecucion
		Universidad unlam = new Universidad(nombre);
		Alumno alumno = new Alumno(dni, nombreAlumno, apellido);
		unlam.registrar(alumno);

		// Verificacion
		assertNotNull(unlam);
		assertEquals(alumno, unlam.buscarAlumnoPorDni(dni));
	}

	@Test
	public void queSePuedaInscribirAlumnoACurso() {
		// Preparacion
		String nombre = "UNLAM";
		Integer codigoCurso = 2637, horario = 1200, horasSemanales = 8;
		Periodos periodo = Periodos.SEGUNDO_CUATRIMESTRE;
		Materia pb1 = new Materia(1223, "pb1");
		Aula aula = new Aula(602);
		Profesor pablito = new Profesor("Pablo", 46364646);
		Alumno alumno = new Alumno(46265819, "Maria", "Carmen");

		// Ejecucion
		Universidad unlam = new Universidad(nombre);
		unlam.registrar(alumno);
		unlam.registrarMateria(pb1);
		unlam.registrarProfesor(pablito);
		Curso curso = unlam.asignarCurso(codigoCurso, pb1.getCodigo(), horario, horasSemanales, periodo, aula,
				pablito.getDni());

		// Verificacion
		assertNotNull(unlam);
		assertEquals(pablito, unlam.buscarProfePorDni(pablito.getDni()));
		assertEquals(pb1, unlam.buscarMateriaPorCodigo(pb1.getCodigo()));
		assertEquals(curso, unlam.buscarCurso(curso.getCodigoCurso()));
		assertTrue(unlam.inscribirAlumnoAUnCurso(alumno.getDni(), codigoCurso));
		assertEquals(alumno, curso.buscarAlumnoPorDni(alumno.getDni()));
	}

	@Test
	public void queNoSePuedaInscribirAlumnoACursoPorCorrelativas() {
		// Preparacion
		String nombre = "UNLAM";
		Integer codigoCurso = 5557, horario = 1200, horasSemanales = 4;
		Materia matematicas = new Materia(1771, "Matematicas");
		Materia matematicas2 = new Materia(1772, "Matematicas 2");
		Periodos periodo1 = Periodos.PRIMER_CUATRIMESTRE;
		Periodos periodo2 = Periodos.SEGUNDO_CUATRIMESTRE;
		Aula aula = new Aula(45);
		Aula aula2 = new Aula(46);
		Alumno facundo = new Alumno(46364928, "Facundo", "Mamani Quispe");
		Integer parcial1 = 1, parcial2 = 2;
		Profesor profeMati = new Profesor("Matias", 46364928);
		Profesor profePablo = new Profesor("Pablo", 46265819);

		// Ejecucion
		Universidad unlam = new Universidad(nombre);
		unlam.registrar(facundo);
		unlam.registrarMateria(matematicas);
		unlam.registrarProfesor(profeMati);
		unlam.registrarMateria(matematicas2);
		unlam.registrarProfesor(profePablo);
		Curso curso = new Curso(codigoCurso, horario, horasSemanales, matematicas, periodo1, aula);
		unlam.agregarCurso(curso);
		unlam.asignarCurso(codigoCurso, matematicas.getCodigo(), horario, horasSemanales, periodo1, aula, profeMati.getDni());
		unlam.inscribirAlumnoAUnCurso(facundo.getDni(), codigoCurso);
		curso.obtenerResultadosPruebasDeAlumno(facundo.getDni(), parcial1, parcial2);
		Curso curso2 = new Curso(1234, 1200, 4, matematicas2, periodo2, aula2);
		unlam.agregarCurso(curso2);
		unlam.asignarCurso(curso2.getCodigoCurso(), matematicas2.getCodigo(), horario, horasSemanales, periodo2, aula2, profePablo.getDni());
		unlam.agregarCorrelativa(matematicas2, matematicas);
		
		// Validacion
		assertNotNull(curso);
		assertEquals(facundo, unlam.buscarAlumnoPorDni(facundo.getDni()));
		assertTrue(curso.existeAlumno(facundo.getDni()));
		assertEquals(matematicas, unlam.buscarMateriaPorCodigo(matematicas.getCodigo()));
		assertEquals(matematicas, curso.getMateria());
		assertEquals(Calificacion.RECURSA, curso.verEstadoDelAlumno(facundo.getDni()));
		assertFalse(unlam.inscribirAlumnoAUnCurso(facundo.getDni(), curso2.getCodigoCurso()));
	}

}
