package views;

import javax.swing.*;
import controllers.PacienteController;
import controllers.ViviendaController;
import models.Paciente;
import models.Vivienda;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PacienteView extends JFrame {
    private JTextField txtNombre;
    private JTextField txtEdad;
    private JTextField txtDNI;
    private JTextField txtCedula;
    private JComboBox<String> comboTipoSangre;
    private JComboBox<String> comboGenero;
    private JComboBox<Vivienda> comboViviendas;
    private PacienteController pacienteController;
    private ViviendaController viviendaController;

    public PacienteView() {
        pacienteController = new PacienteController();
        viviendaController = new ViviendaController();
        setTitle("✨ Registro de Pacientes ✨");
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
                
                
                g2d.setColor(new Color(255, 255, 255, 50));
                g2d.fillOval(-50, -50, 200, 200);
                g2d.fillOval(w-150, h-150, 200, 200);
            }
        };
        mainPanel.setLayout(null);

        
        JLabel titleLabel = new JLabel("Registro de Pacientes");
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

        ImageIcon icon = new ImageIcon(getClass().getResource("E.jpeg"));
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

        // Estilo común para etiquetas
        Font labelFont = new Font("Segoe UI", Font.BOLD, 14);
        Color labelColor = new Color(41, 84, 144);

        // Componentes del formulario con iconos
        addFormField(formPanel, "👤 Nombre:", txtNombre = new JTextField(), 30, labelFont, labelColor);
        addFormField(formPanel, "📅 Edad:", txtEdad = new JTextField(), 90, labelFont, labelColor);
        addFormField(formPanel, "🆔 DNI:", txtDNI = new JTextField(), 150, labelFont, labelColor);
        addFormField(formPanel, "📝 Cédula:", txtCedula = new JTextField(), 210, labelFont, labelColor);

        // Combos
        addComboField(formPanel, "💉 Tipo de Sangre:", 
            comboTipoSangre = new JComboBox<>(new String[]{"A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-"}), 
            270, labelFont, labelColor);

        addComboField(formPanel, "⚧ Género:", 
            comboGenero = new JComboBox<>(new String[]{"Masculino", "Femenino", "Otro"}), 
            330, labelFont, labelColor);

        addComboField(formPanel, "🏠 Vivienda:", 
            comboViviendas = new JComboBox<>(), 
            390, labelFont, labelColor);

        // Poblar combo de viviendas
        for (Vivienda vivienda : viviendaController.listarViviendas()) {
            comboViviendas.addItem(vivienda);
        }

        // Botón de registro
        JButton btnRegistrar = new JButton("📋 Registrar Paciente");
        styleButton(btnRegistrar);
        btnRegistrar.setBounds(100, 470, 280, 40);
        formPanel.add(btnRegistrar);

        mainPanel.add(formPanel);

        // Footer
        JLabel footerLabel = new JLabel("© 2024 Clínica Médica");
        footerLabel.setFont(new Font("Segoe UI", Font.ITALIC, 12));
        footerLabel.setForeground(new Color(100, 100, 100));
        footerLabel.setBounds(50, 640, 200, 20);
        mainPanel.add(footerLabel);

        add(mainPanel);
        setLocationRelativeTo(null);

        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String nombre = txtNombre.getText();
                    int edad = Integer.parseInt(txtEdad.getText());
                    String dni = txtDNI.getText();
                    String cedula = txtCedula.getText();
                    String tipoSangre = (String) comboTipoSangre.getSelectedItem();
                    String genero = (String) comboGenero.getSelectedItem();
                    Vivienda vivienda = (Vivienda) comboViviendas.getSelectedItem();

                    pacienteController.registrarPaciente(
                        new Paciente(nombre, edad, dni, tipoSangre, genero, cedula, vivienda)
                    );
                    JOptionPane.showMessageDialog(null, "✅ Paciente registrado exitosamente!", "Éxito", JOptionPane.INFORMATION_MESSAGE);

                    // Limpiar campos
                    txtNombre.setText("");
                    txtEdad.setText("");
                    txtDNI.setText("");
                    txtCedula.setText("");
                    comboTipoSangre.setSelectedIndex(0);
                    comboGenero.setSelectedIndex(0);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "❌ Por favor ingrese un valor numérico válido para la edad.", 
                        "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private void addFormField(JPanel panel, String labelText, JTextField textField, 
            int y, Font labelFont, Color labelColor) {
        JLabel label = new JLabel(labelText);
        styleLabel(label, labelFont, labelColor);
        label.setBounds(30, y, 120, 30);
        panel.add(label);

        styleTextField(textField);
        textField.setBounds(150, y, 280, 35);
        panel.add(textField);
    }

    private void addComboField(JPanel panel, String labelText, JComboBox<?> comboBox, 
            int y, Font labelFont, Color labelColor) {
        JLabel label = new JLabel(labelText);
        styleLabel(label, labelFont, labelColor);
        label.setBounds(30, y, 120, 30);
        panel.add(label);

        styleComboBox(comboBox);
        comboBox.setBounds(150, y, 280, 35);
        panel.add(comboBox);
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