package algoritmoGenetico.individuos;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Universidad Complutense de Madrid.
 * Programación Evolutiva.
 * Grupo A 2021.
 * Profesor:
 * 	-Carlos Cervigon Ruckauer.
 * 
 * Clase Tablero Rastro Santa Fe.
 * 
 * @author 
 * Grupo G06:
 * 	-Miguel Robledo.
 * 	-Diego Alejandro Rodríguez Pereira.
 *
 */
public class RastroSantaFe {

	/****************************************************************************/
	/******************************** ATRIBUTTES ********************************/
	/****************************************************************************/
	
	private int[][] tablero;
	private final static int tamTablero = 32;
	
	private final String filepath = "src/RastroDeSantaFe";
	
	
	/****************************************************************************/
	/******************************* CONSTRUCTOR ********************************/
	/****************************************************************************/
	public RastroSantaFe() {
		
		this.tablero = new int[tamTablero][tamTablero];
		
		try {
			
			this.loadData();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	
	/***************************************************************************/
	/********************************* METHODS *********************************/
	/***************************************************************************/
	private void loadData() throws FileNotFoundException {
		
		FileInputStream file = new FileInputStream(filepath);
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(file);
		
		int j = 0;
		while (sc.hasNextLine()) {
			
			String[] st = sc.nextLine().split(" ");
			for (int i = 0; i < tamTablero; i++) {
				
				if (st[i].equals("#")) {
					tablero[i][j] = 1;
				}
				else {
					tablero[i][j] = 0;
				}
			}
			j++;
		}
	}
	
	
	/***************************************************************************/
	/**************************** GETTERS & SETTERS ****************************/
	/***************************************************************************/
	
	public int[][] getTablero(){
		
		return this.tablero;
	}
}
