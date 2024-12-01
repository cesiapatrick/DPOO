package models;

import java.io.Serializable;
import java.util.ArrayList;

public class HistoriaClinica implements Serializable {
	private static final long serialVersionUID = 1L;
	
    private Paciente paciente;
    private ArrayList<ConsultaMedica> consultas;

    public HistoriaClinica(Paciente paciente) {
        this.setPaciente(paciente);
        this.consultas = new ArrayList<>();
    }

    public void agregarConsulta(ConsultaMedica consulta) {
        consultas.add(consulta);
    }

    public String generarResumen() {
        StringBuilder resumen = new StringBuilder();
        resumen.append("Resumen de Historia Clínica:\n");
        for (ConsultaMedica consulta : consultas) {
            resumen.append(consulta.getFecha()).append(" - Diagnóstico: ").append(consulta.getDiagnostico()).append("\n");
        }
        return resumen.toString();
    }

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}
}