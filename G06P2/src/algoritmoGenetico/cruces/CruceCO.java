package algoritmoGenetico.cruces;

import java.util.ArrayList;

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
			listaDinamica2.remove(index1);
		}
		
		//Hacer Cruce Monopunto
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
	}

	/**************************** GET & SET ********************************/
}
