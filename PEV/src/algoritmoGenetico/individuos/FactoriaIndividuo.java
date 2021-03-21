package algoritmoGenetico.individuos;

public class FactoriaIndividuo {

	public static Individuo<?> getIndividuo(String id) {
		
		switch(id) {
			
		case "Funcion 1": return new IndividuoFuncion1();
        case "Funcion Schubert": return new IndividuoFuncion2();
        case "Funcion Holder table": return new IndividuoFuncion3();
        case "Funcion Michalewicz": return new IndividuoFuncion4(6);
        case "Funcion Michalewicz (Booleanos)": return new IndividuoFuncion4(6);
        case "Funcion Michalewicz (Reales)": return new IndividuoFuncion4(6);
        case "Funcion Bukin" : return new IndividuoFuncionBukin();
        case "Funcion Matyas" : return new IndividuoFuncionMatyas();
        default: return new IndividuoFuncion1();
		}
	}
}
