package main;

import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import algoritmoGenetico.individuos.Individuo;
import algoritmoGenetico.individuos.Operando;
import algoritmoGenetico.individuos.RastroSantaFe;
import interfaz.panelPrincipal;
import misc.Pair;

public class main {

	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RastroSantaFe sf = new RastroSantaFe();
					panelPrincipal window = new panelPrincipal(sf.getComida());
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		/*ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
		
		int count = 0;
		int end = list.size();
		System.out.println(list);
		for (int i = 0; i < list.size(); i++) {
			
			System.out.println(list.get(0));
			list.remove(0);
			//count++;
		}
		System.out.println(list);*/
		/*RastroSantaFe sf = new RastroSantaFe();
		Individuo ind1 = new Individuo("Completo", 2, sf.getComida());
		System.out.println("Antes 1: " + ind1.printFenotipo());
		Individuo ind2 = new Individuo("Completo", 2, new ArrayList<Pair>());
		System.out.println("Antes 2: " + ind2.printFenotipo());
		
		ind2.copyFenotipe(ind1.copyFenotipe());
		System.out.println("Despues 1: " + ind1.printFenotipo());
		System.out.println("Despues 2: " + ind2.printFenotipo());
		
		
		for(int i = 0; i < ind1.getTerminales().size(); i++) {
			System.out.println(ind1.getTerminales().get(i));
		}
		
		ind1.calculateFitness();
		System.out.println(ind1.getFitness());
		*/
	}
}
