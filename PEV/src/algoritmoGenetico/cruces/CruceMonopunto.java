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
 * Clase Cruce Monopunto.
 * 
 * @author 
 * Grupo G06:
 * 	-Miguel Robledo.
 * 	-Diego Alejandro Rodríguez Pereira.
 *
 */
public class CruceMonopunto extends Cruce {
	
	
	/**
	 * [ES] Esta es una función abstracta y es la principal método de las clases de cruces.
	 * A partir de esta función se seleccionan y cruzan los individuos.
	 * En la clase "CruceMonopunto" se modifica para permitir seleccionar un punto de cruce.
	 * 
	 * [EN] This is an abstract function and it is the principal function of the cross classes.
	 * From here we select and cross the individuals.
	 * In the class "CruceMonopunto" it's modified to allow select a crossover's point.
	 * 
	 * @param poblacion	[ES] Población a cruzar.
	 * 					[EN] Population to cross.
	 * @param probCruce	[ES] Probabilidad de cruce.
	 * 					[EN] Crossover probability.
	 */
	@Override
	public void cruza(ArrayList<Individuo> poblacion, double probCruce) {
		
		this.num_selec_cruce = 0;
		this.probCruce = probCruce;
		this.selec_cruce = new ArrayList<Integer>();
		this.tamPoblacion = poblacion.size();
		
		int longitudCromosoma = poblacion.get(0).getLongitudCromosoma();
		
		this.seleccionaIndividuos(poblacion);
		
		Random rand = new Random();
		if (longitudCromosoma == 2) {
			this.punto_cruce = 1;
		}
		else if (longitudCromosoma < 2) {
			this.punto_cruce = 0;
		}
		else {
			this.punto_cruce = rand.nextInt(longitudCromosoma - 2) + 1;
		}
		
		for (int i = 0; i < this.num_selec_cruce; i += 2) {
			
			cruzaPadres(poblacion.get(selec_cruce.get(i)), poblacion.get(selec_cruce.get(i + 1)));
		}
	}
	
	@Override
	protected void cruzaPadres(Individuo padre1, Individuo padre2) {
		
		ArrayList cromoPadre1 = padre1.getCromosoma();
		ArrayList cromoPadre2 = padre2.getCromosoma();
		
		ArrayList cromoPadre1Aux = new ArrayList();
		cromoPadre1Aux.addAll(cromoPadre1);
		
		ArrayList cromoPadre2Aux = new ArrayList();
		cromoPadre2Aux.addAll(cromoPadre2);
		
		ArrayList cromoHijo1 = new ArrayList();
		cromoHijo1.addAll(cromoPadre1Aux);

		ArrayList cromoHijo2 = new ArrayList();
		cromoHijo2.addAll(cromoPadre2Aux);
		
		int longitudCromo = padre1.getLongitudCromosoma();
		int longi = this.punto_cruce;
		while (longi < longitudCromo) {
			
			cromoHijo1.set(longi, cromoPadre2Aux.get(longi));
			cromoHijo2.set(longi, cromoPadre1Aux.get(longi));
			longi++;
		}
		
		sustituyePadres(padre1, padre2, cromoHijo1, cromoHijo2, cromoPadre1Aux, cromoPadre2Aux);
	}
}
