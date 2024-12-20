package views;

import javax.swing.*;
import controllers.HospitalData;
import models.Doctor;
import models.Paciente;
import models.Usuario;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.FileSystemNotFoundException;
import java.util.ArrayList;

public class AdminLoginView extends JFrame {
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JComboBox<String> comboRol;
    //private DoctorController doctorController;
    private HospitalData hospitalData = HospitalData.getInstance();
    private static final String ADMIN_USERNAME = "admin";
    private static final String ADMIN_PASSWORD = "admin123";
    private Usuario usuario = null;
    
    public AdminLoginView() {
    	
        setTitle(" Sistema de Acceso Médico ");
        setSize(900, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        
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

        
        JLabel titleLabel = new JLabel("Bienvenido al Sistema Médico");
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
        
        ImageIcon icon = new ImageIcon(getClass().getResource("A.jpg"));
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

        
        addFormField(formPanel, " Usuario:", txtUsername = new JTextField(), 30);
        addFormField(formPanel, " Contraseña:", txtPassword = new JPasswordField(), 90);

        JLabel lblRol = new JLabel(" Rol:");
        styleLabel(lblRol);
        lblRol.setBounds(30, 150, 120, 30);
        formPanel.add(lblRol);

        comboRol = new JComboBox<String>();
        comboRol.addItem("Doctor");
        comboRol.addItem("Administrador");
        styleComboBox(comboRol);
        comboRol.setBounds(150, 150, 280, 35);
        formPanel.add(comboRol);
        
        JButton btnLogin = new JButton(" Iniciar Sesión");
        styleButton(btnLogin);
        btnLogin.setBounds(50, 410, 180, 40);
        formPanel.add(btnLogin);

        JButton btnRegister = new JButton(" Registrar");
        styleButton(btnRegister);
        btnRegister.setBounds(250, 410, 180, 40);
        formPanel.add(btnRegister);

        mainPanel.add(formPanel);

        
        JLabel footerLabel = new JLabel("© 2024 Clínica Médica");
        footerLabel.setFont(new Font("Segoe UI", Font.ITALIC, 12));
        footerLabel.setForeground(new Color(100, 100, 100));
        footerLabel.setBounds(50, 640, 200, 20);
        mainPanel.add(footerLabel);

        add(mainPanel);
        setLocationRelativeTo(null);

        
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = txtUsername.getText();
                String password = new String(txtPassword.getPassword());

                if (comboRol.getSelectedItem().equals("Administrador")) {
                    if (username.equals(ADMIN_USERNAME) && password.equals(ADMIN_PASSWORD)) {
                        JOptionPane.showMessageDialog(null, " Inicio de sesión de administrador exitoso");
                        hospitalData.setUsuarioActivo("Administrador");
                        new MainView().setVisible(true);
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, " Credenciales de administrador incorrectas", 
                            "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    if (hospitalData.autenticarInicioSesion(username, password)) {
                        JOptionPane.showMessageDialog(null, " Inicio de sesión de doctor exitoso");
                        hospitalData.setUsuarioActivo("Doctor");
                        new MainView().setVisible(true);
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, " Credenciales de doctor incorrectas", 
                            "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        btnRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = txtUsername.getText();
                String password = new String(txtPassword.getPassword());
                String tipo = comboRol.getSelectedItem().toString();
               
                
                if (username.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Por favor complete todos los campos.", 
                        "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                if (comboRol.getSelectedItem().equals("Doctor")) {

                    if (hospitalData.autenticarRegistro(username)) {
                        JOptionPane.showMessageDialog(null, "Doctor registrado exitosamente");
                        
                        usuario = new Usuario(username, password, tipo);
                        hospitalData.addUsuario(usuario);
                        hospitalData.setUsuarioActivo("Doctor");
                        
                        new MainView().setVisible(true);
                        dispose();
                        
                    } else {
                        JOptionPane.showMessageDialog(null, "El usuario ya existe", 
                            "Error", JOptionPane.ERROR_MESSAGE);
                        limpiarCampos();
                    }
                }  else {
                	JOptionPane.showMessageDialog(null, "Administrador registrado exitosamente");
                    
                    usuario = new Usuario(username, password, tipo);
                    hospitalData.addUsuario(usuario);
                    hospitalData.setUsuarioActivo("Administrador");
                    
                    new MainView().setVisible(true);
                    dispose();
                }
            }
        });

        comboRol.setSelectedItem("Doctor");
    }

    private void addFormField(JPanel panel, String labelText, JTextField field, int y) {
        JLabel label = new JLabel(labelText);
        styleLabel(label);
        label.setBounds(30, y, 120, 30);
        
        field.setBounds(150, y, 280, 35);
        styleTextField(field);
        
        panel.add(label);
        panel.add(field);
    }

    private void styleLabel(JLabel label) {
        label.setFont(new Font("Segoe UI", Font.BOLD, 14));
        label.setForeground(new Color(41, 84, 144));
    }

    private void styleTextField(JTextField field) {
        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        field.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(135, 206, 250), 2),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        field.setBackground(new Color(240, 248, 255));
    }

    private void styleComboBox(JComboBox<String> comboBox) {
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
        txtUsername.setText("");
        txtPassword.setText("");
    }
}