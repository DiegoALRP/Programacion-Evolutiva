package main;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import javax.swing.JOptionPane;

import algoritmoGenetico.cruces.Cruce;
import algoritmoGenetico.individuos.Individuo;
import algoritmoGenetico.individuos.Operando;
import algoritmoGenetico.individuos.RastroSantaFe;
import algoritmoGenetico.misc.Pair;
import algoritmoGenetico.mutaciones.Mutacion;
import algoritmoGenetico.mutaciones.MutacionArbol;
import algoritmoGenetico.mutaciones.MutacionExpansion;
import algoritmoGenetico.mutaciones.MutacionHoist;
import algoritmoGenetico.mutaciones.MutacionPermutacion;
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
		
		/*RastroSantaFe santaFe = new RastroSantaFe();
		Individuo ind1 = new Individuo("Completo", 2, 400, santaFe);
		Individuo ind2 = new Individuo("Completo", 2, 400, santaFe);
		
		System.out.println(ind1.printFenotipo());
		System.out.println(ind1.getNumNodosArbol());
		System.out.println(ind2.printFenotipo());
		System.out.println(ind2.getNumNodosArbol());
		
		/*System.out.println(ind1.getTreeSize());
		System.out.println(ind2.getTreeSize());
		
		//Cruce cruce = new Cruce();
		//cruce.cruzaPadres(ind1, ind2);
		//MutacionPermutacion muta = new MutacionPermutacion();
		//muta.mutaIndividuo(ind1);
		//muta.mutaIndividuo(ind2);
		MutacionExpansion muta = new MutacionExpansion();
		muta.mutaIndividuo(ind1);
		muta.mutaIndividuo(ind2);
		
		
		System.out.println(ind1.printFenotipo());
		System.out.println(ind2.printFenotipo());
		System.out.println(ind1.getTreeSize());
		System.out.println(ind2.getTreeSize());*/
	}
}
