package controllers;

import models.HistoriaClinica;
import models.Paciente;
import java.util.HashMap;

public class HistoriaClinicaController {
    private HashMap<String, HistoriaClinica> historiasClinicas;

    public HistoriaClinicaController() {
        this.historiasClinicas = new HashMap<>();
    }

    public void registrarHistoria(Paciente paciente) {
        historiasClinicas.put(paciente.getDni(), new HistoriaClinica(paciente));
    }

    public HistoriaClinica obtenerHistoria(String dni) {
        return historiasClinicas.get(dni);
    }
}