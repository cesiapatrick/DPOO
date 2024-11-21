package models;

public class Vacunacion {
    private String vacuna;
    private boolean completada;
    private Paciente paciente;

    public Vacunacion(String vacuna, boolean completada, Paciente paciente) {
        this.vacuna = vacuna;
        this.completada = completada;
        this.paciente = paciente;
    }

	public String getVacuna() {
		return vacuna;
	}

	public void setVacuna(String vacuna) {
		this.vacuna = vacuna;
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