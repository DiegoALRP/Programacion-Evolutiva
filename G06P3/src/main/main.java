package main;

import java.awt.EventQueue;

import javax.swing.JOptionPane;

import algoritmoGenetico.individuos.Operando;
import interfaz.panelPrincipal;
import misc.rastroSantaFe;

public class main {

	public static void main(String[] args) {
		
		/*EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					rastroSantaFe sf = new rastroSantaFe();
					panelPrincipal window = new panelPrincipal(sf.getComida());
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});*/
		
		String st = "PROGN2";
		Operando op = new Operando(st);
		System.out.println(op.equalsProgN2()); 
	}
}
