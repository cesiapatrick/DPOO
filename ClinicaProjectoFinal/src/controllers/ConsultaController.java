package controllers;

import models.ConsultaMedica;
import java.io.Serializable;
import java.util.ArrayList;

public class ConsultaController implements Serializable {
	private static final long serialVersionUID = 1L;

    private ArrayList<ConsultaMedica> consultas;
    
    private static ConsultaController consultaController = null;

    
    public ConsultaController() {
        this.consultas = new ArrayList<>();
    }
    
    public static ConsultaController getInstance() {
        if (consultaController == null) {
        	consultaController= new ConsultaController();
        }
        return consultaController;
    }

    
    public void registrarConsulta(ConsultaMedica consulta) {
        consultas.add(consulta);
    }

    public ArrayList<ConsultaMedica> listarConsultas() {
        return consultas;
    }
    
    public void setConsultas(ArrayList<ConsultaMedica> consultas) {
    	this.consultas = consultas;
    }
}