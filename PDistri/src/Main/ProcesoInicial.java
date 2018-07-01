/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.PrintStream;
/**
 *
 * @author sysop
 */
public class ProcesoInicial {

	private static JFrame frame;
	public static JTextArea conversationTextArea = new JTextArea();
	public static JLabel processNameLabel;
	public static JLabel processTypeLabel;
	public static JLabel processIconLabel;
	public static int ProcessID;
	private static EnvioReciboHilos bgThread;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            ProcesoInicial window = new ProcesoInicial();
                            System.out.println("entre");
                            //ProcesoInicial.frame.setVisible(true);
                            System.out.println("entre2");
                            initialize();
                        } catch (Exception e) {
                            System.out.println("no entre");
                            e.printStackTrace();
                        }
                    }
                });
	}
	
	public static void Invocacion(final int id){
		System.out.println("Invocacion al proceso numero "+id);
		//ProcessID = id;
		EventQueue.invokeLater(new Runnable() {

			public void run() {
				try {
					ProcesoInicial window = new ProcesoInicial();
					window.ProcessID = id;
					initialize();
					//System.out.println("Starting "+id);
					window.frame.setVisible(true);
					window.frame.getContentPane().setBackground(Color.WHITE);
					bgThread = new EnvioReciboHilos();
					//sample bgThread = new sample();
					Thread th = new Thread(bgThread);
					th.start();
					
					window.frame.addWindowListener(new WindowAdapter() {		//action on window close
						public void windowClosing(WindowEvent arg0) {
							//JOptionPane.showMessageDialog(frame, "Eggs are not supposed to be green.");
							try{
								bgThread.onProcessClose();
							}catch(Exception e){
								e.printStackTrace();
							}
						}
					});
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the application.
	 */
	public ProcesoInicial(int id) {
		//initialize();
		ProcessID = id;
	}
	
	public ProcesoInicial() {
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private static void initialize() {
		System.out.println("process id "+ProcessID);
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 101, 414, 150);
		scrollPane.setViewportView(conversationTextArea);
		frame.getContentPane().add(scrollPane);
		
		/**
		conversationTextArea = new JTextArea();
		conversationTextArea.setColumns(20);
		conversationTextArea.setRows(5);
		conversationTextArea.setEditable(false);
		conversationTextArea.setLineWrap(true);
		**/
		
		processNameLabel = new JLabel("Process "+ProcessID);
		processNameLabel.setBounds(10, 11, 126, 14);
		frame.getContentPane().add(processNameLabel);
		
		processIconLabel = new JLabel("New label");
		processIconLabel.setIcon(new ImageIcon("C:\\Users\\sysop\\Desktop\\JavaLali\\PDistri\\estrellita.jpg"));
		processIconLabel.setBounds(231, 14, 59, 53);
		frame.getContentPane().add(processIconLabel);
		
		processTypeLabel = new JLabel("Process\r\n");
		processTypeLabel.setBounds(305, 28, 119, 14);
		frame.getContentPane().add(processTypeLabel);
		
		JButton btnStartToken = new JButton("Start election");
		btnStartToken.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					bgThread.sendMessage("ELECT");
				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnStartToken.setBounds(10, 70, 150, 23);
		frame.getContentPane().add(btnStartToken);
	}
}
