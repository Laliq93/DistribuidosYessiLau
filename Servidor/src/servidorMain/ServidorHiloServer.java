/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorMain;
import java.io.*;
import java.net.*;
import java.util.logging.*;
/**
 *
 * @author macdelali
 */
public class ServidorHiloServer extends Thread {
    private Socket socket;
    private DataOutputStream dos;
    private DataInputStream dis;
    private int idSessio;
    
    public ServidorHiloServer(Socket socket, int id) {
        this.socket = socket;
        this.idSessio = id;
        try {
            dos = new DataOutputStream(socket.getOutputStream());
            dis = new DataInputStream(socket.getInputStream());
        } catch (IOException ex) {
            Logger.getLogger(ServidorHiloServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void desconnectar() {
        try {
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(ServidorHiloServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    public void run() {
        String accion = "";
        try {
            accion = dis.readUTF();
            if(accion.equals("hola")){
                System.out.println("El cliente con idSesion "+this.idSessio+" saluda");
                dos.writeUTF("adios");
            }
        } catch (IOException ex) {
            Logger.getLogger(ServidorHiloServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        desconnectar();
    }
}
