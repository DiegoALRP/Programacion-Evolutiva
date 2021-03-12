package algoritmoGenetico.cruces;



public class FactoriaCruces {
	public static Cruce getAlgoritmoDeCruce(String algoritmo, int participantes) {
		switch(algoritmo) {
			case "Discreto Uniforme":
				return new CruceUniforme();
			case "Aritmetico":
				return new CruceAritmetico();
			case "BLX-alpha":
				return new CruceBLX();
			default:
				return new CruceMonopunto();
		}
	}
}
