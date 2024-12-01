package controllers;

import models.Vacunacion;
import java.io.Serializable;
import java.util.ArrayList;

public class VacunacionController implements Serializable {
	private static final long serialVersionUID = 1L;

    private ArrayList<Vacunacion> vacunaciones;
    
    private static VacunacionController vacunacionController = null;

	public VacunacionController() {
		this.vacunaciones = new ArrayList<>();
	}

	public static VacunacionController getInstance() {
		if (vacunacionController == null) {
			vacunacionController = new VacunacionController();
		}
		return vacunacionController;
	}

    public void registrarVacunacion(Vacunacion vacunacion) {
        vacunaciones.add(vacunacion);
    }

    //GetVacunaciones
    public ArrayList<Vacunacion> listarVacunaciones() {
        return vacunaciones;
    }
    
    public void setVacunaciones(ArrayList<Vacunacion> vacunas) {
    	this.vacunaciones = vacunas;
    }
}