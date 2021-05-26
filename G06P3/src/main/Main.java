package main;

import java.awt.EventQueue;

import algoritmoGenetico.individuos.RastroSantaFe;
import interfaz.panelPrincipal;



public class Main {

	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RastroSantaFe sf = new RastroSantaFe();
					@SuppressWarnings("unused")
					panelPrincipal window = new panelPrincipal(sf);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
