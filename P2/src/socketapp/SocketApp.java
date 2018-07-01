package socketapp;

import socketapp.Client;
import socketapp.Server;

public class SocketApp {

    public static void main(String[] args) {
        initServer(); // Iniciamos el server.
        initClient(); // Iniciamos el cliente y recibimos el mensaje.
    }
    
    private static void initClient() { //Inicia el cliente del socket.
        Client client = new Client();
 
        System.out.printf("Leido: [ %s ].\n", client.readMessageFromServer());
    }
    
    private static void initServer() {
        Runnable runnable = new Runnable() {
 
            @Override
            public final void run() {
                Server server = new Server();
                server.startSocketServer();
                server.writeMessage("Mensaje para los clientes.");
                 
                server.stopSocketServer();
            }
        };
        Thread t1 = new Thread(runnable);
        t1.start();
    } 
}