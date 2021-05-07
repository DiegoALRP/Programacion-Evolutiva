package main;

import java.awt.EventQueue;

import javax.swing.JOptionPane;

import interfaz.panelPrincipal;
import misc.rastroSantaFe;

public class main {

public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					rastroSantaFe sf = new rastroSantaFe();
					panelPrincipal window = new panelPrincipal(sf.getComida());
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
