package ar.unlam.intraconsulta;
import java.util.ArrayList;

public class Curso {
	private Materia materia;
	private Integer codigoCurso;
	private Integer horario;
	private Integer horasSemanales;
	private CicloLectivo cicloLectivo;
	private Aula aula;
	private Integer id;
	private static Integer contador = 0;
	private ArrayList<Profesor> profes;
	private ArrayList<Alumno> alumnos;
	private ArrayList<Cursada> cursadas;

	public Curso(Integer codigoCurso, Integer horario, Integer horasSemanales, Materia materia, Periodos periodoAcademico, Aula aula) {
		this.codigoCurso = codigoCurso;
		this.materia = materia;
		this.horario = horario;
		this.horasSemanales = horasSemanales;
		this.cicloLectivo = new CicloLectivo(periodoAcademico, null, null, null);
		this.aula = aula;
		this.profes = new ArrayList<Profesor>();
		this.alumnos = new ArrayList<Alumno>();
		this.cursadas = new ArrayList<Cursada>();
		this.id = this.generarSiguienteID();
	}
	
	public boolean hayEspacio() {
		if(this.alumnos.size() < this.aula.getCapacidad()) {
			return true;
		}
		return false;
	}
	
	public void asignarInicioYFinDePeriodo(Integer inicio, Integer fechaFinal) {
		this.cicloLectivo.setFechaInicio(inicio);
		this.cicloLectivo.setFinalizacion(fechaFinal);;
	}
	
	public void asignarFechaInscripciones(Integer fecha) {
		this.cicloLectivo.setFechasInscripciones(fecha);
	}
	
	public boolean agregarProfe(Profesor profe) {
		if(!this.existeProfe(profe.getDni())) {
			return this.profes.add(profe);
		}
		return false;
	}
	
	public boolean existeProfe(Integer dni) {
		for (int i = 0; i < profes.size(); i++) {
			if (this.profes.get(i).getDni().equals(dni))
				return true;
		}
		return false;
	}
	
	public boolean agregarAlumno(Alumno alumno) {
		if(!this.existeAlumno(alumno.getDni()) && this.hayEspacio()) {
			this.alumnos.add(alumno);
			return this.cursadas.add(this.crearCursada(alumno.getDni()));
		}
		return false;
	}
	
	public boolean existeAlumno(Integer dni) {
		for (int i = 0; i < alumnos.size(); i++) {
			if (this.alumnos.get(i).getDni().equals(dni))
				return true;
		}
		return false;
	}

	public Cursada crearCursada(Integer dni) {
		Cursada cursada = new Cursada(this.getMateria(), this.buscarAlumnoPorDni(dni));
		return cursada;
	}
	
	public boolean existeCursada(Integer dni) {
		for (int i = 0; i < cursadas.size(); i++) {
			if (this.cursadas.get(i).getAlumno().getDni().equals(dni))
				return true;
		}
		return false;
	}
	
	public Calificacion obtenerResultadosPruebasDeAlumno(Integer dniAlumno, Integer parcial1, Integer parcial2) {
		Cursada cursadaDelAlumno = this.buscarCursada(dniAlumno);
		cursadaDelAlumno.hacerParciales(parcial1, parcial2);
		return cursadaDelAlumno.evaluarParciales();
	}
	
	public Calificacion obtenerResultadosRecupAlumno(Integer dniAlumno, Integer notaNueva) {
		Cursada cursada = this.buscarCursada(dniAlumno);
		return cursada.obtenerResultadoDeRecuperatorio(notaNueva);
	}
	
	public Calificacion obtenerResultadosFinalDeAlumno(Integer dniAlumno, Integer nota) {
		Cursada cursada = this.buscarCursada(dniAlumno);
		return cursada.hacerFinal(nota);
	}
	
	public String verNotasDeAlumno(Integer dni) {
		Cursada cursada = this.buscarCursada(dni);
		String notasDelAlumno = "" + cursada.getParciales().getParcial1() + cursada.getParciales().getParcial2() + 
				"\nRecuperatorio: " + cursada.getParciales().getRecuperatorio();
		return notasDelAlumno;
	}
	
	public Calificacion verEstadoDelAlumno(Integer dni) {
        Cursada cursada = this.buscarCursada(dni);
        Calificacion calificacion = null;
        if (cursada.getRecuperatorio() != null) {
	         if (cursada.getRecuperatorio() == Calificacion.RECURSA || cursada.getRecuperatorio() == Calificacion.PROMOCIONA) {
	        	return cursada.getRecuperatorio();
	        } else if (cursada.getRecuperatorio() == Calificacion.FINAL) {
	            calificacion = cursada.getCalificacionDefinal();
	        	return calificacion;
	        }
        } return cursada.getNotaFinal();
    }
	
	public Cursada buscarCursada(Integer dni) {
		for(int i = 0; i < cursadas.size(); i++) {
			if(this.buscarAlumnoPorDni(dni) == cursadas.get(i).getAlumno()) {
				return cursadas.get(i);
			}
		} return null;
	}
	
	public Profesor buscarProfePorDni(Integer dni) {
		for (int i = 0; i < this.profes.size(); i++) {
			if (this.profes.get(i).getDni().equals(dni)) {
				return this.profes.get(i);
			}
		}
		return null;
	}
	
	public Alumno buscarAlumnoPorDni(Integer dni) {
        for (int i = 0; i < alumnos.size(); i++) {
            if (this.alumnos.get(i).getDni().equals(dni))
                return this.alumnos.get(i);
        }
        return null;
    }
	
	public Integer getCodigoCurso() {
		return codigoCurso;
	}

	public void setCodigoCurso(Integer codigoCurso) {
		this.codigoCurso = codigoCurso;
	}

	public Integer getHorario() {
		return horario;
	}

	public void setHorario(Integer horario) {
		this.horario = horario;
	}

	public CicloLectivo getCicloLectivo() {
		return cicloLectivo;
	}

	public void setCicloLectivo(CicloLectivo cicloLectivo) {
		this.cicloLectivo = cicloLectivo;
	}

	public Aula getAula() {
		return aula;
	}

	public void setAula(Aula aula) {
		this.aula = aula;
	}

	public Materia getMateria() {
		return materia;
	}
	
	public void setMateria(Materia materia) {
		this.materia = materia;
	}

	public Integer getHorasSemanales() {
		return horasSemanales;
	}

	public void setHorasSemanales(Integer horasSemanales) {
		this.horasSemanales = horasSemanales;
	}

	public ArrayList<Profesor> getProfes() {
		return profes;
	}

	public void setProfes(ArrayList<Profesor> profes) {
		this.profes = profes;
	}

	public ArrayList<Alumno> getAlumnos() {
		return alumnos;
	}

	public void setAlumnos(ArrayList<Alumno> alumnos) {
		this.alumnos = alumnos;
	}

	public ArrayList<Cursada> getCursadas() {
		return cursadas;
	}

	public void setCursadas(ArrayList<Cursada> cursadas) {
		this.cursadas = cursadas;
	}

	public Integer getFechaInicial() {
		return this.cicloLectivo.getFechaInicio();
	}
	
	public Integer getFechaFinalizacion() {
		return this.cicloLectivo.getFinalizacion();
	}
	
	public Integer getFechaInscripciones() {
		return this.cicloLectivo.getFechasInscripciones();
	}
	
	public Integer generarSiguienteID() {
		return this.contador++;
	}
}