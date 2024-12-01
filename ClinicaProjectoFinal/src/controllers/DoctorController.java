package controllers;

import java.io.Serializable;
import java.util.ArrayList;

import models.Doctor;

public class DoctorController implements Serializable {
	private static final long serialVersionUID = 1L;
    /*
    public boolean registrarDoctor(String id, String username, String password, String nombre, String apellido, String especialidad) {
    	ArrayList<Doctor> doctores = HospitalData.getInstance().getDoctores();
        for (Doctor doctor : doctores) {
            if (doctor.getId().equalsIgnoreCase(id)) {
                return false; // Usuario ya existe
            }
        }
        Doctor nuevoDoctor = new Doctor(id, username, password, nombre, apellido, especialidad);
        HospitalData.getInstance().addDoctor(nuevoDoctor);
        return true;
    }

    public boolean autenticarDoctor(String username, String password) {
    	ArrayList<Doctor> doctores = HospitalData.getInstance().getDoctores();
        for (Doctor doctor : doctores) {
            if (doctor.getUsername().equals(username) && doctor.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }
    */
}