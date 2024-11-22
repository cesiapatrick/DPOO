package views;

import javax.swing.*;
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

public class ConsultaView extends JFrame {
    private JTextField txtDiagnostico;
    private JTextField txtTratamiento;
    private JComboBox<Paciente> comboPacientes;
    private JComboBox<Doctor> comboDoctores;
    private JSpinner spinnerFecha;
    private ConsultaController consultaController;

    public ConsultaView() {
        consultaController = new ConsultaController();
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

        
        JLabel lblBuscarPaciente = new JLabel(" Buscar Paciente:");
        styleLabel(lblBuscarPaciente, labelFont, labelColor);
        lblBuscarPaciente.setBounds(30, 10, 120, 30);

        JTextField txtBuscarPaciente = new JTextField();
        styleTextField(txtBuscarPaciente);
        txtBuscarPaciente.setBounds(150, 10, 280, 35);

        
        JLabel lblPaciente = new JLabel(" Paciente:");
        styleLabel(lblPaciente, labelFont, labelColor);
        lblPaciente.setBounds(30, 100, 120, 30);
        
        comboPacientes = new JComboBox<>();
        for (Paciente paciente : HospitalData.getPacientes()) {
            comboPacientes.addItem(paciente);
        }
        styleComboBox(comboPacientes);
        comboPacientes.setBounds(150, 100, 280, 35);

        txtBuscarPaciente.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String filtro = txtBuscarPaciente.getText().toLowerCase();
                comboPacientes.removeAllItems();
                for (Paciente paciente : HospitalData.getPacientes()) {
                    if (paciente.getNombre().toLowerCase().contains(filtro)) {
                        comboPacientes.addItem(paciente);
                    }
                }
            }
        });

        
        JLabel lblBuscarDoctor = new JLabel(" Buscar Doctor:");
        styleLabel(lblBuscarDoctor, labelFont, labelColor);
        lblBuscarDoctor.setBounds(30, 50, 120, 30);

        JTextField txtBuscarDoctor = new JTextField();
        styleTextField(txtBuscarDoctor);
        txtBuscarDoctor.setBounds(150, 50, 280, 35);

        JLabel lblDoctor = new JLabel(" Doctor:");
        styleLabel(lblDoctor, labelFont, labelColor);
        lblDoctor.setBounds(30, 160, 120, 30);

        comboDoctores = new JComboBox<>();
        for (Doctor doctor : HospitalData.getDoctores()) {
            comboDoctores.addItem(doctor);
        }
        styleComboBox(comboDoctores);
        comboDoctores.setBounds(150, 160, 280, 35);

        txtBuscarDoctor.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String filtro = txtBuscarDoctor.getText().toLowerCase();
                comboDoctores.removeAllItems();
                for (Doctor doctor : HospitalData.getDoctores()) {
                    if (doctor.getNombre().toLowerCase().contains(filtro)) {
                        comboDoctores.addItem(doctor);
                    }
                }
            }
        });

        JLabel lblFecha = new JLabel(" Fecha:");
        styleLabel(lblFecha, labelFont, labelColor);
        lblFecha.setBounds(30, 210, 120, 30);

        spinnerFecha = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(spinnerFecha, "yyyy-MM-dd");
        spinnerFecha.setEditor(dateEditor);
        spinnerFecha.setBounds(150, 210, 280, 35);
        styleSpinner(spinnerFecha);

        JLabel lblDiagnostico = new JLabel(" Diagnóstico:");
        styleLabel(lblDiagnostico, labelFont, labelColor);
        lblDiagnostico.setBounds(30, 270, 120, 30);

        txtDiagnostico = new JTextField();
        styleTextField(txtDiagnostico);
        txtDiagnostico.setBounds(150, 270, 280, 35);

        JLabel lblTratamiento = new JLabel(" Tratamiento:");
        styleLabel(lblTratamiento, labelFont, labelColor);
        lblTratamiento.setBounds(30, 330, 120, 30);

        txtTratamiento = new JTextField();
        styleTextField(txtTratamiento);
        txtTratamiento.setBounds(150, 330, 280, 35);

        JButton btnRegistrarConsulta = new JButton(" Registrar Consulta");
        styleButton(btnRegistrarConsulta);
        btnRegistrarConsulta.setBounds(100, 400, 280, 40);

        // Agregar componentes al panel de formulario
        formPanel.add(lblBuscarPaciente);
        formPanel.add(txtBuscarPaciente);
        formPanel.add(lblBuscarDoctor);
        formPanel.add(txtBuscarDoctor);
        formPanel.add(lblPaciente);
        formPanel.add(comboPacientes);
        formPanel.add(lblDoctor);
        formPanel.add(comboDoctores);
        formPanel.add(lblFecha);
        formPanel.add(spinnerFecha);
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

        add(mainPanel);
        setLocationRelativeTo(null);

        btnRegistrarConsulta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Paciente paciente = (Paciente) comboPacientes.getSelectedItem();
                Doctor doctor = (Doctor) comboDoctores.getSelectedItem();
                Date fechaConsulta = (Date) spinnerFecha.getValue();
                String diagnostico = txtDiagnostico.getText();
                String tratamiento = txtTratamiento.getText();

                consultaController.registrarConsulta(new ConsultaMedica(fechaConsulta, diagnostico, tratamiento, paciente));
                
                JOptionPane.showMessageDialog(null, "✅ Consulta registrada exitosamente!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                txtDiagnostico.setText("");
                txtTratamiento.setText("");
                spinnerFecha.setValue(new Date());
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

    private void styleSpinner(JSpinner spinner) {
        spinner.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        spinner.setBorder(BorderFactory.createLineBorder(new Color(135, 206, 250), 2));
        ((JSpinner.DefaultEditor)spinner.getEditor()).getTextField().setBackground(new Color(240, 248, 255));
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