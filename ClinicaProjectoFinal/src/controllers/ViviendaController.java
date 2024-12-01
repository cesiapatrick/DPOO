package controllers;

import models.Vivienda;
import java.io.Serializable;
import java.util.ArrayList;

public class ViviendaController implements Serializable {
	private static final long serialVersionUID = 1L;

    private ArrayList<Vivienda> viviendas;

    public ViviendaController() {
        this.viviendas = new ArrayList<>();
    }

    public void registrarVivienda(Vivienda vivienda) {
        viviendas.add(vivienda);
    }

    public ArrayList<Vivienda> listarViviendas() {
        return viviendas;
    }
}