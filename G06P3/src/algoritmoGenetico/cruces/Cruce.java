package algoritmoGenetico.cruces;

import java.util.ArrayList;
import java.util.Random;

import algoritmoGenetico.individuos.Arbol;
import algoritmoGenetico.individuos.Individuo;
import algoritmoGenetico.individuos.Operando;

/**
 * Universidad Complutense de Madrid.
 * Programación Evolutiva.
 * Grupo A 2021.
 * Profesor:
 * 	-Carlos Cervigon Ruckauer.
 * 
 * Práctica 3
 * 
 * Clase Cruce.
 * 
 * @author 
 * Grupo G06:
 * 	-Miguel Robledo.
 * 	-Diego Alejandro Rodríguez Pereira.
 *
 */
public class Cruce {

	/******************************** ATRIBUTTES ********************************/
	protected ArrayList<Integer> selec_cruce;	//Array de enteros que contiene el indice de los individuos de la poblacion
	protected double probCruce;					//Probabilidad de Cruce
	protected int punto_cruce;					//Punto de Cruce
	protected int num_selec_cruce;				//Numero de individuos seleccionados para cruzar
	protected int tamPoblacion;					//Tamanno de la poblacion
	protected int numCruce;
	
	/******************************* CONSTRUCTOR ********************************/
	
	/********************************* METHODS *********************************/
	public void cruza(ArrayList<Individuo> poblacion, double probCruce) {
		
		this.num_selec_cruce = 0;
		this.probCruce = probCruce;
		this.selec_cruce = new ArrayList<Integer>();
		this.tamPoblacion = poblacion.size();
		
		this.seleccionaIndividuos(poblacion);
	}
	
	public void cruzaPadres(Individuo padre1, Individuo padre2) {
		
		Arbol asub1 = padre1.getCromosoma().getSubTree(90);
		Arbol asub1Padre = asub1.getPadre();
		
		Arbol asub2 = padre2.getCromosoma().getSubTree(90);
		Arbol asub2Padre = asub2.getPadre();
		
		asub1Padre.insertNewTree(asub2, asub1Padre.getIndex(asub1.getRaiz()));
		asub2Padre.insertNewTree(asub1, asub2Padre.getIndex(asub2.getRaiz()));
	}
	
	/********************************* AUXILIARY METHODS *********************************/
	
	/**
	 * [ES] Esta funciï¿½n se extiende a todas las subclases de la clase cruce. Su funciï¿½n es seleccionar a los
	 * individuos a cruzar. Cada individuo se selecciona con una probabilidad (la probabilidad de cruce que pasamos
	 * como parï¿½metro en la funciï¿½n principal). Y almacenamos el ï¿½ndice del individuo en un array.
	 * Este mï¿½todo incluye una variaciï¿½n, y es que no cruza individuos con mismo cromosoma.
	 * 
	 * [EN] This function extends to all subclasses of the cruce's class. It's function it's to select the
	 * individuals to cross. Each individual is selected with a probability (the crossover probability that we pass
	 * as parameter of the principal function. And we store the index of the individual in an array.
	 * This method includes a variation, it doens't crossover individuals with the same chromosome.
	 * 
	 * @param poblacion	[ES] La poblaciï¿½n de la cual queremos seleccionar a los individuos a cruzar.
	 * 					[EN] The population from which we select the individuals to cross.
	 */
	private void seleccionaIndividuos(ArrayList<Individuo> poblacion) {
		
		Random rand = new Random();
		
		for (int i = 0; i < tamPoblacion; i++) {
			
			if (rand.nextDouble() < this.probCruce) {

				if (this.num_selec_cruce % 2 == 1) {
					
					ArrayList<Operando> cromo1 = poblacion.get(i).getRefFenotipe();
					ArrayList<Operando> cromo2 = poblacion.get(this.selec_cruce.get(this.num_selec_cruce - 1)).getRefFenotipe();
					
					if (cromo1.size() != cromo2.size()) {
						
						this.selec_cruce.add(i);
						this.num_selec_cruce++;
					}
					else {
						
						boolean iguales = false;
						int cromoLength = cromo1.size();
						for (int k = 0; k < cromoLength && !iguales; k++) {
							
							if (cromo1.get(k).equals(cromo2.get(k))) {
								
								iguales = true;
							}
						}
						
						if (!iguales) {
							
							this.selec_cruce.add(i);
							this.num_selec_cruce++;
						}
					}
				}
				else {
					
					this.selec_cruce.add(i);
					this.num_selec_cruce++;
				}
			}
		}
		
		if ((num_selec_cruce % 2) == 1) {
			
			this.num_selec_cruce--;
			this.selec_cruce.remove(num_selec_cruce);
		}
	}
	
	
	/*protected void sustituyePadres(Individuo padre1, Individuo padre2, ArrayList<Integer> cromoHijo1, ArrayList<Integer>  cromoHijo2,
			ArrayList<Integer>  cromoPadre1Aux, ArrayList<Integer>  cromoPadre2Aux) {
		
		double fitnessP1 = padre1.getFitness();
		double fitnessP2 = padre2.getFitness();
		
		padre1.setCromosoma(cromoHijo1);
		padre2.setCromosoma(cromoHijo2);
		
		double fitnessH1 = padre1.calculateFitness();
		double fitnessH2 = padre2.calculateFitness();
		
		if (fitnessP1 > fitnessH1) padre1.setCromosoma(cromoPadre1Aux);
		if (fitnessP2 > fitnessH2) padre2.setCromosoma(cromoPadre2Aux);
	}*/
	
	
	/**************************** GETTERS & SETTERS ****************************/
	
}
