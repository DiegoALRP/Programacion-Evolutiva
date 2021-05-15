package interfaz;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.List;
import java.util.ArrayList;

import javax.swing.JPanel;

import algoritmoGenetico.misc.Pair;

public class panelHormiga extends JPanel{

	private final static int tama�oCelda = 20;
	private final static int tama�oTablero = 32;
	
	private int[][] tablero;
	private ArrayList<Pair<Integer, Integer>> camino;
	
	 public panelHormiga(int[][] tablero, ArrayList<Pair<Integer, Integer>> camino) {
		 
		 setVisible(true);
		 this.tablero = tablero;
		 this.camino = camino;
	 } 
	 
	public void paint(Graphics g)    {  
		
		 for ( int x = tama�oCelda; x <= tama�oCelda * tama�oTablero; x += tama�oCelda) {
			 
			 for ( int y = tama�oCelda; y <= 640; y += tama�oCelda ) 
			 { 
				 g.setColor(Color.black);
				 g.drawRect(x, y + tama�oCelda, tama�oCelda, tama�oCelda);
				 g.setColor(Color.gray);
				 g.fill3DRect(x + 1, y + tama�oCelda + 1, tama�oCelda - 1, tama�oCelda - 1, true);
				
			 }
		 }
		 g.setColor(Color.black);
		 
		 for (int i = 0; i < tama�oTablero; i++) {
			 for (int j = 0; j < tama�oTablero; j++) {
				 
				 if (tablero[i][j] == 1) {
					 g.fillRect(tama�oCelda * i + tama�oCelda, tama�oCelda * j + tama�oCelda*2, tama�oCelda - 1, tama�oCelda - 1);
				 }
			 }
			 
		 }

		 
		 for (Pair<Integer, Integer> pos : camino) {
			 
			 int x = pos.getFirst();
			 int y = pos.getSecond();
			 
			 if (tablero[x][y] == 1) {
				 
				 g.setColor(Color.red);
				 g.fillRect(tama�oCelda * x + tama�oCelda, tama�oCelda * y + tama�oCelda*2, tama�oCelda - 1, tama�oCelda - 1);
			 }
			 else {
				 
				 g.setColor(Color.yellow);
				 g.fillRect(tama�oCelda * x + tama�oCelda, tama�oCelda * y + tama�oCelda*2, tama�oCelda - 1, tama�oCelda - 1);
			 }
			 g.fillRect(tama�oCelda * x + tama�oCelda, tama�oCelda * y + tama�oCelda*2, tama�oCelda - 1, tama�oCelda - 1);
		 }
		 
		/* for(Pair p : casillasComida) {
			 g.fillRect(tama�oCelda * p.get_y() + 1, tama�oCelda * p.get_x() + tama�oCelda + 1, tama�oCelda - 1, tama�oCelda - 1);
		 }
		 
		 g.setColor(Color.yellow);
		 
		 for(Pair p : casillasHormiga) {
			 g.fillRect(tama�oCelda * p.get_y() + 1, tama�oCelda * p.get_x() + tama�oCelda + 1, tama�oCelda - 1, tama�oCelda - 1);
		 }
		 
		 g.setColor(Color.red);
		 
		 for(Pair comida : casillasComida) {
			 for(Pair hormiga : casillasHormiga) {
				 if(comida.equals(hormiga)) {
					 g.fillRect(tama�oCelda * comida.get_y(), tama�oCelda * comida.get_x() + tama�oCelda , tama�oCelda - 1, tama�oCelda - 1);
				 }
			 }
		 }*/
	}

	/*public void repaint(Graphics g) {
		g.setColor(Color.yellow);
		 
		 for(Pair p : casillasHormiga) {
			 g.fillRect(tama�oCelda * p.get_y() + 1, tama�oCelda * p.get_x() + tama�oCelda + 1, tama�oCelda - 1, tama�oCelda - 1);
		 }
	}*/
}
