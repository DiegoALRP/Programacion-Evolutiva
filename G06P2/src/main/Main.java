package main;

import java.io.FileNotFoundException;

import algoritmoGenetico.individuos.Individuo;
import algoritmoGenetico.individuos.NGramas;

public class Main {

	public static void main(String[] args) {
		
		double start = System.currentTimeMillis();
		String st = "Gtunfwveqw: Mfdue fw gtd, mfdue fw zrtpr, twb xtue fw enr Wtefqwtx Xrtvor Rtue.";
		String st2 = "Gtunfwveqw: Mfdue fw gtd, mfdue fw zrtpr, twb xtue fw enr Wtefqwtx Xrtvor Rtue.Gtunfwveqw: Mfdue fw gtd, mfdue fw zrtpr, twb xtue fw enr Wtefqwtx Xrtvor Rtue.";
		
		String st3 = "Washington: First in war, first in peace, and last in the National League East. Washington: First in war, first in peace, and last in the National League East.";
		NGramas ng = new NGramas();
		ng.loadHashs();
		
		Individuo ind = new Individuo(st, st2, ng);
		double end = System.currentTimeMillis() - start;
		System.out.println("Time: " + end);
		//System.out.println(ind.getFenotipe());
		
		/*double start = System.currentTimeMillis();
		NGramas ng = new NGramas();
		ng.loadHashs();
		double end = System.currentTimeMillis() - start;
		System.out.println("Time: " + end);*/
		
		//System.out.println((char)97);
		
		/*for (int i = 0; i < 26; i++) {
			
			System.out.println((char)(97 + i));
		}*/
	}

}
