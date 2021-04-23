package algoritmoGenetico.mutaciones;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

import algoritmoGenetico.individuos.Individuo;

/**
 * Universidad Complutense de Madrid.
 * Programación Evolutiva.
 * Grupo A - 2021.
 * Profesor:
 * 	-Carlos Cervigon Ruckauer.
 * 
 * Práctica 2.
 * 
 * Clase Mutación por Intercambio Doble.
 * 
 * @author 
 * Grupo G06:
 * 	-Miguel Robledo.
 * 	-Diego Alejandro Rodríguez Pereira.
 *
 */
public class MutacionIntercambioDoble extends Mutacion {

	@Override
	protected void mutaIndividuo(Individuo individuo) {
		
		ArrayList<Integer> cromosoma = individuo.getCromosoma();
		int longCromo = cromosoma.size();
		
		Random rand = new Random();
		HashSet<Integer> puntos = new HashSet<Integer>();
		int a = rand.nextInt(longCromo);
		puntos.add(a);
		int b = rand.nextInt();
		while (puntos.contains(b)) {
			b = rand.nextInt();
		}
		puntos.add(b);
		int c = rand.nextInt();
		while (puntos.contains(c)) {
			c = rand.nextInt();
		}
		puntos.add(c);
		int d = rand.nextInt();
		while (puntos.contains(d)) {
			d = rand.nextInt();
		}
		puntos.add(d);
		
		int aux1 = cromosoma.get(a);
		int aux2 = cromosoma.get(c);
		cromosoma.set(a, cromosoma.get(b));
		cromosoma.set(b, aux1);
		cromosoma.set(c, cromosoma.get(d));
		cromosoma.set(d, aux2);
	}

}
