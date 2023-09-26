package ar.unlam.intraconsulta;

public class Cursada {
	private Materia materia;
	private Alumno alumno;
	private Notas parciales;
	private Calificacion notaFinal;
	private Calificacion recuperatorio;
	private Calificacion calificacionDefinal;
	private Integer id;
	private static Integer contador = 0;

	public Cursada(Materia materia, Alumno alumno) {
		this.materia = materia;
		this.alumno = alumno;
		this.parciales = null;
		this.recuperatorio = null;
		this.id = this.generarSiguienteID();
	}

	public void hacerParciales(Integer nota1, Integer nota2) {
		this.parciales = new Notas(nota1, nota2);
	}

	public Calificacion evaluarParciales() {
		if (this.parciales.getParcial1() >= 7 && this.parciales.getParcial2() >= 7) {
			notaFinal = Calificacion.PROMOCIONA;
		} else if ((this.parciales.getParcial1() >= 4 && this.parciales.getParcial2() >= 4) && (this.parciales.getParcial1() < 7 || this.parciales.getParcial2() < 7)) {
			notaFinal = Calificacion.APROBADO;
		} else if (this.parciales.getParcial1() < 4 && this.parciales.getParcial2() < 4) {
			notaFinal = Calificacion.RECURSA;
		} else if (this.parciales.getParcial1() < 4 || this.parciales.getParcial2() < 4) {
			notaFinal = Calificacion.DESAPROBADO;
		}
		return notaFinal;
	}
	
	public Calificacion evaluarRecuperatorio() {
		if (this.parciales.getParcial1() >= 7 && this.parciales.getParcial2() >= 7) {
			recuperatorio = Calificacion.PROMOCIONA;
		} else if ((this.parciales.getParcial1() >= 4 && this.parciales.getParcial2() >= 4) && (this.parciales.getParcial1() < 7 || this.parciales.getParcial2() < 7)) {
			recuperatorio = Calificacion.FINAL;
		} else if (this.parciales.getParcial1() < 4 || this.parciales.getParcial2() < 4) {
			recuperatorio = Calificacion.RECURSA;
		}
		return recuperatorio;
	}
	
	public Calificacion obtenerResultadoDeRecuperatorio(Integer notaFinal) {
		Integer parcialDesaprobado = this.buscarRecuperatorio();
		this.hacerRecuperatorio(parcialDesaprobado, notaFinal);
		if (this.evaluarRecuperatorio() == Calificacion.FINAL) {
			this.setRecuperatorio(Calificacion.FINAL);
		} else if (this.evaluarRecuperatorio() == Calificacion.RECURSA) {
			this.setRecuperatorio(Calificacion.RECURSA);
		} else if (this.evaluarRecuperatorio() == Calificacion.PROMOCIONA) {
			this.setRecuperatorio(Calificacion.PROMOCIONA);
		}
		return getRecuperatorio();
	}
	
	public Integer buscarRecuperatorio() {
		Integer parcialBuscado = 0;
		if (this.getNotaFinal() == Calificacion.APROBADO) {
			if(parciales.getParcial1() < 7 && parciales.getParcial2() >= 7) {
				parcialBuscado = 1;
			} else if (parciales.getParcial2() < 7 && parciales.getParcial1() >= 7) {
				parcialBuscado = 2;
			}}
		if (this.getNotaFinal() == Calificacion.DESAPROBADO) {
			if (parciales.getParcial1() < 4 && parciales.getParcial2() >= 4) {
				parcialBuscado = 1;
			} else if (parciales.getParcial2() < 4 && parciales.getParcial1() >= 4) {
				parcialBuscado = 2;
			}}
		return parcialBuscado;
	}
	
	public void hacerRecuperatorio(Integer parcialARecuperar, Integer notaDelRecuperatorio) {
		this.parciales.usarParcial(parcialARecuperar, notaDelRecuperatorio);
	}

	public boolean recuperatorioDisponible() {
		if (this.buscarRecuperatorio() == 1 || this.buscarRecuperatorio() == 2) {
			return true;
		}
		return false;
	}

	public Calificacion hacerFinal(Integer nota) {
		if (this.finalDisponible()) {
			this.evaluarFinal(nota);
			return this.getCalificacionDefinal();
		}
		return null;
	}
	
	public void evaluarFinal(Integer nota) {
		if(nota >= 4) {
			this.calificacionDefinal = Calificacion.APROBADO;
		} else if (nota <= 4) {
			this.calificacionDefinal = Calificacion.RECURSA;
		}
	}
	
	public boolean finalDisponible() {
		if (this.recuperatorio == Calificacion.APROBADO || this.getNotaFinal() == Calificacion.APROBADO) {
			return true;
		}
		return false;
	}
	
	public Calificacion getNotaFinal() {
		return notaFinal;
	}
	
	public Materia getMateria() {
		return materia;
	}

	public void setMateria(Materia materia) {
		this.materia = materia;
	}

	public Alumno getAlumno() {
		return alumno;
	}

	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}
	
	public Notas getParciales() {
		return parciales;
	}
	
	public void setParciales(Notas parciales) {
		this.parciales = parciales;
	}
	
	public void setNotaFinal(Calificacion notaFinal) {
		this.notaFinal = notaFinal;
	}

	public Calificacion getRecuperatorio() {
		return recuperatorio;
	}

	public void setRecuperatorio(Calificacion recuperatorio) {
		this.recuperatorio = recuperatorio;
	}

	public Calificacion getCalificacionDefinal() {
		return calificacionDefinal;
	}

	public void setCalificacionDefinal(Calificacion calificacionDefinal) {
		this.calificacionDefinal = calificacionDefinal;
	}
	
	public Integer generarSiguienteID() {
		return this.contador++;
	}
}
