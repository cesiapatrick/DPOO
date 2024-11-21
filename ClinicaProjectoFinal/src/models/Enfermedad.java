package models;

public class Enfermedad {
    private String nombre;
    private boolean enVigilancia;

    public Enfermedad(String nombre, boolean enVigilancia) {
        this.nombre = nombre;
        this.enVigilancia = enVigilancia;
    }

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public boolean isEnVigilancia() {
		return enVigilancia;
	}

	public void setEnVigilancia(boolean enVigilancia) {
		this.enVigilancia = enVigilancia;
	}
}