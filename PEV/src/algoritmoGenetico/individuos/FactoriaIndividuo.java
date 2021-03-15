package algoritmoGenetico.individuos;

public class FactoriaIndividuo {

	public static Individuo<?> getIndividuo(String id) {
		
		switch(id) {
			
			case "Funcion1": return new IndividuoFuncion1();
			default: return new IndividuoFuncion1();
		}
	}
}
