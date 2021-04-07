package main;

import java.util.ArrayList;
import java.util.Random;

import algoritmoGenetico.AlgoritmoGenetico;
import algoritmoGenetico.cruces.Cruce;
import algoritmoGenetico.cruces.CruceAritmetico;
import algoritmoGenetico.cruces.CruceBLX;
import algoritmoGenetico.cruces.CruceMonopunto;
import algoritmoGenetico.cruces.CruceUniforme;
import algoritmoGenetico.individuos.IndividuoFuncion1;
import algoritmoGenetico.mutaciones.Mutacion;
import algoritmoGenetico.mutaciones.MutacionBasica;
import algoritmoGenetico.mutaciones.MutacionUniforme;
import algoritmoGenetico.seleccion.Seleccion;
import algoritmoGenetico.seleccion.SeleccionEstocastico;
import algoritmoGenetico.seleccion.SeleccionRestos;
import algoritmoGenetico.seleccion.SeleccionRuleta;
import algoritmoGenetico.seleccion.SeleccionTorneo;
import algoritmoGenetico.seleccion.SeleccionTorneoProbabilistico;
import algoritmoGenetico.seleccion.SeleccionTruncamiento;
import interfaz.panelPrincipal;

//import java.lan
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
		panelPrincipal p = new panelPrincipal();
		/*AlgoritmoGenetico ag = new AlgoritmoGenetico(2, 
				2, 0.0001, null, null, 0.01, null, 0, 0);*/
		
		/*Seleccion sel = new SeleccionRuleta();
		Cruce cr = new CruceMonopunto();
		Mutacion mt = new MutacionBasica();
		
		double mejorAbsoluto = 0;
		double mediaAbsoluto = 0;
		double mediaMejorGeneracion = 0;
		double mediaMediaGeneracion = 0;
		
		int vueltas = 100;
		
		for (int i = 0; i < vueltas; i++) {
			AlgoritmoGenetico ag = new AlgoritmoGenetico(100, 100, 0.0001, sel, cr,
					0.6, mt, 0.05, 0, "Funcion Michalewicz (Reales)", 6);
			
			double mejorAbsolutoArray[] = ag.getMejorAbsoluto();
			double mejorGeneracionArray[] = ag.getMejorGeneracion();
			double mediaGeneracionArray[] = ag.getMediaGeneracion();
			
			double mejorAbsolutoAux = 0;
			double mediaAbsolutoAux = 0;
			double mediaMejorGeneracionAux = 0;
			double mediaMediaGeneracionAux = 0;
			
			for (int j = 0; j < mejorAbsolutoArray.length; j++) {
				
				mejorAbsolutoAux = mejorAbsolutoArray[j];
				mediaMejorGeneracionAux += mejorGeneracionArray[j];
				mediaMediaGeneracionAux += mediaGeneracionArray[j];
				
				if (mejorAbsolutoAux < mejorAbsoluto) {
					
					mejorAbsoluto = mejorAbsolutoAux;
				}
				
				
				mediaAbsolutoAux += mejorAbsolutoAux;
			}
			
			mediaAbsolutoAux = mediaAbsolutoAux/mejorAbsolutoArray.length;
			mediaAbsoluto += mediaAbsolutoAux;
			
			mediaMejorGeneracionAux = mediaMejorGeneracionAux/mejorAbsolutoArray.length;
			mediaMejorGeneracion += mediaMejorGeneracionAux;
			
			mediaMediaGeneracionAux = mediaMediaGeneracionAux/mejorAbsolutoArray.length;
			mediaMediaGeneracion += mediaMediaGeneracionAux;
		}
		
		mediaAbsoluto = mediaAbsoluto/vueltas;
		mediaMejorGeneracion = mediaMejorGeneracion/vueltas;
		mediaMediaGeneracion = mediaMediaGeneracion/vueltas;
		
		
		System.out.println("Mejor Absoluto: " + mejorAbsoluto);
		System.out.println("Media Absoluto: " + mediaAbsoluto);
		System.out.println("Media Mejor Generacion: " + mediaMejorGeneracion);
		System.out.println("Media Media Generacion: " + mediaMediaGeneracion);*/
	}

}
