package main;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import javax.swing.JOptionPane;

import algoritmoGenetico.individuos.Individuo;
import algoritmoGenetico.individuos.Operando;
import algoritmoGenetico.individuos.RastroSantaFe;
import algoritmoGenetico.misc.Pair;
import algoritmoGenetico.selecciones.Seleccion;
import algoritmoGenetico.selecciones.SeleccionTorneo;
import interfaz.panelPrincipal;



public class Main {

	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RastroSantaFe sf = new RastroSantaFe();
					panelPrincipal window = new panelPrincipal(sf);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
