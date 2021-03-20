package algoritmoGenetico.individuos;

public class FactoriaIndividuo {

	public static Individuo<?> getIndividuo(String id) {
		
		switch(id) {
			
		case "Funcion 1": return new IndividuoFuncion1();
        case "Funcion Schubert": return new IndividuoFuncion2();
        case "Funcion Holder table": return new IndividuoFuncion3();
        case "Funcion Michalewicz": return new IndividuoFuncion4(6);
        default: return new IndividuoFuncion1();
		}
	}
}
