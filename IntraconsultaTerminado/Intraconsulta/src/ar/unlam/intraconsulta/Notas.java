package ar.unlam.intraconsulta;

public class Notas {
	private Integer parcial1 ;
	private Integer parcial2;
	private Integer recuperatorio;
	private Integer id;
	private static Integer contador = 0;

	public Notas(Integer parcial1, Integer parcial2) {
		this.parcial1 = parcial1;
		this.parcial2 = parcial2;
		this.recuperatorio = null;
		this.id = this.generarSiguienteID();
	}

	public void usarParcial(Integer numeroParcial, Integer notaNueva) {
		if(numeroParcial == 1) {
			this.setParcial1(notaNueva);
		} else if (numeroParcial == 2) {
			this.setParcial2(notaNueva);;
		}
	}

	public Integer getParcial1() {
		return parcial1;
	}

	public void setParcial1(Integer parcial1) {
		this.parcial1 = parcial1;
	}

	public Integer getParcial2() {
		return parcial2;
	}

	public void setParcial2(Integer parcial2) {
		this.parcial2 = parcial2;
	}

	public Integer getRecuperatorio() {
		return recuperatorio;
	}

	public void setRecuperatorio(Integer recuperatorio) {
		this.recuperatorio = recuperatorio;
	}
	
	public Integer generarSiguienteID() {
		return this.contador++;
	}
}
