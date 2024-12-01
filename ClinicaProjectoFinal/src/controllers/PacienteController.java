package controllers;

import models.Paciente;
import java.io.Serializable;
import java.util.ArrayList;

public class PacienteController implements Serializable {
	private static final long serialVersionUID = 1L;

	private ArrayList<Paciente> pacientes;

	private static PacienteController pacienteController = null;

	public PacienteController() {
		this.pacientes = new ArrayList<>();
	}

	public static PacienteController getInstance() {
		if (pacienteController == null) {
			pacienteController = new PacienteController();
		}
		return pacienteController;
	}

	public void registrarPaciente(Paciente paciente) {
		pacientes.add(paciente);
	}

	public ArrayList<Paciente> listarPacientes() {
		return pacientes;
	}

	public void setPacientes (ArrayList<Paciente> pacientes) {
		this.pacientes = pacientes;
	}
}