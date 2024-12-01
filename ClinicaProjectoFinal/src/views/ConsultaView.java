package views;

import javax.swing.*;
import javax.swing.text.JTextComponent;

import controllers.ConsultaController;
import controllers.HospitalData;
import models.ConsultaMedica;
import models.Paciente;
import models.Doctor;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Date;
import com.toedter.calendar.JDateChooser;


public class ConsultaView extends JFrame {
	private int idPaciente;
	private int idDoctor;
	private JTextField txtDiagnostico;
	private JTextField txtTratamiento;
	private JTextField txtPacienteEncontrado;
	private JTextField txtDoctorEncontrado;
	private JDateChooser dateChooser;
	private ConsultaController consultaController = ConsultaController.getInstance();
	private Paciente pacienteEncontrado = null;
	private Doctor doctorEncontrado = null;
	private ConsultaMedica consultaMedica = null;
	private HospitalData hospitalData = HospitalData.getInstance();

	public ConsultaView() {
		setTitle("✨ Registro de Consultas Médicas ✨");
		setSize(900, 700);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


		JPanel mainPanel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Graphics2D g2d = (Graphics2D) g;
				g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
				int w = getWidth();
				int h = getHeight();
				GradientPaint gp = new GradientPaint(
						0, 0, new Color(162, 217, 255),
						0, h, new Color(216, 238, 255)
						);
				g2d.setPaint(gp);
				g2d.fillRect(0, 0, w, h);

				// Agregar círculos decorativos
				g2d.setColor(new Color(255, 255, 255, 50));
				g2d.fillOval(-50, -50, 200, 200);
				g2d.fillOval(w-150, h-150, 200, 200);
			}
		};
		mainPanel.setLayout(null);


		JLabel titleLabel = new JLabel("Sistema de Consultas Médicas");
		titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
		titleLabel.setForeground(new Color(41, 84, 144));
		titleLabel.setBounds(50, 20, 400, 40);
		mainPanel.add(titleLabel);


		JPanel imagePanel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Graphics2D g2d = (Graphics2D) g;
				g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
				GradientPaint gp = new GradientPaint(
						0, 0, new Color(135, 206, 250),
						getWidth(), getHeight(), new Color(176, 224, 230)
						);
				g2d.setPaint(gp);
				g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
			}
		};
		imagePanel.setLayout(new BorderLayout());
		imagePanel.setBounds(550, 80, 300, 300);
		imagePanel.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createEmptyBorder(10, 10, 10, 10),
				BorderFactory.createLineBorder(new Color(255, 255, 255, 200), 3)
				));

		ImageIcon icon = new ImageIcon(getClass().getResource("R.png"));
		Image img = icon.getImage();
		Image scaledImg = img.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
		ImageIcon scaledIcon = new ImageIcon(scaledImg);

		JLabel imgLabel = new JLabel(scaledIcon);
		imgLabel.setHorizontalAlignment(JLabel.CENTER);
		imagePanel.add(imgLabel);
		mainPanel.add(imagePanel);


		JPanel formPanel = new JPanel(null) {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Graphics2D g2d = (Graphics2D) g;
				g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
				GradientPaint gp = new GradientPaint(
						0, 0, new Color(255, 255, 255, 240),
						0, getHeight(), new Color(240, 248, 255, 240)
						);
				g2d.setPaint(gp);
				g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
			}
		};
		formPanel.setBounds(50, 80, 480, 550);
		formPanel.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createEmptyBorder(20, 20, 20, 20),
				BorderFactory.createLineBorder(new Color(255, 255, 255, 200), 3)
				));


		Font labelFont = new Font("Segoe UI", Font.BOLD, 14);
		Color labelColor = new Color(41, 84, 144);

		//Buscar paciente
		JLabel lblBuscarPaciente = new JLabel("Buscar Paciente ID:");
		styleLabel(lblBuscarPaciente, labelFont, labelColor);
		lblBuscarPaciente.setBounds(20, 20, 127, 30);

		JTextField txtBuscarPaciente = new JTextField();
		styleTextField(txtBuscarPaciente);
		txtBuscarPaciente.setBounds(150, 20, 230, 35);

		

		//Txt Pacientes Encontrados
		txtPacienteEncontrado = new JTextField();
		styleTextField(txtPacienteEncontrado);
		txtPacienteEncontrado.setBounds(150, 80, 280, 35);
		txtPacienteEncontrado.setEditable(false); 
		formPanel.add(txtPacienteEncontrado);
		
		//Boton Buscar Paciente
		JButton btnBuscarPaciente = new JButton("Buscar");
		styleButton(btnBuscarPaciente);
		btnBuscarPaciente.setBounds(380, 20, 80, 35);
		formPanel.add(btnBuscarPaciente);


		//Iniciar Busqueda Paciente

		btnBuscarPaciente.addActionListener(new ActionListener() {
			@Override

			public void actionPerformed(ActionEvent e) {
				try {
					String idPacienteStr = txtBuscarPaciente.getText().trim();
					idPaciente = Integer.parseInt(idPacienteStr);
				} catch (NumberFormatException e1) {
				    JOptionPane.showMessageDialog(null, "Por favor, ingrese un ID de paciente valido.", "Error", JOptionPane.ERROR_MESSAGE);
				    return;
				}
				pacienteEncontrado = HospitalData.getInstance().buscarByIdPaciente(idPaciente); 

				if (pacienteEncontrado != null) {
					txtPacienteEncontrado.setText(pacienteEncontrado.getNombre()+" "+pacienteEncontrado.getApellido());
				} else {
					txtPacienteEncontrado.setText("**Paciente no encontrado**");
				}

			}
		});

		//Paciente
		JLabel lblPaciente = new JLabel("Paciente:");
		styleLabel(lblPaciente, labelFont, labelColor);
		lblPaciente.setBounds(20, 80, 120, 30);


		//Buscar Doctor
		JLabel lblBuscarDoctor = new JLabel("Buscar Doctor ID:");
		styleLabel(lblBuscarDoctor, labelFont, labelColor);
		lblBuscarDoctor.setBounds(20, 140, 120, 30);

		JTextField txtBuscarDoctor = new JTextField();
		styleTextField(txtBuscarDoctor);
		txtBuscarDoctor.setBounds(150, 140, 230, 35);

		//Boton Buscar Doctor
		JButton btnBuscarDoctor = new JButton("Buscar");
		styleButton(btnBuscarDoctor);
		btnBuscarDoctor.setBounds(380, 140, 80, 35); 
		formPanel.add(btnBuscarDoctor);
		//

		//Iniciar Busqueda Doctor
		btnBuscarDoctor.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String idDoctorStr = txtBuscarDoctor.getText().trim();
					idDoctor = Integer.parseInt(idDoctorStr);
				} catch (NumberFormatException e1) {
				    JOptionPane.showMessageDialog(null, "Por favor, ingrese un ID de medico valido.", "Error", JOptionPane.ERROR_MESSAGE);
				    return;
				}
				doctorEncontrado = HospitalData.getInstance().buscarByIdDoctor(idDoctor); 
				
				if (doctorEncontrado != null) {
					txtDoctorEncontrado.setText(doctorEncontrado.getNombre()+" "+doctorEncontrado.getApellido()+" -- "+doctorEncontrado.getEspecialidad());
				} else {
					txtDoctorEncontrado.setText("**Doctor no encontrado**");
				}
			}
		});

		//Doctor
		JLabel lblDoctor = new JLabel("Doctor:");
		styleLabel(lblDoctor, labelFont, labelColor);
		lblDoctor.setBounds(20, 200, 120, 30);

		//Txt DoctorEncontrado
		txtDoctorEncontrado = new JTextField();
		styleTextField(txtDoctorEncontrado);
		txtDoctorEncontrado.setBounds(150, 200, 280, 35);
		txtDoctorEncontrado.setEditable(false);
		formPanel.add(txtDoctorEncontrado);


		//Fecha
		JLabel lblFecha = new JLabel("Fecha:");
		styleLabel(lblFecha, labelFont, labelColor);
		lblFecha.setBounds(20, 260, 120, 30);

		// Agregando JDateChooser para la fecha
        dateChooser = new JDateChooser();
        dateChooser.setDateFormatString("yyyy-MM-dd");
        dateChooser.setBounds(150, 260, 200, 35);
        dateChooser.getDateEditor().getUiComponent().setFont(new Font("Segoe UI", Font.BOLD, 14));
        ((JTextField) dateChooser.getDateEditor().getUiComponent()).setHorizontalAlignment(JTextField.CENTER);
        dateChooser.getDateEditor().getUiComponent().setForeground(Color.BLACK);
        dateChooser.getDateEditor().getUiComponent().setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(new Color(135, 206, 250), 2),
				BorderFactory.createEmptyBorder(5, 10, 5, 10)
				));
        dateChooser.getDateEditor().getUiComponent().setBackground(new Color(240,248,255));
        ((JTextComponent) dateChooser.getDateEditor().getUiComponent()).setEditable(false);

		//Diagnostico
		JLabel lblDiagnostico = new JLabel("Diagnóstico:");
		styleLabel(lblDiagnostico, labelFont, labelColor);
		lblDiagnostico.setBounds(20, 320, 120, 30);

		txtDiagnostico = new JTextField();
		styleTextField(txtDiagnostico);
		txtDiagnostico.setBounds(150, 320, 280, 35);

		//Tratamiento
		JLabel lblTratamiento = new JLabel("Tratamiento:");
		styleLabel(lblTratamiento, labelFont, labelColor);
		lblTratamiento.setBounds(20, 380, 120, 30);

		txtTratamiento = new JTextField();
		styleTextField(txtTratamiento);
		txtTratamiento.setBounds(150, 380, 280, 35);

		//Boton Registrar Consulta
		JButton btnRegistrarConsulta = new JButton(" Registrar Consulta");
		styleButton(btnRegistrarConsulta);
		btnRegistrarConsulta.setBounds(100, 460, 280, 40);

		// Agregar componentes al panel de formulario
		formPanel.add(lblBuscarPaciente);
		formPanel.add(txtBuscarPaciente);
		formPanel.add(lblBuscarDoctor);
		formPanel.add(txtBuscarDoctor);
		formPanel.add(lblPaciente);
		formPanel.add(txtPacienteEncontrado);
		formPanel.add(lblDoctor);
		formPanel.add(txtDoctorEncontrado);
		formPanel.add(lblFecha);
		formPanel.add(dateChooser);
		formPanel.add(lblDiagnostico);
		formPanel.add(txtDiagnostico);
		formPanel.add(lblTratamiento);
		formPanel.add(txtTratamiento);
		formPanel.add(btnRegistrarConsulta);

		mainPanel.add(formPanel);

		// Footer decorativo
		JLabel footerLabel = new JLabel("© 2024 Clínica Médica");
		footerLabel.setFont(new Font("Segoe UI", Font.ITALIC, 12));
		footerLabel.setForeground(new Color(100, 100, 100));
		footerLabel.setBounds(50, 640, 200, 20);
		mainPanel.add(footerLabel);

		getContentPane().add(mainPanel);
		setLocationRelativeTo(null);

		btnRegistrarConsulta.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Paciente paciente = (Paciente) pacienteEncontrado;
				Doctor doctor = (Doctor) doctorEncontrado;
				Date fechaConsulta = dateChooser.getDate();
				String diagnostico = txtDiagnostico.getText();
				String tratamiento = txtTratamiento.getText();
				
				//Validaciones
				if (pacienteEncontrado == null) {
					JOptionPane.showMessageDialog(null, "No se ha encontrado un paciente. Por favor, busque un paciente primero.", 
							"Error", JOptionPane.ERROR_MESSAGE);
					txtBuscarPaciente.setText("");
					txtPacienteEncontrado.setText("");
					return;
				}

				if (doctorEncontrado == null) {
					JOptionPane.showMessageDialog(null, "No se ha encontrado un doctor. Por favor, busque un doctor primero.",
							"Error", JOptionPane.ERROR_MESSAGE);;
							txtBuscarDoctor.setText("");
							txtDoctorEncontrado.setText("");
							return; 
				}
				
				if (paciente == null || doctor == null || fechaConsulta == null || diagnostico.isEmpty() || tratamiento.isEmpty()) {
        			JOptionPane.showMessageDialog(null, "❌ Por favor complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
        			return;
        		}
				

				consultaMedica = new ConsultaMedica (fechaConsulta, diagnostico, tratamiento, doctor, paciente);
				hospitalData.addConsulta(consultaMedica);
				consultaController.registrarConsulta(consultaMedica);

				JOptionPane.showMessageDialog(null, "✅ Consulta registrada exitosamente!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
				txtDiagnostico.setText("");
				txtTratamiento.setText("");
				dateChooser.setDate(new Date());
				dispose();
			}
		});
	}

	private void styleLabel(JLabel label, Font font, Color color) {
		label.setFont(font);
		label.setForeground(color);
	}

	private void styleTextField(JTextField textField) {
		textField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		textField.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(new Color(135, 206, 250), 2),
				BorderFactory.createEmptyBorder(5, 10, 5, 10)
				));
		textField.setBackground(new Color(240, 248, 255));
	}

	private void styleButton(JButton button) {
		button.setFont(new Font("Segoe UI", Font.BOLD, 14));
		button.setForeground(Color.WHITE);
		button.setBackground(new Color(30, 144, 255));
		button.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
		button.setFocusPainted(false);
		button.setCursor(new Cursor(Cursor.HAND_CURSOR));


		button.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				button.setBackground(new Color(0, 119, 204));
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				button.setBackground(new Color(30, 144, 255));
			}
		});
	}
}