package server;

import java.io.*;
import java.net.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BackupSocket {
    private static final int PRIMARY_PORT = 5000;
    private static final int BACKUP_PORT = 5001;
    private static final String CSV_FILE_PATH = "backup_log.csv";
    private static final DateTimeFormatter TIMESTAMP_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static void main(String[] args) {
        try {
            
            if (conectarServidorPrincipal()) {
                System.out.println("Conexión con el servidor principal exitosa.");
                return;
            }

            
            iniciarServidorBackup();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean conectarServidorPrincipal() {
        try (Socket socket = new Socket("localhost", PRIMARY_PORT)) {
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
            output.println("Mensaje de backup - Verificando estado del servidor principal.");
            return true;
        } catch (ConnectException e) {
            System.out.println("No se puede conectar al servidor principal.");
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private static void iniciarServidorBackup() throws IOException {
        // Crear archivo CSV con encabezados si no existe
        inicializarArchivoCSV();

        try (ServerSocket backupServerSocket = new ServerSocket(BACKUP_PORT)) {
            System.out.println("Servidor de backup iniciado en el puerto " + BACKUP_PORT + "...");

            while (true) {
                Socket clientSocket = backupServerSocket.accept();

                // Manejar la conexión en un hilo separado para permitir múltiples conexiones
                new Thread(() -> {
                    try {
                        BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                        String mensaje = input.readLine();
                        
                        // Obtener información del cliente
                        String clientIP = clientSocket.getInetAddress().getHostAddress();
                        int clientPort = clientSocket.getPort();

                        System.out.println("Mensaje recibido en el servidor de backup: " + mensaje);

                        // Guardar mensaje en log CSV
                        guardarMensajeEnLog(mensaje, clientIP, clientPort);

                        clientSocket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }).start();
            }
        }
    }

    private static void inicializarArchivoCSV() {
        File csvFile = new File(CSV_FILE_PATH);
        
        
        if (!csvFile.exists()) {
            try (PrintWriter writer = new PrintWriter(new FileWriter(csvFile))) {
                writer.println("Timestamp,Message,Source IP,Port");
            } catch (IOException e) {
                System.err.println("Error al crear archivo CSV: " + e.getMessage());
            }
        }
    }

    private static void guardarMensajeEnLog(String mensaje, String clientIP, int clientPort) {
        try (FileWriter fw = new FileWriter(CSV_FILE_PATH, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter logWriter = new PrintWriter(bw)) {

            
            String timestamp = LocalDateTime.now().format(TIMESTAMP_FORMATTER);
            
            
            String escapedMensaje = mensaje.replace("\"", "\"\"");
            
            
            logWriter.println(String.format("\"%s\",\"%s\",\"%s\",\"%d\"", 
                timestamp, 
                escapedMensaje, 
                clientIP, 
                clientPort));
            
        } catch (IOException e) {
            System.err.println("Error al escribir en el log de backup: " + e.getMessage());
        }
    }

    
    public static void imprimirContenidoCSV() {
        try (BufferedReader reader = new BufferedReader(new FileReader(CSV_FILE_PATH))) {
            String linea;
            System.out.println("Contenido del archivo CSV de backup:");
            while ((linea = reader.readLine()) != null) {
                System.out.println(linea);
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo CSV: " + e.getMessage());
        }
    }
}