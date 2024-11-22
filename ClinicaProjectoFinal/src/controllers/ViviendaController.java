package controllers;

import models.Vivienda;
import java.util.ArrayList;

public class ViviendaController {
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