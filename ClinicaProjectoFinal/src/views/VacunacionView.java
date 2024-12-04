package views;

import javax.swing.*;
import controllers.HospitalData;
import models.Vacunacion;
import models.Paciente;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class VacunacionView extends JFrame {
    private JTextField txtVacuna;
    private int idPaciente;
    private JTextField txtPacienteEncontrado;
    private JCheckBox chkCompletada;
    private Paciente pacienteEncontrado = null;
    private HospitalData hospitalData = HospitalData.getInstance();

    public VacunacionView() {
        setTitle(" Control de Vacunación ");
        setSize(900, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Panel principal con gradiente
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
                
                // Círculos decorativos
                g2d.setColor(new Color(255, 255, 255, 50));
                g2d.fillOval(-50, -50, 200, 200);
                g2d.fillOval(w-150, h-150, 200, 200);
            }
        };
        mainPanel.setLayout(null);

        // Título decorativo
        JLabel titleLabel = new JLabel("Sistema de Vacunación");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setForeground(new Color(41, 84, 144));
        titleLabel.setBounds(50, 20, 400, 40);
        mainPanel.add(titleLabel);

        // Panel para la imagen
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
        
        ImageIcon icon = new ImageIcon(getClass().getResource("F.jpeg"));
        Image img = icon.getImage();
        Image scaledImg = img.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImg);

        JLabel imgLabel = new JLabel(scaledIcon);
        imgLabel.setHorizontalAlignment(JLabel.CENTER);
        imagePanel.add(imgLabel);
        mainPanel.add(imagePanel);

        // Panel de formulario
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

        // Estilo común para etiquetas
        Font labelFont = new Font("Segoe UI", Font.BOLD, 14);
        Color labelColor = new Color(41, 84, 144);
        
        //Buscar Paciente
        JLabel lblBuscarPaciente = new JLabel("Buscar Paciente ID:");
        styleLabel(lblBuscarPaciente, labelFont, labelColor);
        lblBuscarPaciente.setBounds(25, 40, 127, 30);

        JTextField txtBuscarPaciente = new JTextField();
        styleTextField(txtBuscarPaciente);
        txtBuscarPaciente.setBounds(165, 40, 230, 35);

        // Boton buscar paciente
        JButton btnBuscarPaciente = new JButton("Buscar");
        styleButton(btnBuscarPaciente);
        btnBuscarPaciente.setBounds(395, 40, 80, 35);
        formPanel.add(btnBuscarPaciente);

        //Paciente
        JLabel lblPaciente = new JLabel("Paciente:");
        styleLabel(lblPaciente, labelFont, labelColor);
        lblPaciente.setBounds(25, 120, 120, 30);

        txtPacienteEncontrado = new JTextField();
        styleTextField(txtPacienteEncontrado);
        txtPacienteEncontrado.setBounds(165, 120, 280, 35);
        txtPacienteEncontrado.setEditable(false);
        formPanel.add(txtPacienteEncontrado);

        //Vacunas
        JLabel lblVacuna = new JLabel("Vacuna:");
        styleLabel(lblVacuna, labelFont, labelColor);
        lblVacuna.setBounds(25, 200, 120, 30);
        
        
        txtVacuna = new JTextField();
        styleTextField(txtVacuna);
        txtVacuna.setBounds(165, 200, 280, 35);

        JLabel lblCompletada = new JLabel("Completada:");
        styleLabel(lblCompletada, labelFont, labelColor);
        lblCompletada.setBounds(25, 280, 120, 30);

        // Checkbox vacuna completada
        chkCompletada = new JCheckBox();
        chkCompletada.setBounds(165, 280, 35, 35);
        chkCompletada.setOpaque(false);


        // Boton para registrar vacuna
        JButton btnRegistrarVacuna = new JButton("Registrar Vacuna");
        styleButton(btnRegistrarVacuna);
        btnRegistrarVacuna.setBounds(120, 450, 280, 40);

        
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
                

        // Agregar componentes al panel de formulario
        formPanel.add(lblPaciente);
        formPanel.add(txtPacienteEncontrado);
        formPanel.add(lblBuscarPaciente);
        formPanel.add(txtBuscarPaciente);
        //formPanel.add(comboPacientes);
        formPanel.add(lblVacuna);
        formPanel.add(txtVacuna);
        formPanel.add(lblCompletada);
        formPanel.add(chkCompletada);
        formPanel.add(btnRegistrarVacuna);

        mainPanel.add(formPanel);

        // Footer decorativo
        JLabel footerLabel = new JLabel("© 2024 Clínica Médica");
        footerLabel.setFont(new Font("Segoe UI", Font.ITALIC, 12));
        footerLabel.setForeground(new Color(100, 100, 100));
        footerLabel.setBounds(50, 640, 200, 20);
        mainPanel.add(footerLabel);

        add(mainPanel);
        setLocationRelativeTo(null);
        

        btnRegistrarVacuna.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Paciente paciente = (Paciente) pacienteEncontrado;
                String nombreVacuna = txtVacuna.getText();
                boolean completada = chkCompletada.isSelected();
                Vacunacion vacunacion = new Vacunacion(nombreVacuna, completada, paciente);
                hospitalData.addVacunacion(vacunacion);
                
                //Validaciones
				if (pacienteEncontrado == null) {
					JOptionPane.showMessageDialog(null, "No se ha encontrado un paciente. Por favor, busque un paciente primero.", 
							"Error", JOptionPane.ERROR_MESSAGE);
					txtBuscarPaciente.setText("");
					txtPacienteEncontrado.setText("");
					return;
				}
				
				if (paciente == null ||  nombreVacuna.isEmpty()) {
        			JOptionPane.showMessageDialog(null, "❌ Por favor complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
        			return;
        		}
                
                JOptionPane.showMessageDialog(null, " Vacunación registrada exitosamente!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                txtVacuna.setText("");
                txtBuscarPaciente.setText("");
                txtPacienteEncontrado.setText("");
                chkCompletada.setSelected(false);
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

    private void styleComboBox(JComboBox<?> comboBox) {
        comboBox.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        comboBox.setBackground(new Color(240, 248, 255));
        comboBox.setBorder(BorderFactory.createLineBorder(new Color(135, 206, 250), 2));
    }

    private void styleButton(JButton button) {
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(30, 144, 255));
        button.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // Efecto hover
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