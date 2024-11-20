package models;

public class Paciente {
    private String nombre;
    private int edad;
    private String dni;
    private String tipoSangre;
    private String genero;
    private String cedula;
    private Vivienda vivienda;

    public Paciente(String nombre, int edad, String dni, String tipoSangre, String genero, String cedula, Vivienda vivienda) {
        this.nombre = nombre;
        this.edad = edad;
        this.dni = dni;
        this.tipoSangre = tipoSangre;
        this.genero = genero;
        this.cedula = cedula;
        this.vivienda = vivienda;
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public String getDni() {
        return dni;
    }

    public String getTipoSangre() {
        return tipoSangre;
    }

    public String getGenero() {
        return genero;
    }

    public String getCedula() {
        return cedula;
    }

    public Vivienda getVivienda() {
        return vivienda;
    }

    @Override
    public String toString() {
        return nombre + " (Cedula: " + cedula + ")";
    }
}