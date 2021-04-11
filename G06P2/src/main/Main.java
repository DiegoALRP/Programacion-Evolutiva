package main;

import java.io.FileNotFoundException;

import algoritmoGenetico.individuos.Individuo;
import algoritmoGenetico.individuos.NGramas;

public class Main {

	public static void main(String[] args) {
		
		/*String st = "Gtunfwveqw: Mfdue fw gtd, mfdue fw zrtpr, twb xtue fw enr Wtefqwtx Xrtvor Rtue.";
		Individuo ind = new Individuo(st);
		System.out.println(ind.getFenotipe());*/
		
		double start = System.currentTimeMillis();
		NGramas ng = new NGramas();
		ng.loadHashs();
		double end = System.currentTimeMillis() - start;
		System.out.println("Time: " + end);
		
		//System.out.println((char)97);
		
		/*for (int i = 0; i < 26; i++) {
			
			System.out.println((char)(97 + i));
		}*/
	}

}
