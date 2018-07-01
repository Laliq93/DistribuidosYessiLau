package socketapp;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private static final int PORT = 8200; //Puerto en el que se instalará el socket.
    private ServerSocket skServer; //Instancia del servidor Socket.

    public final void startSocketServer() { //Puerto en el cual se inicia el socket.
        try {
            this.skServer = new ServerSocket(PORT);
        } catch (Exception ex) {
            System.out.printf("Error al iniciar el socket: [ %s ].\n",
                ex.getMessage());
        }
    }

    public final void writeMessage(String message) { //Escribe un mensaje en el socket al encontrar un cliente.
        try {
            Socket skClient = this.skServer.accept();// Obtenemos un cliente.
 
            System.out.println("Obtiene canal de salida.");
            OutputStream aux = skClient.getOutputStream();
             
            System.out.println("Se crea una instancia del escritor.");
            DataOutputStream flujo = new DataOutputStream(aux);
             
            System.out.println("Se escribe el mensaje en bytes.");
            flujo.write(message.getBytes());
             
            System.out.println("Mensaje enviado.");

            skClient.close(); // Cerramos el cliente.
        } catch (IOException ex) {
            System.out.printf("Error al buscar un cliente: [ %s ].\n",
                ex.getMessage());
        } catch (Exception ex) {
            System.out.printf("Error al escribir mensaje en el socket: [ %s ].\n",
                ex.getMessage());
        }
    }
     
    public final void stopSocketServer() {
        if (!this.skServer.isClosed()) {
            try {
                this.skServer.close();
            } catch (IOException ex) {
                System.out.printf("Error al cerrar el server: [ %s ].\n",
                    ex.getMessage());
            }
        }
    }
}