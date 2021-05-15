package interfaz;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.List;
import java.util.ArrayList;

import javax.swing.JPanel;

import algoritmoGenetico.misc.Pair;

public class panelHormiga extends JPanel{

	private final static int tamañoCelda = 20;
	private final static int tamañoTablero = 32;
	
	private int[][] tablero;
	private ArrayList<Pair<Integer, Integer>> camino;
	
	 public panelHormiga(int[][] tablero, ArrayList<Pair<Integer, Integer>> camino) {
		 
		 setVisible(true);
		 this.tablero = tablero;
		 this.camino = camino;
	 } 
	 
	public void paint(Graphics g)    {  
		
		 for ( int x = tamañoCelda; x <= tamañoCelda * tamañoTablero; x += tamañoCelda) {
			 
			 for ( int y = tamañoCelda; y <= 640; y += tamañoCelda ) 
			 { 
				 g.setColor(Color.black);
				 g.drawRect(x, y + tamañoCelda, tamañoCelda, tamañoCelda);
				 g.setColor(Color.gray);
				 g.fill3DRect(x + 1, y + tamañoCelda + 1, tamañoCelda - 1, tamañoCelda - 1, true);
				
			 }
		 }
		 g.setColor(Color.black);
		 
		 for (int i = 0; i < tamañoTablero; i++) {
			 for (int j = 0; j < tamañoTablero; j++) {
				 
				 if (tablero[i][j] == 1) {
					 g.fillRect(tamañoCelda * i + tamañoCelda, tamañoCelda * j + tamañoCelda*2, tamañoCelda - 1, tamañoCelda - 1);
				 }
			 }
			 
		 }

		 
		 for (Pair<Integer, Integer> pos : camino) {
			 
			 int x = pos.getFirst();
			 int y = pos.getSecond();
			 
			 if (tablero[x][y] == 1) {
				 
				 g.setColor(Color.red);
				 g.fillRect(tamañoCelda * x + tamañoCelda, tamañoCelda * y + tamañoCelda*2, tamañoCelda - 1, tamañoCelda - 1);
			 }
			 else {
				 
				 g.setColor(Color.yellow);
				 g.fillRect(tamañoCelda * x + tamañoCelda, tamañoCelda * y + tamañoCelda*2, tamañoCelda - 1, tamañoCelda - 1);
			 }
			 g.fillRect(tamañoCelda * x + tamañoCelda, tamañoCelda * y + tamañoCelda*2, tamañoCelda - 1, tamañoCelda - 1);
		 }
		 
		/* for(Pair p : casillasComida) {
			 g.fillRect(tamañoCelda * p.get_y() + 1, tamañoCelda * p.get_x() + tamañoCelda + 1, tamañoCelda - 1, tamañoCelda - 1);
		 }
		 
		 g.setColor(Color.yellow);
		 
		 for(Pair p : casillasHormiga) {
			 g.fillRect(tamañoCelda * p.get_y() + 1, tamañoCelda * p.get_x() + tamañoCelda + 1, tamañoCelda - 1, tamañoCelda - 1);
		 }
		 
		 g.setColor(Color.red);
		 
		 for(Pair comida : casillasComida) {
			 for(Pair hormiga : casillasHormiga) {
				 if(comida.equals(hormiga)) {
					 g.fillRect(tamañoCelda * comida.get_y(), tamañoCelda * comida.get_x() + tamañoCelda , tamañoCelda - 1, tamañoCelda - 1);
				 }
			 }
		 }*/
	}

	/*public void repaint(Graphics g) {
		g.setColor(Color.yellow);
		 
		 for(Pair p : casillasHormiga) {
			 g.fillRect(tamañoCelda * p.get_y() + 1, tamañoCelda * p.get_x() + tamañoCelda + 1, tamañoCelda - 1, tamañoCelda - 1);
		 }
	}*/
}
