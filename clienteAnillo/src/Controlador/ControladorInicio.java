/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ControladorInicio {

    static String direccionServidor="192.168.0.109";
    static String miIp="";
    static String nodoSig="localhost";
    static boolean primero=true;
    static int puertoEscuchar = 9001;
    static int puertoAEnviar = 9000;

    public ControladorInicio(){
        try {
            this.miIp=InetAddress.getLocalHost().getHostAddress();   
        } catch (UnknownHostException ex) {
            System.out.println("ERROR: no se puede obtener la direccion IP");
            Logger.getLogger(ControladorInicio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void Iniciar(){
        ServerSocket server;
        Socket socket;
        ObjectOutputStream salida;        
        ObjectInputStream entrada;
        String mensajeRecibido;
        
        if(primero){
            Enviar("1");
        }        
        
            try {
                server = new ServerSocket(puertoEscuchar);
                while(true){
                socket = new Socket();
                socket = server.accept();
                salida = new ObjectOutputStream(socket.getOutputStream());
                entrada = new ObjectInputStream(socket.getInputStream());
                String mensaje = (String) entrada.readObject();
                System.out.println("Se ha recibido "+mensaje);
                mensaje = String.valueOf(Integer.valueOf(mensaje)+1);
                Enviar(mensaje);
                }
            } catch (IOException ex) {
                Logger.getLogger(ControladorInicio.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
            Logger.getLogger(ControladorInicio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void Enviar(String mensaje) {
        try {
            //miIp = InetAddress.getLocalHost().getHostAddress();
            Socket servidor = new Socket(nodoSig, puertoAEnviar);            
            ObjectOutputStream salidaServidor = new ObjectOutputStream(servidor.getOutputStream());
            ObjectInputStream llegadaServidor = new ObjectInputStream(servidor.getInputStream());
            salidaServidor.writeObject(mensaje);
            servidor.close();
            System.out.println("Se ha enviado "+mensaje);
        } catch (IOException ex) {
            Logger.getLogger(ControladorInicio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
