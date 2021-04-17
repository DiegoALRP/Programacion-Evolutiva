package algoritmoGenetico.cruces;

import java.util.ArrayList;
import java.util.Random;

import algoritmoGenetico.individuos.Individuo;

/**
 * Universidad Complutense de Madrid.
 * Programación Evolutiva.
 * Grupo A 2021.
 * Profesor:
 * 	-Carlos Cervigon Ruckauer.
 * 
 * Práctica 2
 * 
 * Clase Cruce CO (Cruce Ordinal).
 * 
 * @author 
 * Grupo G06:
 * 	-Miguel Robledo.
 * 	-Diego Alejandro Rodríguez Pereira.
 *
 */
public class CruceCO extends Cruce {
	
	/**************************** ATRIBUTTES *******************************/
	private int longCromo;
	/**************************** CONSTRUCTOR ******************************/
	/***************************** METHODS ********************************/
	@Override
	public void cruza(ArrayList<Individuo> poblacion, double probCruce) {

		this.num_selec_cruce = 0;
		this.probCruce = probCruce;
		this.selec_cruce = new ArrayList<Integer>();
		this.tamPoblacion = poblacion.size();
		
		this.seleccionaIndividuos(poblacion);
		
		for (int i = 0; i < this.num_selec_cruce; i += 2) {
			
			cruzaPadres(poblacion.get(selec_cruce.get(i)), poblacion.get(selec_cruce.get(i + 1)));
		}
	}

	@Override
	protected void cruzaPadres(Individuo padre1, Individuo padre2) {
		
		ArrayList<Integer> cromoPadre1 = padre1.getCromosoma();
		ArrayList<Integer> cromoPadre2 = padre2.getCromosoma();
		/*this.longCromo = 9;
		ArrayList<Integer> cromoPadre1 = new ArrayList<Integer>(longCromo);
		ArrayList<Integer> cromoPadre2 = new ArrayList<Integer>(longCromo);
		for (int i = 0; i < longCromo; i++) {
			cromoPadre2.add(i + 1);
		}
		cromoPadre1.add(4);
		cromoPadre1.add(5);
		cromoPadre1.add(2);
		cromoPadre1.add(1);
		cromoPadre1.add(8);
		cromoPadre1.add(7);
		cromoPadre1.add(6);
		cromoPadre1.add(9);
		cromoPadre1.add(3);*/
		
		longCromo = cromoPadre1.size();
		
		ArrayList<Integer> cromoPadre1Aux = new ArrayList<Integer>(longCromo);
		ArrayList<Integer> cromoPadre2Aux = new ArrayList<Integer>(longCromo);
		
		ArrayList<Integer> listaDinamica1 = new ArrayList<Integer>(longCromo);
		ArrayList<Integer> listaDinamica1Aux = new ArrayList<Integer>(longCromo);
		ArrayList<Integer> listaDinamica2 = new ArrayList<Integer>(longCromo);
		ArrayList<Integer> listaDinamica2Aux = new ArrayList<Integer>(longCromo);
		
		for (int i = 0; i < longCromo; i++) {
			
			cromoPadre1Aux.add(cromoPadre1.get(i));
			cromoPadre2Aux.add(cromoPadre2.get(i));
			
			listaDinamica1.add(i);
			listaDinamica1Aux.add(i);
			listaDinamica2.add(i);
			listaDinamica2Aux.add(i);
		}
		
		ArrayList<Integer> cromoHijo1Aux = new ArrayList<Integer>(longCromo);
		ArrayList<Integer> cromoHijo2Aux = new ArrayList<Integer>(longCromo);
		
		int index1;
		int index2;
		for (int i = 0; i < longCromo; i++) {
			
			index1 = listaDinamica1.indexOf(cromoPadre1Aux.get(i));
			cromoHijo1Aux.add(index1);
			listaDinamica1.remove(index1);
			
			index2 = listaDinamica2.indexOf(cromoPadre2Aux.get(i));
			cromoHijo2Aux.add(index2);
			listaDinamica2.remove(index2);
		}
		
		//Hacer Cruce Monopunto
		cruceMonopunto(cromoHijo1Aux, cromoHijo2Aux);
		ArrayList<Integer> cromoHijo1 = new ArrayList<Integer>(longCromo);
		ArrayList<Integer> cromoHijo2 = new ArrayList<Integer>(longCromo);
		
		int a;
		int b;
		for (int i = 0; i < longCromo; i++) {
			
			a = listaDinamica1Aux.get(cromoHijo1Aux.get(i));
			cromoHijo1.add(a);
			//Problema grave aqui (creo que ya lo solucione
			listaDinamica1Aux.remove((Object) a);
			
			b = listaDinamica2Aux.get(cromoHijo2Aux.get(i));
			cromoHijo2.add(b);
			listaDinamica2Aux.remove((Object) b);
		}
		
		sustituyePadres(padre1, padre2, cromoHijo1, cromoHijo2, cromoPadre1Aux, cromoPadre2Aux);
	}

	private void cruceMonopunto(ArrayList<Integer> cromoPadre1, ArrayList<Integer> cromoPadre2) {
		
		Random rand = new Random();
		//int puntoCruce = rand.nextInt(longCromo - 2) + 1;
		int puntoCruce = 4;
		int aux;
		for (int i = puntoCruce; i < longCromo; i++) {
			
			aux = cromoPadre1.get(i);
			cromoPadre1.set(i, cromoPadre2.get(i));
			cromoPadre2.set(i, aux);
		}
	}
	/**************************** GET & SET ********************************/
}
