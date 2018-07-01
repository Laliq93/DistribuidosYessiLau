package clientemain;
import java.io.*;
import java.net.Socket;
import java.util.*;
import java.util.logging.*;

class ClienteSub extends Thread {
    protected Socket sk;
    protected DataOutputStream dos;
    protected DataInputStream dis;
    private int id;
        public ClienteSub (int id) {
        this.id = id;
        }
    
    @Override
    public void run() {
        try {
            sk = new Socket("127.0.0.1", 10578);
            dos = new DataOutputStream(sk.getOutputStream());
            dis = new DataInputStream(sk.getInputStream());
            System.out.println(id + " envía saludo");
            dos.writeUTF("hola");
            String respuesta="";
            respuesta = dis.readUTF();
            System.out.println(id + " Servidor devuelve saludo: " + respuesta);
            dis.close();
            dos.close();
            sk.close();
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
public class Cliente {
    public static void main(String[] args) {
        ArrayList<Thread> clients = new ArrayList<Thread>();
        for (int i = 0; i < 5; i++) {
            clients.add(new ClienteSub(i));
        }
        for (Thread thread : clients) {
            thread.start();
        }
    }
}
