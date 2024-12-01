package controllers;

import models.Enfermedad;
import java.io.Serializable;
import java.util.ArrayList;

public class EnfermedadController implements Serializable {
	private static final long serialVersionUID = 1L;

    private ArrayList<Enfermedad> enfermedades;
    
    private static EnfermedadController enfermedadController = null;

    public EnfermedadController() {
        this.enfermedades = new ArrayList<>();
    }
    
    public static EnfermedadController getInstance() {
        if (enfermedadController == null) {
        	enfermedadController = new EnfermedadController();
        }
        return enfermedadController;
    }


    public void registrarEnfermedad(Enfermedad enfermedad) {
        enfermedades.add(enfermedad);
    }

    public ArrayList<Enfermedad> listarEnfermedades() {
        return enfermedades;
    }
    
    public void setEnfermedades(ArrayList<Enfermedad> enfermedades) {
    	this.enfermedades = enfermedades;
    }
    
    
}