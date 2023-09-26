package ar.unlam.intraconsulta;

public class CicloLectivo {
	private Periodos periodoAcademico;
	private Integer fechaInicio;
	private Integer finalizacion;
	private Integer fechasInscripciones;
	private Integer id;
	private static Integer contador = 0;
	
	public CicloLectivo(Periodos periodoAcademico, Integer fechaInicio, Integer finalizacion,
			Integer fechasInscripciones) {
		this.periodoAcademico = periodoAcademico;
		this.fechaInicio = fechaInicio;
		this.finalizacion = finalizacion;
		this.fechasInscripciones = fechasInscripciones;
		this.id = this.generarSiguienteID();
	}

	public Periodos getPeriodoAcademico() {
		return periodoAcademico;
	}

	public void setPeriodoAcademico(Periodos periodoAcademico) {
		this.periodoAcademico = periodoAcademico;
	}

	public Integer getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Integer fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Integer getFinalizacion() {
		return finalizacion;
	}

	public void setFinalizacion(Integer finalizacion) {
		this.finalizacion = finalizacion;
	}

	public Integer getFechasInscripciones() {
		return fechasInscripciones;
	}

	public void setFechasInscripciones(Integer fechasInscripciones) {
		this.fechasInscripciones = fechasInscripciones;
	}
	
	public Integer generarSiguienteID() {
		return this.contador++;
	}
	
}
