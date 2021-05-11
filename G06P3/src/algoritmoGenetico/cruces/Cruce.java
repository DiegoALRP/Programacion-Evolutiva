package algoritmoGenetico.cruces;

import java.util.ArrayList;
import java.util.Random;

import algoritmoGenetico.individuos.Individuo;

public class Cruce {

	protected ArrayList<Integer> selec_cruce;	//Array de enteros que contiene el �ndice de los individuos de la poblaci�n
	protected double probCruce;					//Probabilidad de Cruce
	protected int punto_cruce;					//Punto de Cruce
	protected int num_selec_cruce;				//Numero de individuos seleccionados para cruzar
	protected int tamPoblacion;					//Tama�o de la poblaci�n
	protected int numCruce;				//Tama�o de la poblaci�n

		
	protected void seleccionaIndividuos(ArrayList<Individuo> poblacion) {
			
			this.tamPoblacion = poblacion.size();
			Random rand = new Random();
			
			for (int i = 0; i < tamPoblacion; i++) {
				
				if (rand.nextDouble() < this.probCruce) {
	
					if (this.num_selec_cruce % 2 == 1) {
						
						/*if (!poblacion.get(i).getCromosoma().equals(poblacion.get(this.selec_cruce.get(this.num_selec_cruce - 1)).getCromosoma())) {
							
							this.selec_cruce.add(i);
							this.num_selec_cruce++;
						}*/
					}
					else {
						
						this.selec_cruce.add(i);
						this.num_selec_cruce++;
					}
				}
			}
	}
	
	public void cruza(ArrayList<Individuo> poblacion, double probCruce) {
			
			this.num_selec_cruce = 0;
			this.probCruce = probCruce;
			this.selec_cruce = new ArrayList<Integer>();
			this.tamPoblacion = poblacion.size();
			
			this.seleccionaIndividuos(poblacion);
			
			for (int i = 0; i < this.num_selec_cruce; i += 2) {
				this.numCruce++;
				cruzaPadres(poblacion.get(selec_cruce.get(i)), poblacion.get(selec_cruce.get(i + 1)));
			}
		}
	
	private void cruzaPadres(Individuo padre, Individuo padre2) {
		
	}
}
