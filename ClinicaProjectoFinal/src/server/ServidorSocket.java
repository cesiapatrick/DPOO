package server;

import java.io.*;
import java.net.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ServidorSocket {
    private static final int PORT = 5000;
    private static final DateTimeFormatter TIMESTAMP_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Servidor iniciado en el puerto " + PORT + " en " + 
                               LocalDateTime.now().format(TIMESTAMP_FORMATTER));

            while (true) {
                Socket clientSocket = serverSocket.accept();
                
                // Manejar cada cliente en un hilo separado
                new Thread(() -> {
                    try (BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                         PrintWriter output = new PrintWriter(clientSocket.getOutputStream(), true)) {
                        
                        String clientInfo = "Cliente: " + clientSocket.getInetAddress().getHostAddress() + 
                                            ":" + clientSocket.getPort();
                        
                        String mensaje;
                        while ((mensaje = input.readLine()) != null) {
                            System.out.println(LocalDateTime.now().format(TIMESTAMP_FORMATTER) + 
                                               " - " + clientInfo + ": " + mensaje);
                            
                            
                            output.println("Mensaje recibido: " + mensaje);
                        }
                    } catch (IOException e) {
                        System.err.println("Error procesando cliente: " + e.getMessage());
                    } finally {
                        try {
                            clientSocket.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        } catch (IOException e) {
            System.err.println("Error en el servidor: " + e.getMessage());
            e.printStackTrace();
        }
    }
}