package views;

import javax.swing.*;
import controllers.VacunacionController;
import controllers.PacienteController;
import models.Vacunacion;
import models.Paciente;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class VacunacionView extends JFrame {
    private JTextField txtVacuna;
    private JTextField searchBar;
    private JCheckBox chkCompletada;
    private JComboBox<Paciente> comboPacientes;
    private VacunacionController vacunacionController;
    private PacienteController pacienteController;

    public VacunacionView() {
        vacunacionController = new VacunacionController();
        pacienteController = new PacienteController();
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

        // Componentes del formulario
        JLabel lblPaciente = new JLabel(" Paciente:");
        styleLabel(lblPaciente, labelFont, labelColor);
        lblPaciente.setBounds(30, 30, 120, 30);
        
        // Agregar SearchBar
        searchBar = new JTextField();
        styleTextField(searchBar);
        searchBar.setBounds(150, 30, 280, 35);
        formPanel.add(searchBar);
        
        comboPacientes = new JComboBox<>();
        for (Paciente paciente : pacienteController.listarPacientes()) {
            comboPacientes.addItem(paciente);
        }
        styleComboBox(comboPacientes);
        comboPacientes.setBounds(150, 75, 280, 35);

        JLabel lblVacuna = new JLabel(" Vacuna:");
        styleLabel(lblVacuna, labelFont, labelColor);
        lblVacuna.setBounds(30, 130, 120, 30);

        txtVacuna = new JTextField();
        styleTextField(txtVacuna);
        txtVacuna.setBounds(150, 130, 280, 35);

        JLabel lblCompletada = new JLabel(" Completada:");
        styleLabel(lblCompletada, labelFont, labelColor);
        lblCompletada.setBounds(30, 190, 120, 30);

        chkCompletada = new JCheckBox();
        chkCompletada.setBounds(150, 190, 35, 35);
        chkCompletada.setOpaque(false);
        
        JButton btnRegistrarVacuna = new JButton(" Registrar Vacuna");
        styleButton(btnRegistrarVacuna);
        btnRegistrarVacuna.setBounds(100, 260, 280, 40);

        // Agregar componentes al panel de formulario
        formPanel.add(lblPaciente);
        formPanel.add(comboPacientes);
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

        // Funcionalidad de búsqueda
        searchBar.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String searchText = searchBar.getText().toLowerCase();
                comboPacientes.removeAllItems();
                for (Paciente paciente : pacienteController.listarPacientes()) {
                    if (paciente.toString().toLowerCase().contains(searchText)) {
                        comboPacientes.addItem(paciente);
                    }
                }
                if (comboPacientes.getItemCount() > 0) {
                    comboPacientes.setSelectedIndex(0);
                }
            }
        });

        btnRegistrarVacuna.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Paciente paciente = (Paciente) comboPacientes.getSelectedItem();
                String vacuna = txtVacuna.getText();
                boolean completada = chkCompletada.isSelected();
                vacunacionController.registrarVacunacion(new Vacunacion(vacuna, completada, paciente));
                JOptionPane.showMessageDialog(null, " Vacunación registrada exitosamente!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                txtVacuna.setText("");
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