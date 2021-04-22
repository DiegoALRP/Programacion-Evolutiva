package main;

import java.awt.EventQueue;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import javax.swing.JOptionPane;

import algoritmoGenetico.AlgoritmoGenetico;
import algoritmoGenetico.individuos.Individuo;
import algoritmoGenetico.individuos.NGramas;
import algoritmoGenetico.individuos.Texto;
import interfaz.panelPrincipal;

public class Main {

	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JOptionPane.showMessageDialog(null, "Al hacer click en OK empezará la carga de ficheros. Porfavor espere.");
					NGramas ngramas = new NGramas();
					ngramas.loadHashs();
					panelPrincipal window = new panelPrincipal(ngramas);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		/*NGramas ngrama = new NGramas();
		ngrama.loadHashs();
		
		double mediaMejorFitness = 0;
		int numIter = 30;
		for (int i = 0; i < numIter; i++) {
			
			System.out.println("\n ITERACION: " + (i + 1));
			double start = System.currentTimeMillis();
			
			AlgoritmoGenetico ag = new AlgoritmoGenetico(ngrama, true, true);
			ag.startAlgorithm();
			
			double end = System.currentTimeMillis() - start;
			System.out.println("Time: " + end);
			mediaMejorFitness += ag.getMejorFitnessAbsoluto();
		}
		
		mediaMejorFitness = mediaMejorFitness/numIter;
		System.out.println("Media: " + mediaMejorFitness);*/
	}
}
