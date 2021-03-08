package algoritmoGenetico.cruces;

import java.util.ArrayList;
import java.util.Random;

import algoritmoGenetico.individuos.Individuo;
import algoritmoGenetico.individuos.IndividuoFuncion1;

public class CruceMonopunto extends Cruce<Boolean> {

	public CruceMonopunto(double probCruce) {
		
		this.num_selec_cruce = 0;
		this.probCruce = probCruce;
		this.selec_cruce = new ArrayList<Integer>();
	}
	
	@Override
	protected ArrayList<Individuo<Boolean>> seleccionadosCruce(ArrayList<Individuo<Boolean>> poblacion) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected ArrayList<Individuo<Boolean>> cruza(ArrayList<Individuo<Boolean>> poblacion) {
		// TODO Auto-generated method stub
		
		this.seleccionIndividuos(poblacion);
		
		Random rand = new Random();
		
		this.punto_cruce = rand.nextInt(this.tamPoblacion - 2) + 1;
		
		for (int i = 0; i < this.num_selec_cruce; i++) {
			
		}
		return null;
	}
	
	protected void cruzaPadres(Individuo<Boolean> padre1, Individuo<Boolean> padre2) {
		
		Individuo<Boolean> hijo1 = padre1;
		Individuo<Boolean> hijo2 = padre2;
		
		hijo1.
	}

	protected void seleccionIndividuos(ArrayList<Individuo<Boolean>> poblacion) {
		
		this.tamPoblacion = poblacion.size();
		Random rand = new Random();
		
		for (int i = 0; i < tamPoblacion; i++) {
			
			if (this.probCruce > rand.nextInt()) {
				
				this.selec_cruce.add(i);
				this.num_selec_cruce++;
			}
		}
		
		if ((num_selec_cruce % 2) == 1) {
			
			this.num_selec_cruce--;
			this.selec_cruce.remove(num_selec_cruce);
		}
	}
}
