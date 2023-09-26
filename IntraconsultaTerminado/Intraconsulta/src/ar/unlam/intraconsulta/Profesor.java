package ar.unlam.intraconsulta;

public class Profesor {
	private String nombre;
	private Integer dni;
	private Integer id;
	private static Integer contador = 0;
	
	public Profesor(String nombre, Integer dni) {
		this.nombre = nombre;
		this.dni = dni;
		this.id = this.generarSiguienteID();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getDni() {
		return dni;
	}

	public void setDni(Integer dni) {
		this.dni = dni;
	}
	
	public Integer generarSiguienteID() {
		return this.contador++;
	}
	
}
