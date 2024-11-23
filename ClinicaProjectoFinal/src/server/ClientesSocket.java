package server;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ClienteSocket {
    private static final String SERVER_HOST = "localhost";
    private static final int SERVER_PORT = 5000;

    public static void main(String[] args) {
        try (Socket socket = new Socket(SERVER_HOST, SERVER_PORT);
             PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
             Scanner scanner = new Scanner(System.in)) {

            System.out.println("Conectado al servidor: " + SERVER_HOST + ":" + SERVER_PORT);
            
            while (true) {
                System.out.print("Escriba su mensaje (o 'salir' para terminar): ");
                String mensaje = scanner.nextLine();
                
                if ("salir".equalsIgnoreCase(mensaje)) {
                    break;
                }
                
                output.println(mensaje);
                System.out.println("Mensaje enviado: " + mensaje);
            }
        } catch (UnknownHostException e) {
            System.err.println("Servidor no encontrado: " + e.getMessage());
        } catch (ConnectException e) {
            System.err.println("No se pudo conectar al servidor: " + e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}