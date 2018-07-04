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
import java.net.Socket;
//import static server.MainServer.cliente;

/**
 *
 * @author FernandoRainier
 */
public class HiloDeCliente extends Thread{

    private final int NCliente;
    private final Socket cliente;
    
    //Se crea el constructor para recibir y guardar los parametros
    //En este caso el numero de cliente conectado y el socket que debe atender
    public HiloDeCliente(int numeroCliente, Socket socket)
    {
        this.NCliente = numeroCliente;
        this.cliente = socket;
    }

    //METODO PARA ATENDER AL CLIENTE
    @Override
    public void run() {
        //while(true)
        //{
            System.out.println("Entro en el hilo del servidor. Y es el cliente numero: "+ NCliente +"");
            try{
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
                System.out.println("Servidor> Resultado del cliente "+ NCliente+"");
                System.out.println("Servidor> "+ strOutput +"");
                //se imprime en cliente
                output.flush();//vacia contenido
                output.println(strOutput);                
                //cierra conexion
                cliente.close();    
            }catch(IOException excepcion)
            {
                System.out.println(excepcion.getMessage());
            }
        //}
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    } 
    
    public static String process(String request)
    {
        String result;
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
        try{
            Thread.sleep(5 * 1000);
        }catch(InterruptedException ex){
            Thread.currentThread().interrupt();
        }
        return result;
    }
}
