package models;

public class Doctor {
    private String username;
    private String password;
    private String nombre;
    private String apellido;
    private String especialidad;

    public Doctor(String username, String password, String nombre, String apellido, String especialidad) {
        this.username = username;
        this.password = password;
        this.nombre = nombre;
        this.apellido = apellido;
        this.especialidad = especialidad;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    @Override
    public String toString() {
        return nombre + " " + apellido + " (" + especialidad + ")";
    }
}