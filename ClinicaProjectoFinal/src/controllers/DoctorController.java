package controllers;

import models.Doctor;

public class DoctorController {
    public DoctorController() {
        if (HospitalData.getDoctores() == null) {
            HospitalData.initDoctores();
        }
    }

    public boolean registrarDoctor(String username, String password, String nombre, String apellido, String especialidad) {
        for (Doctor doctor : HospitalData.getDoctores()) {
            if (doctor.getUsername().equals(username)) {
                return false; // Usuario ya existe
            }
        }
        Doctor nuevoDoctor = new Doctor(username, password, nombre, apellido, especialidad);
        HospitalData.addDoctor(nuevoDoctor);
        return true;
    }

    public boolean autenticarDoctor(String username, String password) {
        for (Doctor doctor : HospitalData.getDoctores()) {
            if (doctor.getUsername().equals(username) && doctor.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }
}