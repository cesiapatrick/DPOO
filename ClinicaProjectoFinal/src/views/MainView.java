package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.border.*;
import controllers.HospitalData;
import models.ConsultaMedica;
import models.Enfermedad;
import models.Paciente;
import models.Vacunacion;
import models.Vivienda;

import java.io.*;

public class MainView extends JFrame {
	
    private HospitalData hospitalData = HospitalData.getInstance();
    
    private final Color PRIMARY_COLOR = new Color(41, 128, 185);
    private final Color SECONDARY_COLOR = new Color(52, 152, 219);
    private final Color BACKGROUND_START = new Color(236, 240, 241);
    private final Color BACKGROUND_END = new Color(250, 252, 253);
    private final Color BUTTON_HOVER = new Color(25, 111, 168);
    private final Color CARD_BACKGROUND = new Color(255, 255, 255, 240);

    public MainView() {
    	
        
        setTitle(" Sistema de Gestión de Clínica Médica");
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);

        
        JPanel mainPanel = createMainPanel();
        setContentPane(mainPanel);

        
        JPanel contentArea = createContentArea();
        mainPanel.add(contentArea, BorderLayout.CENTER);

        
        JPanel sidePanel = createSidePanel();
        mainPanel.add(sidePanel, BorderLayout.WEST);

        
        JPanel topBar = createTopBar();
        mainPanel.add(topBar, BorderLayout.NORTH);

        
        JPanel footer = createFooter();
        mainPanel.add(footer, BorderLayout.SOUTH);
        
      //Al cerrar ventana Principal - GuardarDatos
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
            	int opcion = JOptionPane.showConfirmDialog(MainView.this, "¿Estás seguro de que quieres salir?", "Confirmar salida", JOptionPane.YES_NO_OPTION);
                
