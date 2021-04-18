package main;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import algoritmoGenetico.AlgoritmoGenetico;
import algoritmoGenetico.individuos.Individuo;
import algoritmoGenetico.individuos.NGramas;
import algoritmoGenetico.individuos.Texto;

public class Main {

	public static void main(String[] args) {
		
		/*double start = System.currentTimeMillis();
		StringBuilder st = new StringBuilder("Gtunfwveqw: Mfdue fw gtd, mfdue fw zrtpr, twb xtue fw enr Wtefqwtx Xrtvor Rtue.");
		StringBuilder st2 = new StringBuilder("Gtunfwveqw: Mfdue fw gtd, mfdue fw zrtpr, twb xtue fw enr Wtefqwtx Xrtvor Rtue.Gtunfwveqw: Mfdue fw gtd, mfdue fw zrtpr, twb xtue fw enr Wtefqwtx Xrtvor Rtue.");
		
		StringBuilder st3 = new StringBuilder("Washington: First in war, first in peace, and last in the National League East. Washington: First in war, first in peace, and last in the National League East.");
		NGramas ng = new NGramas();
		ng.loadHashs();
		Texto claseTexto = new Texto(st, st2);
		
		Individuo ind = new Individuo(claseTexto, ng);
		System.out.println(ind.getFenotipe());
		double end = System.currentTimeMillis() - start;
		System.out.println("Time: " + end);*/
		
		/*double start = System.currentTimeMillis();
		
		AlgoritmoGenetico ag = new AlgoritmoGenetico();
		ag.startAlgorithm();
		
		double end = System.currentTimeMillis() - start;
		System.out.println("Time: " + end);*/
		
		double start = System.currentTimeMillis();
		
		IntentoVueltaAtras va = new IntentoVueltaAtras();
		va.start();
		
		double end = System.currentTimeMillis() - start;
		System.out.println("Time: " + end);
	}
}
