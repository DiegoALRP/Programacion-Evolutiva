package main;

import java.io.FileNotFoundException;

import algoritmoGenetico.individuos.Individuo;
import algoritmoGenetico.individuos.NGramas;

public class Main {

	public static void main(String[] args) {
		
		double start = System.currentTimeMillis();
		StringBuilder st = new StringBuilder("Gtunfwveqw: Mfdue fw gtd, mfdue fw zrtpr, twb xtue fw enr Wtefqwtx Xrtvor Rtue.");
		StringBuilder st2 = new StringBuilder("Gtunfwveqw: Mfdue fw gtd, mfdue fw zrtpr, twb xtue fw enr Wtefqwtx Xrtvor Rtue.Gtunfwveqw: Mfdue fw gtd, mfdue fw zrtpr, twb xtue fw enr Wtefqwtx Xrtvor Rtue.");
		
		StringBuilder st3 = new StringBuilder("Washington: First in war, first in peace, and last in the National League East. Washington: First in war, first in peace, and last in the National League East.");
		NGramas ng = new NGramas();
		ng.loadHashs();
		
		Individuo ind = new Individuo(st, st2, ng);
		System.out.println(ind.getFenotipe());
		double end = System.currentTimeMillis() - start;
		System.out.println("Time: " + end);
		
	}

}
