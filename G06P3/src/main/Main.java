package main;

import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import algoritmoGenetico.individuos.Individuo;
import algoritmoGenetico.individuos.Operando;
import algoritmoGenetico.individuos.RastroSantaFe;
import algoritmoGenetico.selecciones.Seleccion;
import algoritmoGenetico.selecciones.SeleccionTorneo;
import interfaz.panelPrincipal;

public class Main {

	public static void main(String[] args) {
		
		/*EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RastroSantaFe sf = new RastroSantaFe();
					panelPrincipal window = new panelPrincipal(sf.getComida());
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});*/
		
		RastroSantaFe rastro = new RastroSantaFe();
		Individuo ind = new Individuo("Completo", 2, rastro.getComida());
		
		for (int i = 0; i < 50; i++) {
			
			System.out.println(ind.calculateFitness());
		}
		
		/*RastroSantaFe rastro = new RastroSantaFe();
		int numIndividuos = 10;
		ArrayList<Individuo> poblacion = new ArrayList<Individuo>();
		
		for (int i = 0; i < numIndividuos; i++) {
			
			poblacion.add(new Individuo("Completo", 3, rastro.getComida()));
		}
		
		System.out.println("\n --------------- \n Poblacion Antes: ");
		for (int i = 0; i < numIndividuos; i++) {
			
			System.out.println(poblacion.get(i).printFenotipo());
			System.out.println(poblacion.get(i).calculateFitness());
		}
		
		Seleccion seleccion = new SeleccionTorneo();
		poblacion = seleccion.seleccionar(poblacion);
		
		System.out.println("\n --------------- \n Poblacion Despues: ");
		for (int i = 0; i < numIndividuos; i++) {
			
			System.out.println(poblacion.get(i).printFenotipo());
			System.out.println(poblacion.get(i).calculateFitness());
		}*/
	}
}
