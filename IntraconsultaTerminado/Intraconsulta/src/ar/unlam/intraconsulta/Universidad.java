package ar.unlam.intraconsulta;

import java.util.ArrayList;
import java.util.Iterator;

public class Universidad {
	private String nombre;
	private Integer id;
	private static Integer contador = 0;
	private ArrayList<Alumno> alumnos;
	private ArrayList<Profesor> profesores;
	private ArrayList<Materia> materias;
	private ArrayList<Aula> aulas;
	private ArrayList<Curso> cursos;
	
	public Universidad(String nombre) {
		this.nombre = nombre;
		this.alumnos = new ArrayList<Alumno>();
		this.profesores = new ArrayList<Profesor>();
		this.materias = new ArrayList<Materia>();
		this.aulas = new ArrayList<Aula>();
		this.cursos = new ArrayList<Curso>();
		this.id = this.generarSiguienteID();
	}

	public Boolean registrarProfesor(Profesor profe) {
		return this.profesores.add(profe);
	}
	
	public Boolean registrarMateria(Materia materia) {
		return this.materias.add(materia);
	}
	
	public void agregarCorrelativa(Materia materia, Materia correlativa) {
		for(int i = 0; i < this.materias.size(); i++) {
			if(this.materias.get(i).getCodigo().equals(materia.getCodigo())) {
				this.materias.get(i).agregarCorrelativa(correlativa);
			}
		}
	}
	
	public boolean agregarCurso(Curso curso) {
		return this.cursos.add(curso);
	}

	public Curso asignarCurso(Integer codigoCurso, Integer codigoMateria, Integer horario, Integer horasSemanales, Periodos periodoAcademico, Aula aula,
			Integer dniProfe) {
		Materia materia1 = this.buscarMateriaPorCodigo(codigoMateria);
		Profesor profe = this.buscarProfePorDni(dniProfe);
		if(this.buscarAulaPorNumero(aula.getNumero()) == null) {
			this.agregarAulaLibre(aula);
			aula = this.buscarAulaPorNumero(aula.getNumero());
		} else {
			aula = this.buscarAulaPorNumero(aula.getNumero());
		}
		if (materia1 == null || profe == null) {
			return null;
		}
		Curso curso = new Curso(codigoCurso, horario, horasSemanales, materia1, periodoAcademico, aula);
		this.agregarCurso(curso);
		curso.agregarProfe(profe);
		aula.asignarCurso(curso);
		return curso;
	}
	
	public Boolean registrar(Alumno alumno) {
		if (buscarAlumnoPorDni(alumno.getDni()) == null) {
			return this.alumnos.add(alumno);
		} else {
			return false;
		}
	}

	public boolean inscribirAlumnoAUnCurso(Integer dni, Integer codigo) {
		boolean inscripto = false;
		Alumno alumno = this.buscarAlumnoPorDni(dni);
		Materia materia = this.buscarCurso(codigo).getMateria();
		if (alumno == null || materia == null) {
			return false;
		}
		ArrayList<Materia> correlativas = materia.getCorrelativas();
		if(correlativas.size() != 0) {
			for (int i = 0; i < correlativas.size(); i++) {
				Materia materiaCorrelativa = correlativas.get(i);
				if (!estaAprobado(dni, materiaCorrelativa.getCodigo()))
					return inscripto;
			}
		}
		this.buscarCurso(codigo).agregarAlumno(alumno);
		return inscripto = true;
	}

	public Boolean existeAlumno(Integer dni) {
		for (int i = 0; i < alumnos.size(); i++) {
			if (this.alumnos.get(i).getDni().equals(dni))
				return true;
		}
		return false;
	}

	public boolean estaAprobado(Integer dni, Integer codigo) {
		Curso curso = this.buscarCursoPorCodigoMateria(codigo);
		if (curso.verEstadoDelAlumno(dni) == Calificacion.APROBADO || curso.verEstadoDelAlumno(dni) == Calificacion.PROMOCIONA) {
			return true;
		}
		return false;
	}

	public Alumno buscarAlumnoPorDni(Integer dni) {
		for (int i = 0; i < alumnos.size(); i++) {
			if (this.alumnos.get(i).getDni().equals(dni))
				return this.alumnos.get(i);
		}
		return null;
	}

	public Profesor buscarProfePorDni(Integer dni) {
		for (int i = 0; i < this.profesores.size(); i++) {
			if (this.profesores.get(i).getDni().equals(dni)) {
				return this.profesores.get(i);
			}
		}
		return null;
	}

	public Materia buscarMateriaPorCodigo(Integer codigo) {
		for (int i = 0; i < this.materias.size(); i++) {
			if (this.materias.get(i).getCodigo().equals(codigo))
				return this.materias.get(i);
		}
		return null;
	}
	
	public Materia buscarCorrelativa(Materia materia, Materia correlativa) {
		for(int i = 0; i < this.materias.size(); i++) {
			if(this.materias.get(i).getCodigo().equals(materia.getCodigo())) {
				return this.materias.get(i).buscarCorrelativa(correlativa);
			}
		}
		return null;
	}
	
	public Curso buscarCursoPorDni(Integer dni) {
		for(int i = 0; i < this.cursos.size(); i++) {
			if(this.cursos.get(i).buscarAlumnoPorDni(dni) != null) {
				return this.cursos.get(i);
			}
		}
		return null;
	}
	
	public Curso buscarCursoPorCodigoMateria(Integer codigo) {
		for(int i = 0; i < this.materias.size(); i++) {
			if(this.cursos.get(i).getMateria().getCodigo().equals(codigo)) {
				return this.cursos.get(i);
			}
		}
		return null;
	}

	public Curso buscarCurso(Integer codigo) {
		for (int i = 0; i < this.cursos.size(); i++) {
			if (this.cursos.get(i).getCodigoCurso().equals(codigo))
				return this.cursos.get(i);
		}
		return null;
	}

	public boolean agregarAulaLibre(Aula aula) {
		for (Integer i = 0; i < this.aulas.size(); i++) {
			if (this.aulas.get(i).getNumero() == aula.getNumero()) {
				return false;
			}
		}
		return this.aulas.add(aula);
	}
	
	public Aula buscarAulaPorNumero(Integer numero) {
		for(int i = 0; i < this.aulas.size(); i++) {
			if(this.aulas.get(i).getNumero().equals(numero)) {
				return this.aulas.get(i);
			}
		}
		return null;
	}

	public Integer generarSiguienteID() {
		return this.contador++;
	}
}
