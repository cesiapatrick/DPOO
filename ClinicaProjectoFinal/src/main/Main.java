
package main;

import controllers.HospitalData;
import views.AdminLoginView;

public class Main {
    public static void main(String[] args) {
    	//Cargar datos Archivo
    	HospitalData  hospitalData = HospitalData.getInstance();
    	hospitalData.cargarDatosArchivo();
        new AdminLoginView().setVisible(true);
    }
}
