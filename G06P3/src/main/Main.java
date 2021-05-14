package main;

import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import algoritmoGenetico.cruces.Cruce;
import algoritmoGenetico.individuos.Arbol;
import algoritmoGenetico.individuos.Individuo;
import algoritmoGenetico.individuos.Operando;
import algoritmoGenetico.individuos.RastroSantaFe;
import algoritmoGenetico.mutaciones.Mutacion;
import algoritmoGenetico.mutaciones.MutacionArbol;
import algoritmoGenetico.mutaciones.MutacionFuncionalSimple;
import algoritmoGenetico.mutaciones.MutacionPermutacion;
import algoritmoGenetico.mutaciones.MutacionTerminalSimple;
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
		
		/*RastroSantaFe rastro = new RastroSantaFe();
		Individuo ind = new Individuo("Completo", 2, rastro.getComida());
		
		for (int i = 0; i < 50; i++) {
			
			System.out.println(ind.calculateFitness());
		}*/
		
		for (int i = 0; i < 1000; i++) {
			RastroSantaFe rastro = new RastroSantaFe();
			Individuo ind1 = new Individuo("Completo", 3, rastro.getComida());
			Individuo ind2 = new Individuo("Completo", 3, rastro.getComida());
			
			ArrayList<Operando> antes1 = new ArrayList<Operando>();
			ArrayList<Operando> antes2 = new ArrayList<Operando>();
			ind1.getCromosoma().toArrayAux(antes1);
			ind2.getCromosoma().toArrayAux(antes2);
			
			Mutacion mutacion = new MutacionPermutacion();
			mutacion.mutaIndividuo(ind1);
			mutacion.mutaIndividuo(ind2);
			//Cruce cruce = new Cruce();
			//cruce.cruzaPadres(ind1, ind2);
			
			ArrayList<Operando> despues1 = new ArrayList<Operando>();
			ArrayList<Operando> despues2 = new ArrayList<Operando>();
			ind1.getCromosoma().toArrayAux(despues1);
			ind2.getCromosoma().toArrayAux(despues2);
			
			System.out.println("ANTES1: " + antes1);
			System.out.println("DESPU1: " + despues1);
			System.out.println("ANTES2: " + antes2);
			System.out.println("DESPU2: " + despues2);
		}
		
		/*Operando op1 = new Operando("AVANZA");
		Operando op2 = new Operando("AVANZA");
		
		System.out.println(op1.equals(op2));*/
		
		/*Operando op1 = new Operando("PROGN2");
		Arbol a1 = new Arbol(null, op1, 3);
		
		ArrayList<Operando> fasti1 = new ArrayList<Operando>();
		a1.toArrayAux(fasti1);
		//System.out.println("Arbol antes: " + fasti1);
		
		Operando op2 = new Operando("PROGN3");
		Arbol a2 = new Arbol(null, op2, 3);
		ArrayList<Operando> fasti2 = new ArrayList<Operando>();
		a2.toArrayAux(fasti2);
		//System.out.println("Arbol antes: " + fasti2);
		
		ArrayList<Operando> arr1 = new ArrayList<Operando>();
		
		Arbol asub1 = a1.getSubTree(0.5);
		Arbol asub1Padre = asub1.getPadre();
		int prof1 = asub1.getMax_prof();
		asub1.toArrayAux(arr1);
		
		ArrayList<Operando> arr2 = new ArrayList<Operando>();
		
		Arbol asub2 = a2.getSubTree(0.5);
		Arbol asub2Padre = asub2.getPadre();
		int prof2 = asub2.getMax_prof();
		asub2.toArrayAux(arr2);
		
		//Arbol copia1 = new Arbol(arr1, prof1);
		//Arbol copia2 = new Arbol(arr2, prof2);
		
		asub1Padre.insertNewTree(asub2, asub1Padre.getIndex(asub1.getRaiz()));
		asub2Padre.insertNewTree(asub1, asub2Padre.getIndex(asub2.getRaiz()));
		
		ArrayList<Operando> pp1 = new ArrayList<Operando>();
		ArrayList<Operando> pp2 = new ArrayList<Operando>();
		
		a1.toArrayAux(pp1);
		a2.toArrayAux(pp2);
		System.out.println("Arbol antes: " + fasti1);
		System.out.println("Arbol despu: " + pp1);
		System.out.println("Arbol antes: " + fasti2);
		System.out.println("Arbol despu: " + pp2);*/
		
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
