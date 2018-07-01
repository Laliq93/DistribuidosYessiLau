/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;


/**
 *
 * @author sysop
 */

public class Proceso2 {

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		ProcesoInicial procesoI = new ProcesoInicial();
		procesoI.Invocacion(5);
		Thread.currentThread().sleep(2000);
		
	}

}