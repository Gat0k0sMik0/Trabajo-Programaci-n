package ar.unlam.intraconsulta;

import java.util.ArrayList;

public class Materia {
	private Integer codigo;
	private String nombre;
	private Integer id;
	private static Integer contador = 0;
	private ArrayList<Materia> correlativas;

	public Materia(Integer codigo, String nombre) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.id = this.generarSiguienteID();
		correlativas = new ArrayList<>();
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public ArrayList<Materia> getCorrelativas() {
		return correlativas;
	}

	public void setCorrelativas(ArrayList<Materia> correlativas) {
		this.correlativas = correlativas;
	}
	
	public boolean agregarCorrelativa(Materia correlativa) {
		if (correlativas.contains(correlativa)) {
			return false;
		} else {
			return correlativas.add(correlativa);
		}
	}
	
	public Materia buscarCorrelativa(Materia materia) {
		for(int i = 0; i < this.correlativas.size(); i++) {
			if(correlativas.get(i).equals(materia)) {
				return correlativas.get(i);
			}
		}
		return null;
	}
	
	public Integer generarSiguienteID() {
		return this.contador++;
	}

}
