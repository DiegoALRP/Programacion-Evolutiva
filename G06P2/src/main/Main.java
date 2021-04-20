package main;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import algoritmoGenetico.AlgoritmoGenetico;
import algoritmoGenetico.individuos.Individuo;
import algoritmoGenetico.individuos.NGramas;
import algoritmoGenetico.individuos.Texto;
import interfaz.panelPrincipal;

public class Main {

	public static void main(String[] args) {
		
		double start = System.currentTimeMillis();
		
		/*AlgoritmoGenetico ag = new AlgoritmoGenetico();
		ag.startAlgorithm();*/
		panelPrincipal main = new panelPrincipal();
		
		double end = System.currentTimeMillis() - start;
		System.out.println("Time: " + end);
	}
}
