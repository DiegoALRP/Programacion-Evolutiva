package misc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class rastroSantaFe {

	private ArrayList<Pair> comida;
	private String[][] tablero;
	
	private final static int tamaņoTablero = 32;
	
	public rastroSantaFe() {
		
		comida = new ArrayList<Pair>();
		tablero = new String[tamaņoTablero][tamaņoTablero];
		
		int x = 0;
		String pathString = System.getProperty("user.dir") + File.separator + "src" + File.separator + "Rastro de Santa Fe";
		File fichero = new File(pathString);
		
		 try {
		        BufferedReader in;
		        in = new BufferedReader(new FileReader(fichero));
		        
		        String lineaLeida = in.readLine();
		        
		        while (lineaLeida != null) {
		        	String[] line = lineaLeida.split(" ");
		        	for(int i = 0; i < line.length; i++) {
		        		tablero[x][i] = line[i];
		        		if(line[i].equals("#")) {
		        			comida.add(new Pair(i + 1, x + 1));
		        		}
		        	}
		            lineaLeida = in.readLine();
		            x++;
		        }
		    } catch (Exception ex) {
		        ex.printStackTrace();
		    }
	}
	
	public ArrayList<Pair> getComida(){
		return this.comida;
	}
	
	public String[][] getTablero(){
		return this.tablero;
	}
}
