package socketapp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

public class Client {

    private static final int PORT = 8200; //Puerto al que se conectará el cliente.
    private static final String HOST = "localhost"; //Host al que se conectará el cliente.
 
    public final String readMessageFromServer() { //Crea un cliente de socket y lee el mensaje que provee.
        try {
            Socket client = new Socket(HOST, PORT);// Iniciamos el cliente.
 
            InputStreamReader reader =
                new InputStreamReader(client.getInputStream());
            BufferedReader message = new BufferedReader(reader);
 
            return message.readLine();// Leemos el mensaje
        } catch (Exception ex) {
            System.out.printf("Error al leer el mensaje: [ %s ].\n",
                ex.getMessage());
        } 
        return null;
    }
}