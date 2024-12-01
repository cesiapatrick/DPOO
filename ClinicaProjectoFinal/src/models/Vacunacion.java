package models;

import java.io.Serializable;

public class Vacunacion implements Serializable {
	private static final long serialVersionUID = 1L;
	
    private String nombreVacuna;
    private boolean completada;
    private Paciente paciente;

    public Vacunacion(String nombreVacuna, boolean completada, Paciente paciente) {
        this.nombreVacuna = nombreVacuna;
        this.completada = completada;
        this.paciente = paciente;
    }

	public String getVacuna() {
		return nombreVacuna;
	}

	public void setVacuna(String vacuna) {
		this.nombreVacuna = vacuna;
	}

	public boolean isCompletada() {
		return completada;
	}

	public void setCompletada(boolean completada) {
		this.completada = completada;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}
}