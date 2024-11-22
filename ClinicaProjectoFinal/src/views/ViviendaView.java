package views;

import javax.swing.*;
import controllers.ViviendaController;
import models.Vivienda;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViviendaView extends JFrame {
    private JTextField txtDireccion;
    private JTextField txtCiudad;
    private JTextField txtCodigoPostal;
    private ViviendaController viviendaController;

    public ViviendaView() {
        viviendaController = new ViviendaController();
        setTitle(" Registro de Viviendas ");
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
        JLabel titleLabel = new JLabel("Sistema de Registro de Viviendas");
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
        
        ImageIcon icon = new ImageIcon(getClass().getResource("G.png"));
        Image img = icon.getImage(); // Obtiene la imagen
        Image scaledImg = img.getScaledInstance(200, 200, Image.SCALE_SMOOTH); // Cambia 100, 100 por el tamaño que desees
        ImageIcon scaledIcon = new ImageIcon(scaledImg); // Crea un nuevo ImageIcon con la imagen redimensionada

        JLabel imgLabel = new JLabel(scaledIcon);
        imgLabel.setHorizontalAlignment(JLabel.CENTER); // Asegura que la imagen esté centrada
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

        // Componentes del formulario
        JLabel lblDireccion = new JLabel(" Dirección:");
        styleLabel(lblDireccion, labelFont, labelColor);
        lblDireccion.setBounds(30, 30, 120, 30);

        txtDireccion = new JTextField();
        styleTextField(txtDireccion);
        txtDireccion.setBounds(150, 30, 280, 35);

        JLabel lblCiudad = new JLabel(" Ciudad:");
        styleLabel(lblCiudad, labelFont, labelColor);
        lblCiudad.setBounds(30, 90, 120, 30);

        txtCiudad = new JTextField();
        styleTextField(txtCiudad);
        txtCiudad.setBounds(150, 90, 280, 35);

        JLabel lblCodigoPostal = new JLabel(" Código Postal:");
        styleLabel(lblCodigoPostal, labelFont, labelColor);
        lblCodigoPostal.setBounds(30, 150, 120, 30);

        txtCodigoPostal = new JTextField();
        styleTextField(txtCodigoPostal);
        txtCodigoPostal.setBounds(150, 150, 280, 35);

        JButton btnRegistrar = new JButton(" Registrar Vivienda");
        styleButton(btnRegistrar);
        btnRegistrar.setBounds(100, 220, 280, 40);

        // Agregar componentes al panel de formulario
        formPanel.add(lblDireccion);
        formPanel.add(txtDireccion);
        formPanel.add(lblCiudad);
        formPanel.add(txtCiudad);
        formPanel.add(lblCodigoPostal);
        formPanel.add(txtCodigoPostal);
        formPanel.add(btnRegistrar);

        mainPanel.add(formPanel);

        // Footer decorativo
        JLabel footerLabel = new JLabel("© 2024 Sistema de Registro de Viviendas");
        footerLabel.setFont(new Font("Segoe UI", Font.ITALIC, 12));
        footerLabel.setForeground(new Color(100, 100, 100));
        footerLabel.setBounds(50, 640, 250, 20);
        mainPanel.add(footerLabel);

        add(mainPanel);
        setLocationRelativeTo(null);

        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String direccion = txtDireccion.getText();
                String ciudad = txtCiudad.getText();
                String codigoPostal = txtCodigoPostal.getText();
                viviendaController.registrarVivienda(new Vivienda(direccion, ciudad, codigoPostal));
                JOptionPane.showMessageDialog(null, "✅ Vivienda registrada exitosamente!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                txtDireccion.setText("");
                txtCiudad.setText("");
                txtCodigoPostal.setText("");
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