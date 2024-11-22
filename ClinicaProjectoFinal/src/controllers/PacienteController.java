package controllers;

import models.Paciente;
import java.util.ArrayList;

public class PacienteController {
    private ArrayList<Paciente> pacientes;

    public PacienteController() {
        this.pacientes = new ArrayList<>();
    }

    public void registrarPaciente(Paciente paciente) {
        pacientes.add(paciente);
    }

    public ArrayList<Paciente> listarPacientes() {
        return pacientes;
    }
}