package ar.unlam.intraconsulta;

public class Alumno {
	private Integer dni;
	private String apellido;
	private String nombre;
	private Integer id;
	private static Integer contador = 0;
	
	public Alumno(Integer dni, String nombre, String apellido) {
	this.dni=dni;
	this.apellido=apellido;
	this.nombre=nombre;
	this.id = this.generarSiguienteID();
	}

	public Integer getDni() {
		return dni;
	}

	public void setDni(Integer dni) {
		this.dni = dni;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public static Integer getContador() {
		return contador;
	}

	public static void setContador(Integer contador) {
		Alumno.contador = contador;
	}

	public Integer generarSiguienteID() {
		return this.contador++;
	}
	
}
