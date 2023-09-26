package ar.unlam.intraconsulta;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestCursada {

	@Test
	public void queSePuedaCrearLaCursada() {
		//Preparacion
		Materia matematicas = new Materia(1177, "Matematicas");
		Alumno gisel = new Alumno(46364928, "Gisel", "Suarez");
		
		//Ejecucion
		Cursada cursada = new Cursada(matematicas, gisel);
		
		//Verificacion
		assertNotNull(cursada);
	}
	
	@Test
	public void queSePromocione() {
		//Preparacion
		Materia matematicas = new Materia(1177, "Matematicas");
		Alumno gisel = new Alumno(46364928, "Gisel", "Suarez");
		Integer parcial1 = 7;
		Integer parcial2 = 8;
		
		//Ejecucion
		Cursada cursada = new Cursada(matematicas, gisel);
		cursada.hacerParciales(parcial1, parcial2);
		cursada.evaluarParciales();
		
		//Verificacion
		assertNotNull(cursada);
		assertEquals(Calificacion.PROMOCIONA, cursada.getNotaFinal());
	}
	
	@Test
	public void queSeApruebe() {
		//Preparacion
		Materia matematicas = new Materia(1177, "Matematicas");
		Alumno gisel = new Alumno(46364928, "Gisel", "Suarez");
		Integer parcial1 = 5;
		Integer parcial2 = 8;
		
		//Ejecucion
		Cursada cursada = new Cursada(matematicas, gisel);
		cursada.hacerParciales(parcial1, parcial2);
		cursada.evaluarParciales();
		
		//Verificacion
		assertNotNull(cursada);
		assertEquals(Calificacion.APROBADO, cursada.getNotaFinal());
	}
	
	@Test
	public void queSeDesapruebe() {
		//Preparacion
		Materia matematicas = new Materia(1177, "Matematicas");
		Alumno gisel = new Alumno(46364928, "Gisel", "Suarez");
		Integer parcial1 = 5;
		Integer parcial2 = 3;
		
		//Ejecucion
		Cursada cursada = new Cursada(matematicas, gisel);
		cursada.hacerParciales(parcial1, parcial2);
		cursada.evaluarParciales();
		
		//Verificacion
		assertNotNull(cursada);
		assertEquals(Calificacion.DESAPROBADO, cursada.getNotaFinal());
	}
	
	@Test
	public void queHayaRecuperatorioDisponible() {
		//Preparacion
		Materia matematicas = new Materia(1177, "Matematicas");
		Alumno gisel = new Alumno(46364928, "Gisel", "Suarez");
		Integer parcial1 = 5;
		Integer parcial2 = 8;
		
		//Ejecucion
		Cursada cursada = new Cursada(matematicas, gisel);
		cursada.hacerParciales(parcial1, parcial2);
		cursada.evaluarParciales();
		
		//Verificacion
		assertNotNull(cursada);
		assertEquals(Calificacion.APROBADO, cursada.getNotaFinal());
		assertTrue(cursada.recuperatorioDisponible());
	}
	
	@Test
	public void queSePuedaPromocionarConRecuperatorio() {
		//Preparacion
		Materia matematicas = new Materia(1177, "Matematicas");
		Alumno gisel = new Alumno(46364928, "Gisel", "Suarez");
		Integer parcial1 = 5;
		Integer parcial2 = 8;
		Integer Recuperatorio = 10;
		
		//Ejecucion
		Cursada cursada = new Cursada(matematicas, gisel);
		cursada.hacerParciales(parcial1, parcial2);
		cursada.evaluarParciales();
		cursada.obtenerResultadoDeRecuperatorio(Recuperatorio);
		
		//Verificacion
		assertNotNull(cursada);
		assertEquals(Calificacion.APROBADO, cursada.getNotaFinal());
		assertEquals(Calificacion.PROMOCIONA, cursada.getRecuperatorio());
	}
	
	@Test
	public void queHayaFinalDisponible() {
		//Preparacion
		Materia matematicas = new Materia(1177, "Matematicas");
		Alumno gisel = new Alumno(46364928, "Gisel", "Suarez");
		Integer parcial1 = 5;
		Integer parcial2 = 5;
		
		//Ejecucion
		Cursada cursada = new Cursada(matematicas, gisel);
		cursada.hacerParciales(parcial1, parcial2);
		cursada.evaluarParciales();
		
		//Verificacion
		assertNotNull(cursada);
		assertEquals(Calificacion.APROBADO, cursada.getNotaFinal());
		assertTrue(cursada.finalDisponible());
	}
	
	@Test
	public void queSePuedaRecursar() {
		//Preparacion
		Materia matematicas = new Materia(1177, "Matematicas");
		Alumno gisel = new Alumno(46364928, "Gisel", "Suarez");
		Integer parcial1 = 3;
		Integer parcial2 = 2;
		
		//Ejecucion
		Cursada cursada = new Cursada(matematicas, gisel);
		cursada.hacerParciales(parcial1, parcial2);
		cursada.evaluarParciales();
		
		//Verificacion
		assertNotNull(cursada);
		assertEquals(Calificacion.RECURSA, cursada.getNotaFinal());
		assertFalse(cursada.finalDisponible());
	}
	
	@Test
	public void queSePuedaRecursarConRecuperatorio() {
		//Preparacion
		Materia matematicas = new Materia(1177, "Matematicas");
		Alumno gisel = new Alumno(46364928, "Gisel", "Suarez");
		Integer parcial1 = 3;
		Integer parcial2 = 4;
		Integer recuperatorio = 3;
		
		//Ejecucion
		Cursada cursada = new Cursada(matematicas, gisel);
		cursada.hacerParciales(parcial1, parcial2);
		cursada.evaluarParciales();
		cursada.obtenerResultadoDeRecuperatorio(recuperatorio);
		
		//Verificacion
		assertNotNull(cursada);
		assertEquals(Calificacion.DESAPROBADO, cursada.getNotaFinal());
		assertEquals(Calificacion.RECURSA, cursada.getRecuperatorio());
		assertFalse(cursada.finalDisponible());
	}
	
	@Test
	public void queSePuedaRecursarConFinal() {
		//Preparacion
		Materia matematicas = new Materia(1177, "Matematicas");
		Alumno gisel = new Alumno(46364928, "Gisel", "Suarez");
		Integer parcial1 = 3;
		Integer parcial2 = 6;
		Integer recuperatorio = 10;
		Integer notaDeFinal = 1;
		
		//Ejecucion
		Cursada cursada = new Cursada(matematicas, gisel);
		cursada.hacerParciales(parcial1, parcial2);
		cursada.evaluarParciales();
		cursada.obtenerResultadoDeRecuperatorio(recuperatorio);
		cursada.evaluarFinal(notaDeFinal);
		
		//Verificacion
		assertNotNull(cursada);
		assertEquals(Calificacion.DESAPROBADO, cursada.getNotaFinal());
		assertEquals(Calificacion.APROBADO, cursada.getRecuperatorio());
		assertTrue(cursada.finalDisponible());
		assertEquals(Calificacion.RECURSA, cursada.getCalificacionDefinal());
	}

}
