/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.net.*;
/**
 *
 * @author FernandoRainier
 */
public class MainCliente {
  
    final static String HOST = "localhost";
    final static int PUERTO = 5000;
    
    static Socket cliente = null;//Socket para la comunicacion cliente servidor 
    static DataInputStream entrada = null;
    static DataOutputStream salida = null;
    static BufferedReader request;
    
    public static void main(String[] args) throws IOException
    {
        boolean exit = false; //bandera para controlar el ciclo
        //String ipServidor ="127.0.0.1";	  
        //nos conectamos al localhost a traves de esta dirección IP
        //if (cliente != null && salida != null && entrada!= null) {	
        try {	
            System.out.println("Cliente> Inicio");
            while(!exit)
            {
                //abre socket
                cliente = new Socket(HOST, PUERTO);

                
                //MANEJO DE MENSAJE
                //para leer lo que envia el servidor
                BufferedReader input = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
                //para imprimir datos del servidor
                PrintStream output = new PrintStream(cliente.getOutputStream());
                //para leer lo que escriba el usuario
                request = new BufferedReader(new InputStreamReader(System.in));
                System.out.println("Cliente> Escriba el comando");
                //captura lo escrito por el usuario
                String comando = request.readLine();
                //manda peticion
                output.println(comando);
                //captura respuesta del servidor 
                String respuesta = input.readLine();
                System.out.println("Servidor> " + respuesta);
                if(comando.equals("exit")){//terminar aplicacion
                    exit=true;                  
                    System.out.println("Cliente> Fin de programa");    
                }                
                cliente.close();
            }
              
            /**
            entrada = new DataInputStream(cliente.getInputStream());
            // será lo que enviaremos al servidor	
            salida = new DataOutputStream(cliente.getOutputStream());
            // será lo que nos devuelva el servidor	
            */
        }
        catch (UnknownHostException excepcion) {
                System.err.println("El servidor no está levantado");
        }
        catch(IOException e)
        {
            System.out.println("Error: "+e.getMessage());
        }
        /**
        try {
            String linea_recibida;
            //Para leer lo que escriba el usuario            
            request = new BufferedReader(new InputStreamReader(System.in));            
            System.out.println("Cliente> Escriba comando");                
            //captura comando escrito por el usuario
            String escrito = request.readLine();                
            //manda peticion al servidor

            salida.writeBytes(escrito);
            linea_recibida = entrada.readLine();
            System.out.println("SERVIDOR DICE: " + linea_recibida);
            salida.close();
            entrada.close();
            cliente.close();
        }
        catch (UnknownHostException excepcion) {
            System.err.println("No encuentro el servidor en la dirección" + HOST);
        }
        catch (IOException excepcion) {
            System.err.println("Error de entrada/salida");
        }
        catch (Exception e) {
            System.err.println("Error: " + e );
        }
        */
        /**
        boolean exit = false;//bandera para controlar ciclo del programa
        BufferedReader entrada, request; //Definimos variable para la entrada
        //BufferedReader request; //Definimos variable para la entrada
        
        try
        {
            System.out.println("Cliente> Inicio"); 
            while( !exit )
            {
                ss = new Socket( HOST , PUERTO ); //abre el socket
                //Para leer lo que el servidor envia
                entrada = new BufferedReader(new InputStreamReader(ss.getInputStream()));
                //Para imprimir datos del servidor
                PrintStream output = new PrintStream(ss.getOutputStream());                

                //Para leer lo que escriba el usuario            
                request = new BufferedReader(new InputStreamReader(System.in));            
                System.out.println("Cliente> Escriba comando");                
                //captura comando escrito por el usuario
                String escrito = request.readLine();                
                //manda peticion al servidor
                output.println(escrito); 
                //captura respuesta e imprime
                String st = entrada.readLine();
                if( st != null ) System.out.println("Servidor> " + st );    
                if(escrito.equals("exit")){//terminar aplicacion
                    exit=true;                  
                    System.out.println("Cliente> Fin de programa");    
                }  
                ss.close();
            }*/

    }
}