                if (opcion == JOptionPane.YES_OPTION) {
                	hospitalData.guardarDatosArchivo();
                    dispose();
                } else {
                	return;
                }
                
            }
        });
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
        
        
        JLabel logoLabel = new JLabel("", JLabel.CENTER);
        try {
            
            ImageIcon icon = new ImageIcon(getClass().getResource("D.jpeg"));
            
            if (icon.getImageLoadStatus() == MediaTracker.ERRORED) {
                System.out.println("No se pudo cargar la imagen A.png.");
            } else {
                
                Image img = icon.getImage();
                Image scaledImg = img.getScaledInstance(300, 250, Image.SCALE_SMOOTH);
                ImageIcon scaledIcon = new ImageIcon(scaledImg);
                
                logoLabel.setIcon(scaledIcon);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
        JLabel welcomeLabel = new JLabel("Bienvenido al Sistema de Gestión", JLabel.CENTER);
        welcomeLabel.setFont(new Font("Segoe UI", Font.BOLD, 32));
        welcomeLabel.setForeground(PRIMARY_COLOR);
        
        
        JLabel subtitleLabel = new JLabel("Seleccione una opción del menú para comenzar", JLabel.CENTER);
        subtitleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        subtitleLabel.setForeground(Color.GRAY);

        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 10, 10, 10);

        panel.add(logoLabel, gbc);
        panel.add(welcomeLabel, gbc);
        panel.add(subtitleLabel, gbc);

        return panel;
    }

    private JPanel createSidePanel() {
        JPanel panel = new JPanel(new GridBagLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                g2d.setColor(new Color(255, 255, 255, 230));
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
            }
        };
        panel.setPreferredSize(new Dimension(300, 0));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 0, 5, 0);

        
        String[][] menuItems = {
            //{" Registro de Viviendas", "vivienda"},
            {" Registro de Pacientes", "paciente"},
            {" Crear Doctor", "doctor"},
            {" Gestión de Consultas", "consulta"},
            {" Control de Enfermedades", "enfermedad"},
            {" Control de Vacunación", "vacunacion"},
            {" Historias Clínicas", "historia"},
            {" Generar Reporte", "reporte"},
            {" Exportar Datos", "exportar"},
            {" Importar Datos", "importar"}
        };

        for (String[] item : menuItems) {
            panel.add(createMenuButton(item[0], item[1]), gbc);
        }

        return panel;
    }

    private JButton createMenuButton(String text, String name) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

                GradientPaint gp = new GradientPaint(
                    0, 0, getModel().isPressed() ? BUTTON_HOVER : PRIMARY_COLOR,
                    0, getHeight(), getModel().isPressed() ? BUTTON_HOVER.darker() : SECONDARY_COLOR
                );

                g2d.setPaint(gp);
                g2d.fill(new RoundRectangle2D.Double(0, 0, getWidth() - 1, getHeight() - 1, 15, 15));

                FontMetrics fm = g2d.getFontMetrics();
                Rectangle2D r = fm.getStringBounds(text, g2d);
                int x = (getWidth() - (int) r.getWidth()) / 2;
                int y = (getHeight() - (int) r.getHeight()) / 2 + fm.getAscent();

                g2d.setColor(Color.WHITE);
                g2d.drawString(text, x, y);
            }
        };

        button.setName(name);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setForeground(Color.WHITE);
        button.setPreferredSize(new Dimension(250, 45));
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(BUTTON_HOVER);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(PRIMARY_COLOR);
            }
        });

        button.addActionListener(e -> handleButtonClick(name));
        return button;
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

    private void handleButtonClick(String buttonName) {
        switch (buttonName) {
            case "vivienda":
                //new ViviendaView(null).setVisible(true);
                break;
            case "paciente":
                new PacienteView().setVisible(true);
                break;
            case "doctor":
                new CrearDoctorView().setVisible(true);
                break;
            case "consulta":
                new ConsultaView().setVisible(true);
                break;
            case "enfermedad":
                new EnfermedadView().setVisible(true);
                break;
            case "vacunacion":
                new VacunacionView().setVisible(true);
                break;
            case "historia":
                new HistoriaClinicaView().setVisible(true);
                break;
            case "reporte":
                generarReporte();
                break;
            case "exportar":
                exportarDatos();
                break;
            case "importar":
                importarDatos();
                break;
        }
    }

    private void generarReporte() {
        StringBuilder reporte = new StringBuilder();
        reporte.append("===== Reporte del Sistema de Gestión de Clínica Médica =====\n\n");
        
        /*
        // Viviendas
        reporte.append("Viviendas:\n");
        for (Vivienda vivienda : viviendaController.listarViviendas()) {
            reporte.append("- ").append(vivienda.getDireccion()).append(", ").append(vivienda.getCiudad()).append(", ").append(vivienda.getCodigoPostal()).append("\n");
        }
        */

        // Pacientes
        reporte.append("\nPacientes:\n");
        for (Paciente paciente : hospitalData.getPacientes()) {
            reporte.append("- ").append(paciente.getNombre()).append(", ID: ").append(paciente.getId()).append("\n");
        }

        // Consultas Médicas
        reporte.append("\nConsultas Médicas:\n");
        for (ConsultaMedica consulta :  hospitalData.getConsultasMedicas()) {
            reporte.append("- Paciente: ").append(consulta.getPaciente().getNombre()).append(", Diagnóstico: ").append(consulta.getDiagnostico()).append("\n");
        }

        // Enfermedades
        reporte.append("\nEnfermedades:\n");
        for (Enfermedad enfermedad :  hospitalData.getEnfermedades()) {
            reporte.append("- ").append(enfermedad.getNombre()).append(", En Vigilancia: ").append(enfermedad.isEnVigilancia() ? "Sí" : "No").append("\n");
        }

        // Vacunaciones
        reporte.append("\nVacunación:\n");
        for (Vacunacion vacunacion :  hospitalData.getVacunas()) {
            reporte.append("- Paciente: ").append(vacunacion.getPaciente().getNombre()).append(", Vacuna: ").append(vacunacion.getVacuna()).append(", Completada: ").append(vacunacion.isCompletada() ? "Sí" : "No").append("\n");
        }

        JOptionPane.showMessageDialog(this, reporte.toString(), "Reporte del Sistema", JOptionPane.INFORMATION_MESSAGE);
    }

    private void exportarDatos() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Guardar Datos");
        fileChooser.setSelectedFile(new File("datos_clinica.csv"));

        if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            try (FileWriter writer = new FileWriter(fileChooser.getSelectedFile())) {
                JOptionPane.showMessageDialog(this, 
                    "Datos exportados exitosamente a '" + fileChooser.getSelectedFile().getName() + "'",
                    "Exportación Completa", 
                    JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, 
                    "Error al exportar los datos: " + e.getMessage(),
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void importarDatos() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Importar Datos");
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("CSV Files", "csv"));

        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            JOptionPane.showMessageDialog(this,
                "Importación de datos completada con éxito",
                "Importación Exitosa",
                JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        SwingUtilities.invokeLater(() -> {
            new MainView().setVisible(true);
        });
    }
    
    
}