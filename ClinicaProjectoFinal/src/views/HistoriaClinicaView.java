package views;

import javax.swing.*;
import controllers.HistoriaClinicaController;
import controllers.PacienteController;
import models.HistoriaClinica;
import models.Paciente;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class HistoriaClinicaView extends JFrame {
    private JComboBox<Paciente> comboPacientes;
    private JTextField searchBar;
    private JTextArea txtResumenHistoria;
    private HistoriaClinicaController historiaClinicaController;
    private PacienteController pacienteController;

    public HistoriaClinicaView() {
        historiaClinicaController = new HistoriaClinicaController();
        pacienteController = new PacienteController();
        setTitle(" Gestión de Historias Clínicas ");
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

        
        JLabel titleLabel = new JLabel("Historia Clínica del Paciente");
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

        ImageIcon icon = new ImageIcon(getClass().getResource("H.png"));
        Image img = icon.getImage();
        Image scaledImg = img.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImg);

        JLabel imgLabel = new JLabel(scaledIcon);
        imgLabel.setHorizontalAlignment(JLabel.CENTER);
        imagePanel.add(imgLabel);
        mainPanel.add(imagePanel);

        
        JPanel contentPanel = new JPanel(null) {
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
        contentPanel.setBounds(50, 80, 480, 550);
        contentPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createEmptyBorder(20, 20, 20, 20),
            BorderFactory.createLineBorder(new Color(255, 255, 255, 200), 3)
        ));

        
        Font labelFont = new Font("Segoe UI", Font.BOLD, 14);
        Color labelColor = new Color(41, 84, 144);

        
        JLabel lblPaciente = new JLabel(" Seleccione Paciente:");
        styleLabel(lblPaciente, labelFont, labelColor);
        lblPaciente.setBounds(30, 30, 180, 30);
        contentPanel.add(lblPaciente);

        
        searchBar = new JTextField();
        searchBar.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        searchBar.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(135, 206, 250), 2),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        searchBar.setBounds(30, 70, 420, 35);
        searchBar.setBackground(new Color(240, 248, 255));
        contentPanel.add(searchBar);

        comboPacientes = new JComboBox<>();
        for (Paciente paciente : pacienteController.listarPacientes()) {
            comboPacientes.addItem(paciente);
        }
        styleComboBox(comboPacientes);
        comboPacientes.setBounds(30, 115, 420, 35);
        contentPanel.add(comboPacientes);

        
        JButton btnVerResumen = new JButton(" Ver Historia Clínica");
        styleButton(btnVerResumen);
        btnVerResumen.setBounds(30, 160, 420, 40);
        contentPanel.add(btnVerResumen);

        
        JLabel lblResumen = new JLabel(" Resumen de Historia Clínica:");
        styleLabel(lblResumen, labelFont, labelColor);
        lblResumen.setBounds(30, 210, 420, 30);
        contentPanel.add(lblResumen);

        txtResumenHistoria = new JTextArea();
        txtResumenHistoria.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtResumenHistoria.setLineWrap(true);
        txtResumenHistoria.setWrapStyleWord(true);
        txtResumenHistoria.setEditable(false);
        txtResumenHistoria.setBackground(new Color(240, 248, 255));
        txtResumenHistoria.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(135, 206, 250), 2),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        JScrollPane scrollPane = new JScrollPane(txtResumenHistoria);
        scrollPane.setBounds(30, 250, 420, 270);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(135, 206, 250), 2));
        contentPanel.add(scrollPane);

        mainPanel.add(contentPanel);

        
        JLabel footerLabel = new JLabel("© 2024 Clínica Médica");
        footerLabel.setFont(new Font("Segoe UI", Font.ITALIC, 12));
        footerLabel.setForeground(new Color(100, 100, 100));
        footerLabel.setBounds(50, 640, 200, 20);
        mainPanel.add(footerLabel);

        add(mainPanel);
        setLocationRelativeTo(null);

        
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

        
        btnVerResumen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Paciente paciente = (Paciente) comboPacientes.getSelectedItem();
                
                if (paciente == null) {
                    JOptionPane.showMessageDialog(
                        HistoriaClinicaView.this, 
                        "Error: No hay paciente seleccionado.", 
                        "Selecciona un paciente", 
                        JOptionPane.ERROR_MESSAGE
                    );
                } else {
                    HistoriaClinica historia = historiaClinicaController.obtenerHistoria(paciente.getDni());
                    if (historia != null) {
                        txtResumenHistoria.setText(historia.generarResumen());
                    } else {
                        txtResumenHistoria.setText("❌ No hay historia clínica registrada para este paciente.");
                    }
                }
            }
        });
    }

    private void styleLabel(JLabel label, Font font, Color color) {
        label.setFont(font);
        label.setForeground(color);
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
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder());
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
}