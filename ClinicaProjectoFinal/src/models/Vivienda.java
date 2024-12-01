package models;

import java.io.Serializable;

public class Vivienda implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String tipo;
    private String direccion;
    private String ciudad;
    private String codigoPostal;

    public Vivienda(String direccion, String ciudad, String tipo, String codigoPostal) {
        this.direccion = direccion;
        this.ciudad = ciudad;
        this.tipo = tipo;
        this.codigoPostal = codigoPostal;
    }

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getCodigoPostal() {
		return codigoPostal;
	}

	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}
    
}