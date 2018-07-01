/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;


import java.io.DataInputStream;
import java.io.DataOutputStream;


/**
 *
 * @author FernandoRainier
 */
public class MainServer {
    
    final static int PUERTO = 5000;
    
    //static ServerSocket miServicio = null;
    //static Socket cliente = null;
    
    static String mensajeRecibido;
    static DataInputStream entrada;
    static PrintStream salida;
    static int numeroCliente = 0;
    
    static boolean status = true;
    //static DataOutputStream salida;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        try
        {
            //Socket de servidor para esperar peticiones de la red
            ServerSocket miServicio = new ServerSocket(PUERTO);/* crea socket MainServer que escuchara en puerto 5000*/
            //Servidor iniciado
            System.out.println("Servidor> Servidor iniciado. Esperando una conexión:");
            //Socket del cliente
            Socket cliente;
            while(true)
            {
                //en espera de conexion
                cliente = miServicio.accept();
                numeroCliente ++;
                //Se instancia una clase para atender al cliente
                Runnable nuevoCliente = new HiloDeCliente(numeroCliente, cliente);
                //Se lanza un hilo aparte
                Thread hilo = new Thread(nuevoCliente);
                hilo.start();
                        
                 /**       
                //MANEJO DE MENSAJE
                //para leer lo que envia el cliente
                BufferedReader input = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
                //para imprimir los datos de salida
                PrintStream output = new PrintStream(cliente.getOutputStream());
                //se lee la peticion del cliente
                String request = input.readLine();
                System.out.println("Cliente> peticion "+ request +"");
                //se procesa la peticion y se espera resultado
                String strOutput = process(request);
                //se imprime en cola servidor
                System.out.println("Servidor> Resultado");
                System.out.println("Servidor> "+ strOutput +"");
                //se imprime en cliente
                output.flush();//vacia contenido
                output.println(strOutput);                
                //cierra conexion
                cliente.close();
                */
            }
            
            
//            so = new Socket();
//            so = sc.accept();
/**
            while(status == true)
            {
                cliente = ss.accept(); //Si existe la conexion la acepta
                //Para leer lo que envie el cliente
                entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
                salida = new DataOutputStream(cliente.getOutputStream());
                System.out.println("Un cliente se ha conectado.");
                System.out.println("Confirmando conexion al cliente....");            
                salida.writeUTF("Conexión exitosa...n envia un mensaje :D");
                //Recepcion de mensaje
                mensajeRecibido = entrada.readLine();
                System.out.println(mensajeRecibido);
                salida.writeUTF("Se recibio tu mensaje.n Terminando conexion...");
                salida.writeUTF("Gracias por conectarte, adios!");
                System.out.println("Cerrando conexión...");
                ss.close();//Aqui se cierra la conexión con el cliente

                //para imprimir datos de salida                
                //PrintStream output = new PrintStream(cliente.getOutputStream());

            }*/
        }catch(IOException excepcion)
        {
            System.out.println(excepcion.getMessage());
        }
        /**
        try {
            cliente = miServicio.accept();
            entrada = new DataInputStream(cliente.getInputStream());
            salida = new PrintStream(cliente.getOutputStream());
            mensajeRecibido = entrada.readLine();
            salida.println(mensajeRecibido );
            salida.println("Te reenvio lo que he recibido:" + mensajeRecibido );
            salida.close();
            entrada.close();
            cliente.close();
        }
        catch (IOException excepcion) {
            System.out.println(excepcion);
        }*/
        
    }  

    /**
    public static String process(String request)
    {
        String result = "";
        switch(request)
        {
            case "hola":
                result = "hola como estas";
                break;
            case "estas":
                result =  "bien y tu?";
                break;
            default:
                result = "La peticion no se puede";
                break;
        }
        return result;
    }
    */
}
