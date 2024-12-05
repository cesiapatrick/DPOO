package models;

import java.io.Serializable;
import java.util.Date;

public class ConsultaMedica implements Serializable {
	private static final long serialVersionUID = 1L;

    private Date fecha;
    private String diagnostico;
    private String tratamiento;
    private Doctor doctor;
    private Paciente paciente;

    public ConsultaMedica(Date fecha, String diagnostico, String tratamiento, Doctor doctor, Paciente paciente) {
        this.fecha = fecha;
        this.diagnostico = diagnostico;
        this.tratamiento = tratamiento;
        this.doctor = doctor;
        this.paciente = paciente;
    }

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getDiagnostico() {
		return diagnostico;
	}

	public void setDiagnostico(String diagnostico) {
		this.diagnostico = diagnostico;
	}

	public String getTratamiento() {
		return tratamiento;
	}

	public void setTratamiento(String tratamiento) {
		this.tratamiento = tratamiento;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}
	
	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}
	
	public void actualizarParametros(Date fecha, String diagnostico, String tratamiento, Doctor doctor, Paciente paciente) {
        this.fecha = fecha;
        this.diagnostico = diagnostico;
        this.tratamiento = tratamiento;
        this.doctor = doctor;
        this.paciente = paciente;
    }
	
	
}