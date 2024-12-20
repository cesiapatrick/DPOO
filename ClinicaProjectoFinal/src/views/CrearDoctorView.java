package views;

import javax.swing.*;
import controllers.HospitalData;
import models.Doctor;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CrearDoctorView extends JFrame {
	private JTextField txtId;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtEspecialidad;
	private Doctor doctor = null;
	private HospitalData hospitalData = HospitalData.getInstance();
	private int idDoctor = hospitalData.getIdMedicos();

	public CrearDoctorView() {
		//doctorController = new DoctorController();
		setTitle(" Registro de Nuevo Doctor ");
		setSize(800, 600);
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


				g2d.setColor(new Color(255, 255, 255, 50));
				g2d.fillOval(-50, -50, 200, 200);
				g2d.fillOval(w-150, h-150, 200, 200);
			}
		};
		mainPanel.setLayout(null);


		JLabel titleLabel = new JLabel("Registro de Personal Médico");
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
		imagePanel.setBounds(450, 80, 300, 300);
		imagePanel.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createEmptyBorder(10, 10, 10, 10),
				BorderFactory.createLineBorder(new Color(255, 255, 255, 200), 3)
				));

		ImageIcon icon = new ImageIcon(getClass().getResource("B.jpg")); 
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
		formPanel.setBounds(50, 80, 380, 450);
		formPanel.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createEmptyBorder(20, 20, 20, 20),
				BorderFactory.createLineBorder(new Color(255, 255, 255, 200), 3)
				));


		Font labelFont = new Font("Segoe UI", Font.BOLD, 14);
		Color labelColor = new Color(41, 84, 144);


		addFormField(formPanel, " ID:", txtId = new JTextField(String.valueOf(idDoctor)), 50);
		addFormField(formPanel, " Nombre:", txtNombre = new JTextField(), 120);
		addFormField(formPanel, " Apellido:", txtApellido = new JTextField(), 190);
		addFormField(formPanel, " Especialidad:", txtEspecialidad = new JTextField(), 260);

		//Boton de Registrar
		JButton btnRegistrar = new JButton(" Registrar Doctor");
		styleButton(btnRegistrar);
		btnRegistrar.setBounds(50, 350, 280, 40);
		formPanel.add(btnRegistrar);

		mainPanel.add(formPanel);


		JLabel footerLabel = new JLabel("© 2024 Clínica Médica");
		footerLabel.setFont(new Font("Segoe UI", Font.ITALIC, 12));
		footerLabel.setForeground(new Color(100, 100, 100));
		footerLabel.setBounds(50, 540, 200, 20);
		mainPanel.add(footerLabel);

		add(mainPanel);
		setLocationRelativeTo(null);

		btnRegistrar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//String id = txtId.getText();
				String nombre = txtNombre.getText();
				String apellido = txtApellido.getText();
				String especialidad = txtEspecialidad.getText();

				if (nombre.isEmpty() || apellido.isEmpty() || especialidad.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Por favor complete todos los campos.", 
							"Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				//Registrar Doctor y Guardar
				doctor = new Doctor(idDoctor, nombre, apellido, especialidad);
				hospitalData.addDoctor(doctor);
				JOptionPane.showMessageDialog(null, " Doctor registrado exitosamente");
				dispose();
			}
		});
	}

	//Para Modificar Doctor
	
	public CrearDoctorView(Doctor doctorModif) {
		//doctorController = new DoctorController();
		setTitle(" Modificar Datos Doctor ");
		setSize(800, 600);
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


				g2d.setColor(new Color(255, 255, 255, 50));
				g2d.fillOval(-50, -50, 200, 200);
				g2d.fillOval(w-150, h-150, 200, 200);
			}
		};
		mainPanel.setLayout(null);


		JLabel titleLabel = new JLabel("Registro de Personal Médico");
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
		imagePanel.setBounds(450, 80, 300, 300);
		imagePanel.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createEmptyBorder(10, 10, 10, 10),
				BorderFactory.createLineBorder(new Color(255, 255, 255, 200), 3)
				));

		ImageIcon icon = new ImageIcon(getClass().getResource("B.jpg")); 
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
		formPanel.setBounds(50, 80, 380, 450);
		formPanel.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createEmptyBorder(20, 20, 20, 20),
				BorderFactory.createLineBorder(new Color(255, 255, 255, 200), 3)
				));


		Font labelFont = new Font("Segoe UI", Font.BOLD, 14);
		Color labelColor = new Color(41, 84, 144);

		//Tomar datos del doctor original y mostralos
		int idOriginal = doctorModif.getId();
		String nombreOriginal = doctorModif.getNombre();
		String apellidoOriginal = doctorModif.getApellido();
		String especialidadOriginal = doctorModif.getEspecialidad();
		
		addFormField(formPanel, " ID:", txtId = new JTextField(String.valueOf(idOriginal)), 50);
		addFormField(formPanel, " Nombre:", txtNombre = new JTextField(nombreOriginal), 120);
		addFormField(formPanel, " Apellido:", txtApellido = new JTextField(apellidoOriginal), 190);
		addFormField(formPanel, " Especialidad:", txtEspecialidad = new JTextField(especialidadOriginal), 260);

		//Boton de Registrar
		JButton btnRegistrar = new JButton(" Modificar Doctor");
		styleButton(btnRegistrar);
		btnRegistrar.setBounds(50, 350, 280, 40);
		formPanel.add(btnRegistrar);

		mainPanel.add(formPanel);


		JLabel footerLabel = new JLabel("© 2024 Clínica Médica");
		footerLabel.setFont(new Font("Segoe UI", Font.ITALIC, 12));
		footerLabel.setForeground(new Color(100, 100, 100));
		footerLabel.setBounds(50, 540, 200, 20);
		mainPanel.add(footerLabel);

		add(mainPanel);
		setLocationRelativeTo(null);

		btnRegistrar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//String id = txtId.getText();
				String nombre = txtNombre.getText();
				String apellido = txtApellido.getText();
				String especialidad = txtEspecialidad.getText();

				if (nombre.isEmpty() || apellido.isEmpty() || especialidad.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Por favor complete todos los campos.", 
							"Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				//Modificar Doctor y Guarda
				doctorModif.actualizarParametros(nombre, apellido, especialidad);
				
				JOptionPane.showMessageDialog(null, " Doctor Modificado exitosamente");
				dispose();
			}
		});
		dispose();
	}

	private void addFormField(JPanel panel, String labelText, JTextField field, int y) {
		JLabel label = new JLabel(labelText);
		label.setFont(new Font("Segoe UI", Font.BOLD, 14));
		label.setForeground(new Color(41, 84, 144));
		label.setBounds(30, y, 120, 30);

		field.setBounds(150, y, 200, 35);
		styleTextField(field);
		if (labelText.equals(" ID:")) {
			field.setHorizontalAlignment(JTextField.CENTER);
			field.setCaretColor(new Color(0, 0, 0, 0));
			field.setFocusable(false);
			field.setFont(new Font("Segoe UI", Font.BOLD, 14));
		}

		panel.add(label);
		panel.add(field);
	}

	private void styleTextField(JTextField field) {
		field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		field.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(new Color(135, 206, 250), 2),
				BorderFactory.createEmptyBorder(5, 10, 5, 10)
				));
		field.setBackground(new Color(240, 248, 255));
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

	private void limpiarCampos() {
		txtNombre.setText("");
		txtApellido.setText("");
		txtEspecialidad.setText("");
	}
}