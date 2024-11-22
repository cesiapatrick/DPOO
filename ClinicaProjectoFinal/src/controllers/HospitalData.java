package controllers;

import models.Doctor;
import models.Paciente;
import java.util.ArrayList;

public class HospitalData {
    private static ArrayList<Doctor> doctores;
    private static ArrayList<Paciente> pacientes;

    public static ArrayList<Doctor> getDoctores() {
        if (doctores == null) {
            initDoctores();
        }
        return doctores;
    }

    public static void initDoctores() {
        doctores = new ArrayList<>();
    }

    public static void addDoctor(Doctor doctor) {
        if (doctores == null) {
            initDoctores();
        }
        doctores.add(doctor);
    }

    public static ArrayList<Paciente> getPacientes() {
        if (pacientes == null) {
            initPacientes();
        }
        return pacientes;
    }

    public static void initPacientes() {
        pacientes = new ArrayList<>();
    }

    public static void addPaciente(Paciente paciente) {
        if (pacientes == null) {
            initPacientes();
        }
        pacientes.add(paciente);
    }
}