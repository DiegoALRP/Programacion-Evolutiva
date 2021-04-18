package algoritmoGenetico.cruces;

import java.util.ArrayList;
import java.util.HashSet;
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
 * Clase Cruce Por Orden Prioritario.
 * 
 * @author 
 * Grupo G06:
 * 	-Miguel Robledo.
 * 	-Diego Alejandro Rodríguez Pereira.
 *
 */
public class CruceOXPP extends Cruce {

	private int[] puntos;
	private final int numPuntos = 6;
	private int longCromo;
	private HashSet<Integer> setPuntos;
	@Override
	public void cruza(ArrayList<Individuo> poblacion, double probCruce) {

		this.num_selec_cruce = 0;
		this.probCruce = probCruce;
		this.selec_cruce = new ArrayList<Integer>();
		this.tamPoblacion = poblacion.size();
		
		longCromo = 26;
		
		this.seleccionaIndividuos(poblacion);
		
		Random rand = new Random();
		this.puntos = new int[numPuntos];
		setPuntos = new HashSet<Integer>(numPuntos);
		int punto;
		for (int i = 0; i < numPuntos; i++) {
			
			punto = rand.nextInt(longCromo);
			while(setPuntos.contains(punto)) {
				
				punto = rand.nextInt(longCromo);
			}
			this.puntos[i] = punto;
			setPuntos.add(punto);
		}
		
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
			cromoPadre1.add(i + 1);
		}
		cromoPadre2.add(4);
		cromoPadre2.add(5);
		cromoPadre2.add(2);
		cromoPadre2.add(1);
		cromoPadre2.add(8);
		cromoPadre2.add(7);
		cromoPadre2.add(6);
		cromoPadre2.add(9);
		cromoPadre2.add(3);
		
		this.puntos[0] = 2;
		this.puntos[1] = 3;
		this.puntos[2] = 5;
		this.puntos[3] = 8;
		
		this.setPuntos.clear();
		this.setPuntos.add(3);
		this.setPuntos.add(4);
		this.setPuntos.add(6);
		this.setPuntos.add(9);*/
		
		ArrayList<Integer> cromoPadre1Aux = new ArrayList<Integer>(longCromo);
		ArrayList<Integer> cromoPadre2Aux = new ArrayList<Integer>(longCromo);
		
		ArrayList<Integer> cromoHijo1 = new ArrayList<Integer>(longCromo);
		ArrayList<Integer> cromoHijo2 = new ArrayList<Integer>(longCromo);
		
		ArrayList<Integer> arrayPosHijo1 = new ArrayList<Integer>(numPuntos);
		ArrayList<Integer> arrayPosHijo2 = new ArrayList<Integer>(numPuntos);
		HashSet<Integer> vectorPosHijo1 = new HashSet<Integer>(numPuntos);
		HashSet<Integer> vectorPosHijo2 = new HashSet<Integer>(numPuntos);
		
		int punto;
		for (int i = 0; i < numPuntos; i++) {
			
			punto = this.puntos[i];
			arrayPosHijo1.add(cromoPadre1.get(punto));
			arrayPosHijo2.add(cromoPadre2.get(punto));
			vectorPosHijo1.add(cromoPadre1.get(punto));
			vectorPosHijo2.add(cromoPadre2.get(punto));
		}
		
		int posAdded1 = 0;
		int posAdded2 = 0;
		for (int i = 0; i < longCromo; i++) {
			
			if (vectorPosHijo1.contains(cromoPadre2.get(i))) {
				
				cromoHijo1.add(arrayPosHijo1.get(posAdded1));
				posAdded1++;
			}
			else {
				
				cromoHijo1.add(cromoPadre2.get(i));
			}
			
			if (vectorPosHijo2.contains(cromoPadre1.get(i))) {
				
				cromoHijo2.add(arrayPosHijo2.get(posAdded2));
				posAdded2++;
			}
			else {
				
				cromoHijo2.add(cromoPadre1.get(i));
			}
			
			cromoPadre1Aux.add(cromoPadre1.get(i));
			cromoPadre2Aux.add(cromoPadre2.get(i));
		}
		
		sustituyePadres(padre1, padre2, cromoHijo1, cromoHijo2, cromoPadre1Aux, cromoPadre2Aux);
	}

}
