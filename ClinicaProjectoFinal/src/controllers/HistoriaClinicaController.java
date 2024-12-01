package controllers;

import models.HistoriaClinica;
import models.Paciente;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class HistoriaClinicaController implements Serializable {
	private static final long serialVersionUID = 1L;

    private HashMap<Integer, HistoriaClinica> historiasClinicas = new HashMap<>();

    private static HistoriaClinicaController historiaClinicaController = null;

    public static HistoriaClinicaController getInstance() {
        if (historiaClinicaController == null) {
        	historiaClinicaController = new HistoriaClinicaController();
        }
        return historiaClinicaController;
    }
    
    public void registrarHistoria(Paciente paciente) {
        historiasClinicas.put(paciente.getId(), new HistoriaClinica(paciente));
    }

    public HistoriaClinica obtenerHistoria(String dni) {
        return historiasClinicas.get(dni);
    }
    
    public void setHistoriaClinica (HashMap<Integer, HistoriaClinica> historiasClinicas) {
    	this.historiasClinicas = historiasClinicas;
    }
    
    public HashMap<Integer, HistoriaClinica> getHistoriasClinicas() {
        return historiasClinicas;
    }
}