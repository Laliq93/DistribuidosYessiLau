package clientemain;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class Cliente {
	
    /**
    * Puerto
    * */
    private final static int PORT = 5001;
    /**
    * Host
    * */
    private final static String SERVER = "192.168.14.15";
    
    public static void main(String[] args) {
    	boolean exit=false;//bandera para controlar ciclo del programa
        Socket socket;//Socket para la comunicacion cliente servidor        
        try {            
            System.out.println("Cliente> Inicio");  
            while( !exit ){//ciclo repetitivo                                
                socket = new Socket(SERVER, PORT);//abre socket                
                //Para leer lo que envie el servidor      
                
                BufferedReader input = new BufferedReader( new InputStreamReader(socket.getInputStream()));                
                //para imprimir datos del servidor
                System.out.println(input); 
                PrintStream output = new PrintStream(socket.getOutputStream());    
                System.out.println(output); 
                //Para leer lo que escriba el usuario            
                BufferedReader brRequest = new BufferedReader(new InputStreamReader(System.in));            
                System.out.println("Cliente> Escriba comando");                
                //captura comando escrito por el usuario
                String request = brRequest.readLine();                
                //manda peticion al servidor
                output.println(request); 
                //captura respuesta e imprime
                String st = input.readLine();
                if( st != null ) System.out.println("Servidor> " + st );    
                if(request.equals("exit")){//terminar aplicacion
                    exit=true;                  
                    System.out.println("Cliente> Fin de programa");    
                }  
                socket.close();
            }//end while                                    
       } catch (IOException ex) {        
         System.err.println("Cliente no conecto > " + ex.getMessage());   
       }
    }
    
}
