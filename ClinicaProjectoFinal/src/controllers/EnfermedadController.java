package controllers;

import models.Enfermedad;
import java.util.ArrayList;

public class EnfermedadController {
    private ArrayList<Enfermedad> enfermedades;

    public EnfermedadController() {
        this.enfermedades = new ArrayList<>();
    }

    public void registrarEnfermedad(Enfermedad enfermedad) {
        enfermedades.add(enfermedad);
    }

    public ArrayList<Enfermedad> listarEnfermedades() {
        return enfermedades;
    }
}