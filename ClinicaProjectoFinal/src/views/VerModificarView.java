package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.RenderingHints;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import controllers.HospitalData;
import models.ConsultaMedica;
import models.Doctor;
import models.Paciente;

public class VerModificarView extends JFrame {

    private HospitalData hospitalData = HospitalData.getInstance();

    private final Color PRIMARY_COLOR = new Color(41, 128, 185);
    private final Color SECONDARY_COLOR = new Color(52, 152, 219);
    private final Color BACKGROUND_START = new Color(236, 240, 241);
    private final Color BACKGROUND_END = new Color(250, 252, 253);
    private final Color CARD_BACKGROUND = new Color(255, 255, 255, 240);

    
    private JTable pacienteTable;
    private JTable doctorTable;
    private JTable consultaTable;

    public VerModificarView() {
        setTitle("Ver y Modificar Datos");
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        

        JPanel mainPanel = createMainPanel();
        setContentPane(mainPanel);

        JPanel contentArea = createContentArea();
        mainPanel.add(contentArea, BorderLayout.CENTER);

        JPanel topBar = createTopBar();
        mainPanel.add(topBar, BorderLayout.NORTH);

        JPanel footer = createFooter();
        mainPanel.add(footer, BorderLayout.SOUTH);
    }

    private JPanel createMainPanel() {
        JPanel panel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                int w = getWidth();
                int h = getHeight();
                GradientPaint gp = new GradientPaint(0, 0, BACKGROUND_START, 0, h, BACKGROUND_END);
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, w, h);
            }
        };
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        return panel;
    }

    private JPanel createContentArea() {
        JPanel panel = new JPanel(new GridBagLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                g2d.setColor(CARD_BACKGROUND);
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
            }
        };

        JLabel welcomeLabel = new JLabel("Panel de Modificación y Eliminación de Datos", JLabel.CENTER);
        welcomeLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
        welcomeLabel.setForeground(PRIMARY_COLOR);

        JLabel subtitleLabel = new JLabel("Seleccione una opción del menú para comenzar", JLabel.CENTER);
        subtitleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        subtitleLabel.setForeground(Color.GRAY);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.WEST; 
        gbc.insets = new Insets(10, 30, 10, 10);

        
        JPanel titlePanel = new JPanel(new GridBagLayout());
        titlePanel.add(welcomeLabel, gbc);

        
        gbc.insets = new Insets(10, 50, 10, 10);
        titlePanel.add(subtitleLabel, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        panel.add(titlePanel, gbc);

        //Tablas
        JPanel tablePanel = new JPanel(new GridBagLayout());
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        tablePanel.add(new JScrollPane(createPacienteTable()), gbc);
        tablePanel.add(new JScrollPane(createDoctorTable()), gbc);
        tablePanel.add(new JScrollPane(createConsultaTable()), gbc);

        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        panel.add(tablePanel, gbc);

        // botones
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton botonMofidicar = new JButton("Modificar");
        JButton botonBorrar = new JButton("Eliminar");

        botonBorrar.addActionListener(e -> eliminarSelectedItem());
        botonMofidicar.addActionListener(e -> enviarSelectedItem());
        

        buttonPanel.add(botonMofidicar);
        buttonPanel.add(botonBorrar);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weighty = 0;
        panel.add(buttonPanel, gbc);

        return panel;
    }


    private JPanel createTopBar() {
        JPanel panel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                GradientPaint gp = new GradientPaint(
                        0, 0, PRIMARY_COLOR,
                        getWidth(), 0, SECONDARY_COLOR
                );
                g2d.setPaint(gp);
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
            }
        };
        panel.setPreferredSize(new Dimension(0, 60));

        JLabel titleLabel = new JLabel("Sistema de Gestión Médica");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));

        panel.add(titleLabel, BorderLayout.CENTER);
        return panel;
    }

    private JPanel createFooter() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel.setOpaque(false);

        JLabel footerLabel = new JLabel("© 2024 Clínica Médica - Todos los derechos reservados");
        footerLabel.setFont(new Font("Segoe UI", Font.ITALIC, 12));
        footerLabel.setForeground(Color.GRAY);

        panel.add(footerLabel);
        return panel;
    }

    private JScrollPane createPacienteTable() {
        String[] columns = {"ID", "Nombre", "Edad", "Ciudad", "Tipo Sangre"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);

        for (Paciente paciente : hospitalData.getPacientes()) {
            model.addRow(new Object[]{paciente.getId(), paciente.getNombre(), paciente.getEdad(), paciente.getVivienda().getCiudad(), paciente.getTipoSangre()});
        }

        pacienteTable = new JTable(model);
        styleTable(pacienteTable);

        JScrollPane scrollPane = new JScrollPane(pacienteTable);
        scrollPane.setViewportView(pacienteTable); 
        scrollPane.setColumnHeaderView(pacienteTable.getTableHeader()); 
        return scrollPane;
    }

    private JScrollPane createDoctorTable() {
        String[] columns = {"ID", "Nombre", "Apellido", "Especialidad"};
        
        DefaultTableModel model = new DefaultTableModel(columns, 0);

        for (Doctor doctor : hospitalData.getDoctores()) {
            model.addRow(new Object[]{doctor.getId(), doctor.getNombre(), doctor.getApellido(), doctor.getEspecialidad()});
        }

        doctorTable = new JTable(model);
        styleTable(doctorTable);

        JScrollPane scrollPane = new JScrollPane(doctorTable);
        scrollPane.setViewportView(doctorTable);
        scrollPane.setColumnHeaderView(doctorTable.getTableHeader());
        return scrollPane;
    }

    private JScrollPane createConsultaTable() {
        String[] columns = {"Paciente", "Doctor", "Fecha", "Diagnóstico"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);

        for (ConsultaMedica consulta : hospitalData.getConsultasMedicas()) {
            model.addRow(new Object[]{consulta.getPaciente().getNombre(), consulta.getDoctor().getNombre(), consulta.getFecha(), consulta.getDiagnostico()});
        }

        consultaTable = new JTable(model);
        styleTable(consultaTable);

        JScrollPane scrollPane = new JScrollPane(consultaTable);
        scrollPane.setViewportView(consultaTable); 
        scrollPane.setColumnHeaderView(consultaTable.getTableHeader());
        return scrollPane;
    }




    private void styleTable(JTable table) {
        table.setFillsViewportHeight(true);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        table.setRowHeight(30);
        table.setSelectionBackground(PRIMARY_COLOR);
        table.setSelectionForeground(Color.WHITE);
        table.setGridColor(Color.LIGHT_GRAY);
        table.setBorder(BorderFactory.createLineBorder(PRIMARY_COLOR));
        table.setDefaultEditor(Object.class, null);
    }

    private void eliminarSelectedItem() {
        int selectedRow = -1;

        if (pacienteTable.getSelectedRow() != -1) {
            selectedRow = pacienteTable.getSelectedRow();
            DefaultTableModel model = (DefaultTableModel) pacienteTable.getModel();
            Paciente paciente = hospitalData.getPacientes().get(selectedRow);
            hospitalData.eliminarPaciente(paciente);
            model.removeRow(selectedRow);
        } else if (doctorTable.getSelectedRow() != -1) {
            selectedRow = doctorTable.getSelectedRow();
            DefaultTableModel model = (DefaultTableModel) doctorTable.getModel();
            Doctor doctor = hospitalData.getDoctores().get(selectedRow);
            hospitalData.eliminarDoctor(doctor);
            model.removeRow(selectedRow);
        } else if (consultaTable.getSelectedRow() != -1) {
            selectedRow = consultaTable.getSelectedRow();
            DefaultTableModel model = (DefaultTableModel) consultaTable.getModel();
            ConsultaMedica consulta = hospitalData.getConsultasMedicas().get(selectedRow);
            hospitalData.eliminarConsulta(consulta);
            model.removeRow(selectedRow);
        }
    }
    
    //Envia los objetos a los Views para ser modificados
    private void enviarSelectedItem() {
    	int selectedRow = -1;

        if (pacienteTable.getSelectedRow() != -1) {
            selectedRow = pacienteTable.getSelectedRow();
            DefaultTableModel model = (DefaultTableModel) pacienteTable.getModel();
            Paciente paciente = hospitalData.getPacientes().get(selectedRow);
            new PacienteView(paciente).setVisible(true);
            dispose();
            
        } else if (doctorTable.getSelectedRow() != -1) {
            selectedRow = doctorTable.getSelectedRow();
            DefaultTableModel model = (DefaultTableModel) doctorTable.getModel();
            Doctor doctor = hospitalData.getDoctores().get(selectedRow);
            new CrearDoctorView(doctor).setVisible(true);
            dispose();
            
            
        } else if (consultaTable.getSelectedRow() != -1) {
            selectedRow = consultaTable.getSelectedRow();
            DefaultTableModel model = (DefaultTableModel) consultaTable.getModel();
            ConsultaMedica consulta = hospitalData.getConsultasMedicas().get(selectedRow);
            new ConsultaView(consulta).setVisible(true);
            dispose();
        }
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            new VerModificarView().setVisible(true);
        });
    }
}
