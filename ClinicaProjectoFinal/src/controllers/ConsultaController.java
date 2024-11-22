package controllers;

import models.ConsultaMedica;
import java.util.ArrayList;

public class ConsultaController {
    private ArrayList<ConsultaMedica> consultas;

    public ConsultaController() {
        this.consultas = new ArrayList<>();
    }

    public void registrarConsulta(ConsultaMedica consulta) {
        consultas.add(consulta);
    }

    public ArrayList<ConsultaMedica> listarConsultas() {
        return consultas;
    }
}