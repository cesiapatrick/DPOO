package controllers;

import models.Vacunacion;
import java.util.ArrayList;

public class VacunacionController {
    private ArrayList<Vacunacion> vacunaciones;

    public VacunacionController() {
        this.vacunaciones = new ArrayList<>();
    }

    public void registrarVacunacion(Vacunacion vacunacion) {
        vacunaciones.add(vacunacion);
    }

    public ArrayList<Vacunacion> listarVacunaciones() {
        return vacunaciones;
    }
}