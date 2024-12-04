package controllers;

import models.ConsultaMedica;
import models.Doctor;
import models.Enfermedad;
import models.HistoriaClinica;
import models.Paciente;
import models.Usuario;
import models.Vacunacion;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.FileSystemNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

public class HospitalData implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final String nombreArchivo = "datosAlmacenadosClinica.dat";
	
	private int idPacientes = 1;
	private int idDoctores = 1;
    private ArrayList<Doctor> doctores;
    private ArrayList<Paciente> pacientes;
	private ArrayList<Vacunacion> vacunaciones;
	private ArrayList<Enfermedad> enfermedades;
	private ArrayList<ConsultaMedica> consultasMedicas;
	private ArrayList<Usuario> usuarios;
    
    private static HospitalData hospitalData = null;
    private String categoriaUsuario = null;


    
    private HospitalData() {
        doctores = new ArrayList<>();
        pacientes = new ArrayList<>();
        vacunaciones = new ArrayList<>();
        enfermedades = new ArrayList<>();
        consultasMedicas = new ArrayList<>();
        usuarios = new ArrayList<>();
    }
    
    public static HospitalData getInstance() {
        if (hospitalData == null) {
            hospitalData = new HospitalData();
        }
        return hospitalData;
    }
    
    public void setUsuarioActivo (String categoriaUsuario) {
    	this.categoriaUsuario = categoriaUsuario;
    }
    
    public String getUsuarioActivo () {
    	return categoriaUsuario;
    }
    
    
    public static void setHospitalData(HospitalData hospitaDataArchivo) {
    	hospitalData = hospitaDataArchivo;
    }
    
    public void guardarDatosArchivo() {
		try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(nombreArchivo))) {
			out.writeObject(hospitalData);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
    
    public void cargarDatosArchivo() {
        File archivo = new File(nombreArchivo);
        if (archivo.exists()) {
            try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(nombreArchivo))) {
                hospitalData = (HospitalData) in.readObject();
                actualizarArrayClases();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        } 
    }
    
    public void actualizarArrayClases() {
    	
    	//HistoriaClinicaController.getInstance().setHistoriaClinica(historiasClinicas);
    	PacienteController.getInstance().setPacientes(pacientes);
    	VacunacionController.getInstance().setVacunaciones(vacunaciones);
	}
    
    //Vacunas
    public ArrayList<Vacunacion> getVacunas() {
		return vacunaciones;
	}

	public void setVacunas(ArrayList<Vacunacion> vacunas) {
		this.vacunaciones = vacunas;
	}
	
	public void addVacunacion(Vacunacion vacunacion) {
		vacunaciones.add(vacunacion);
	}
	
	//Enfermedades
	public ArrayList<Enfermedad> getEnfermedades() {
		return enfermedades;
	}

	public void setEnfermedades(ArrayList<Enfermedad> arrEnfermedades) {
		enfermedades = arrEnfermedades;
	}
	
	public void addEnfermedad(Enfermedad enfermedad) {
		enfermedades.add(enfermedad);
	}
	
	public boolean validadEnfermedadRegistrada(String nombre) {
    	for (Enfermedad enfermedad : enfermedades) {
            if (enfermedad.getNombre().equalsIgnoreCase(nombre)) {
                return true;
            }
        }
        return false;
    }
	
	//HistoriasClinicas
	
	
	public String obtenerResumenHistoriaClinica(int idPaciente) {
		String resumenHistoriaClinica = null;
		ArrayList<ConsultaMedica> consultasPaciente = new ArrayList<>();
		
		for(ConsultaMedica consulta: consultasMedicas) {
			if(consulta.getPaciente().getId() == idPaciente) {
				consultasPaciente.add(consulta);
			}
		}
		if (consultasPaciente.isEmpty()) {
			return resumenHistoriaClinica;
		}
		
		resumenHistoriaClinica = resumirHistoriaClinica(consultasPaciente);
		return resumenHistoriaClinica;
	}
	
	private String resumirHistoriaClinica(ArrayList<ConsultaMedica> consultasPaciente) {
        StringBuilder resumen = new StringBuilder();
        resumen.append("Resumen de Historia Clínica:\n");
        for (ConsultaMedica consulta : consultasPaciente) {
            resumen.append(consulta.getFecha()).append(" - Diagnóstico: ").append(consulta.getDiagnostico()).append("\n");
        }
        return resumen.toString();
    }
	
	//ConsultasMedicas
	public ArrayList<ConsultaMedica> getConsultasMedicas() {
		return consultasMedicas;
	}

	public void setConsultasMedicas(ArrayList<ConsultaMedica> consultasMedicas) {
		this.consultasMedicas = consultasMedicas;
	}
	
	public void addConsulta(ConsultaMedica consultaMedica) {
        consultasMedicas.add(consultaMedica);
    }

	//Doctores
    public ArrayList<Doctor> getDoctores() {
        return doctores;
    }

    public void addDoctor(Doctor doctor) {
    	idDoctores++;
        doctores.add(doctor);
    }
    
    public void setDoctores(ArrayList<Doctor> doctores) {
    	this.doctores = doctores;
    }
    
    public Doctor buscarByIdDoctor(int id) {
    	for(Doctor doctor: doctores) {
    		if(doctor.getId() == id) {
    			return doctor;
    		}
    	}
    	return null;
    }
    
    //Pacientes
    public ArrayList<Paciente> getPacientes() {
        return pacientes;
    }

    public void addPaciente(Paciente paciente) {
    	idPacientes++;
        pacientes.add(paciente);
    }
    
    public void setPacientes(ArrayList<Paciente> pacientes) {
    	this.pacientes = pacientes;
    }
    
    public Paciente buscarByIdPaciente(int id) {
    	for(Paciente paciente: pacientes) {
    		if(paciente.getId() ==  id) {
    			return paciente;
    		}
    	}
    	return null;
    }
    
    //Usuarios
    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

    public void addUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }
    
    public void setUsuarios(ArrayList<Usuario> usuarios) {
    	this.usuarios = usuarios;
    }
    
    public boolean autenticarInicioSesion(String username, String password) {
        for (Usuario usuario : usuarios) {
            if (usuario.getUsername().equals(username) && usuario.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }
    
    public boolean autenticarRegistro(String username) {
    	for (Usuario usuario : usuarios) {
            if (usuario.getUsername().equals(username)) {
                return false;
            }
        }
        return true;
    }
    
    //Id's
    public int getIdMedicos() {
    	return idDoctores;
    }
    
    public int getIdPacientes() {
    	return idPacientes;
    }    
    
}