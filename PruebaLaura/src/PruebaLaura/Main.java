/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PruebaLaura;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.ServerSocket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
/**
 *
 * @author sysop
 */
@SuppressWarnings("serial")
public class Main extends JFrame{
	public boolean isProcessCoordinator = false; //boolean variable to indicate is the process a coordinator 
	public static boolean isProcessCrashed = false; //boolean variable to indicate is the process crashed 
	public boolean isProcessSlept = true; //boolean variable to indicate is the process active.
	public  int coordinator = 0; //stores coordinator
	public int currentActive = 0; //stores currently active process number
	public  int crashedPortNumber = 0; //stores crashed port number
	public static  int portNumber = 0; //stores port number for process
	public static int processNumberInt = 0; //stores process number
	static InputReader thread; //stores input thread reader
	static ServerSocket serverSocket; //stores socket for process
	static Main rc; //stores UI object
	
	//Variables for UI implementation
	public JPanel panel; 
	public JTextArea textArea;
	public JScrollPane scroll;
	public JTextField messageArea;
	public JButton sendButton;
	
	/**
	 * Constructor of class RCAUserInterface.
	 * Used to initialize GUI 
	 */
	public Main(){
		intializeUIComponents();
	}

	/**
	 * Method contains all UI components 
	 */
	private void intializeUIComponents() {
		textArea = new JTextArea();
		sendButton = new JButton("Enviar paquete");
		panel = new JPanel();
		scroll = new JScrollPane(); 
		setTitle("Proceso Almacenes");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(250, 250, 450, 300);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panel);
		panel.setLayout(null);
		textArea.setEditable(false); 

		scroll.setViewportView(textArea);
		scroll.setBounds(10, 20, 400, 180);
		panel.add(scroll);
		
		messageArea = new JTextField();
		messageArea.setBounds(40, 225, 150, 23);
		panel.add(messageArea);
		
		sendButton.setBounds(200, 225, 150, 23);
		panel.add(sendButton);
		//Method for action event on Send button click
		sendButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				sendButtonActionPerformed(evt);
			}
		});
	}

	/**
	 * Method used to call function based on keyword entered.
	 * @param evt
	 */
	private void sendButtonActionPerformed(ActionEvent evt) {
		if(messageArea.getText().equals("Elegir coordinador")){
			startElectionActionPerformed(); //Election start method
		}
		else if(messageArea.getText().equals("Detener el anillo")){
			crashProcessActionPerformed(); //Crash process method
		}else if(messageArea.getText().equals("Reiniciar los procesos")){
			resetProcessActionPerformed(); //reset process method
		}else{
			textArea.append("\n Escribe un metodo valido.");
		}
	}


	/**
	 * Method starts election process by sending a token to next available process.
	 */
	private void startElectionActionPerformed() {
		isProcessSlept = false;
		textArea.append("\n Envia: "  + processNumberInt);
		ElectionUtility.startElection(ElectionUtility.TOKENTYPE1+" " + processNumberInt, processNumberInt+1,"localhost"); //call start election method
	}

	/**
	 * Method used to crash the process
	 */
	private void crashProcessActionPerformed() {
		try {
			ElectionUtility.crashProcess(thread, serverSocket);
			isProcessCrashed = true; 
			crashedPortNumber = 8080 + processNumberInt;
			textArea.append("\n El proceso se rompio.");
		} catch (IOException e) {
			isProcessCrashed = false;
			textArea.append("\n No se puede terminar este proceso, intente de nuevo.");
		}
	}

	/**
	 * Method used to reset the crashed process 
	 */
	private void resetProcessActionPerformed() {
		try {
			isProcessCrashed = false;
			ElectionUtility.resetProcess(thread, crashedPortNumber, serverSocket, processNumberInt);
			textArea.append("\n Reiniciando hilos.  \n Comenzando eleccion.");
			crashedPortNumber = 0;
			isProcessSlept = false; 
			textArea.append("\n Envia: "  + processNumberInt);
			ElectionUtility.startElection(ElectionUtility.TOKENTYPE1+" " + processNumberInt, processNumberInt+1,"localhost");
		} catch (IOException ex) {  
			System.out.println("\n Puerto no disponible para restablecer el proceso.");
			System.exit(1);
		}

	}

	/**
	 * Method used to start and allocate socket to a process 
	 */
	private static void startProcess(){
		for(int i=1;i<6;i++)
		{
			try{
				portNumber = i+8080;
				serverSocket= new ServerSocket(portNumber);
				processNumberInt = portNumber - 8080; 
				thread = new InputReader(portNumber, processNumberInt, rc, serverSocket); 
				thread.start();
				rc.textArea.append("El proceso "+processNumberInt+" se ha iniciado");
				break;
			}catch (IOException e){
			}
		}

	}

	/**
	 * Method that will initiated the process
	 * @param args
	 */
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
                rc = new Main();
		rc.setVisible(true);
		startProcess();
    }
    
}
