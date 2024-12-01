package models;

import java.io.Serializable;

public class Paciente implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int id;
    private String nombre;
    private String apellido;
    private int edad;
    private String tipoSangre;
    private String genero;
    private String cedula;
    private Vivienda vivienda;

    public Paciente(int id, String cedula, String nombre, String apellido, int edad, String tipoSangre, String genero, Vivienda vivienda) {
    	this.id = id;
    	this.nombre = nombre;
    	this.apellido = apellido;
        this.edad = edad;
        this.tipoSangre = tipoSangre;
        this.genero = genero;
        this.cedula = cedula;
        this.vivienda = vivienda;
    }
    
    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public String getTipoSangre() {
		return tipoSangre;
	}

	public void setTipoSangre(String tipoSangre) {
		this.tipoSangre = tipoSangre;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public Vivienda getVivienda() {
		return vivienda;
	}

	public void setVivienda(Vivienda vivienda) {
		this.vivienda = vivienda;
	}

	@Override
    public String toString() {
        return nombre + " (Cedula: " + cedula + ")";
    }
}