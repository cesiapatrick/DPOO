package views;

import javax.swing.*;
import controllers.EnfermedadController;
import models.Enfermedad;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class EnfermedadView extends JFrame {
    private JTextField txtNombre;
    private JCheckBox chkVigilancia;
    private JTextField txtBuscar;
    private EnfermedadController enfermedadController;

    public EnfermedadView() {
        enfermedadController = new EnfermedadController();
        setTitle(" Control de Enfermedades");
        setSize(700, 500);
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
                    0, 0, new Color(176, 224, 230),
                    w, h, new Color(135, 206, 250)
                );
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, w, h);
                
                
                g2d.setColor(new Color(255, 255, 255, 50));
                g2d.fillOval(-50, -50, 200, 200);
                g2d.fillOval(w-150, h-150, 200, 200);
            }
        };
        mainPanel.setLayout(null);

        
        JLabel titleLabel = new JLabel("Registro de Enfermedades");
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
        imagePanel.setBounds(400, 80, 250, 250);
        imagePanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createEmptyBorder(10, 10, 10, 10),
            BorderFactory.createLineBorder(new Color(255, 255, 255, 200), 3)
        ));
        
        ImageIcon icon = new ImageIcon(getClass().getResource("C.jpg")); 
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
        formPanel.setBounds(50, 80, 330, 350);
        formPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createEmptyBorder(20, 20, 20, 20),
            BorderFactory.createLineBorder(new Color(255, 255, 255, 200), 3)
        ));

        
        Font labelFont = new Font("Segoe UI", Font.BOLD, 14);
        Color labelColor = new Color(41, 84, 144);

        
        JLabel lblBuscar = new JLabel(" Buscar Enfermedad:");
        styleLabel(lblBuscar, labelFont, labelColor);
        lblBuscar.setBounds(20, 160, 290, 30);

        txtBuscar = new JTextField();
        styleTextField(txtBuscar);
        txtBuscar.setBounds(20, 190, 290, 35);

        txtBuscar.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String filtro = txtBuscar.getText().toLowerCase();

            }
        });

        JLabel lblNombre = new JLabel(" Nombre de la Enfermedad:");
        styleLabel(lblNombre, labelFont, labelColor);
        lblNombre.setBounds(20, 30, 290, 30);

        txtNombre = new JTextField();
        styleTextField(txtNombre);
        txtNombre.setBounds(20, 70, 290, 35);

        JLabel lblVigilancia = new JLabel(" En Vigilancia:");
        styleLabel(lblVigilancia, labelFont, labelColor);
        lblVigilancia.setBounds(20, 130, 140, 30);

        chkVigilancia = new JCheckBox();
        chkVigilancia.setBounds(160, 130, 30, 30);
        chkVigilancia.setOpaque(false);
        chkVigilancia.setIcon(new ImageIcon(createCustomCheckBoxImage(false)));
        chkVigilancia.setSelectedIcon(new ImageIcon(createCustomCheckBoxImage(true)));

        JButton btnRegistrarEnfermedad = new JButton(" Registrar Enfermedad");
        styleButton(btnRegistrarEnfermedad);
        btnRegistrarEnfermedad.setBounds(20, 250, 290, 40);

        
        formPanel.add(lblBuscar);
        formPanel.add(txtBuscar);
        formPanel.add(lblNombre);
        formPanel.add(txtNombre);
        formPanel.add(lblVigilancia);
        formPanel.add(chkVigilancia);
        formPanel.add(btnRegistrarEnfermedad);

        
        JLabel footerLabel = new JLabel("© 2024 Control de Enfermedades");
        footerLabel.setFont(new Font("Segoe UI", Font.ITALIC, 12));
        footerLabel.setForeground(new Color(100, 100, 100));
        footerLabel.setBounds(50, 440, 200, 20);
        mainPanel.add(footerLabel);

        mainPanel.add(formPanel);
        add(mainPanel);
        setLocationRelativeTo(null);

        btnRegistrarEnfermedad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = txtNombre.getText();
                boolean enVigilancia = chkVigilancia.isSelected();
                
                if (nombre.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, " Por favor ingrese el nombre de la enfermedad", 
                        "Advertencia", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                
                enfermedadController.registrarEnfermedad(new Enfermedad(nombre, enVigilancia));
                JOptionPane.showMessageDialog(null, " Enfermedad registrada exitosamente!", 
                    "Éxito", JOptionPane.INFORMATION_MESSAGE);
                txtNombre.setText("");
                chkVigilancia.setSelected(false);
            }
        });
    }

    private Image createCustomCheckBoxImage(boolean selected) {
        BufferedImage image = new BufferedImage(20, 20, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (selected) {
            g2d.setColor(new Color(30, 144, 255));
            g2d.fillRoundRect(0, 0, 19, 19, 5, 5);
            g2d.setColor(Color.WHITE);
            g2d.setStroke(new BasicStroke(2));
            g2d.drawLine(4, 10, 8, 14);
            g2d.drawLine(8, 14, 15, 6);
        } else {
            g2d.setColor(new Color(200, 200, 200));
            g2d.drawRoundRect(0, 0, 19, 19, 5, 5);
        }

        g2d.dispose();
        return image;
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
        
        
        textField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                textField.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(30, 144, 255), 2),
                    BorderFactory.createEmptyBorder(5, 10, 5, 10)
                ));
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                textField.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(135, 206, 250), 2),
                    BorderFactory.createEmptyBorder(5, 10, 5, 10)
                ));
            }
        });
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