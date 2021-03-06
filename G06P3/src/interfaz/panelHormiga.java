package interfaz;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

import algoritmoGenetico.misc.Pair;

@SuppressWarnings("serial")
public class panelHormiga extends JPanel{

	private final static int tamaņoCelda = 20;
	private final static int tamaņoTablero = 32;
	
	private int[][] tablero;
	private ArrayList<Pair> camino;
	
	 public panelHormiga(int[][] tablero, ArrayList<Pair> camino) {
		 
		 setVisible(true);
		 this.tablero = tablero;
		 this.camino = camino;
	 } 
	 
	public void paint(Graphics g)    {  
		
		 for ( int x = tamaņoCelda; x <= tamaņoCelda * tamaņoTablero; x += tamaņoCelda) {
			 
			 for ( int y = tamaņoCelda; y <= tamaņoCelda * tamaņoTablero; y += tamaņoCelda ) 
			 { 
				 g.setColor(Color.black);
				 g.drawRect(x, y + tamaņoCelda, tamaņoCelda, tamaņoCelda);
				 g.setColor(Color.gray);
				 g.fill3DRect(x + 1, y + tamaņoCelda + 1, tamaņoCelda - 1, tamaņoCelda - 1, true);
				
			 }
		 }
		 g.setColor(Color.black);
		 
		 for (int i = 0; i < tamaņoTablero; i++) {
			 for (int j = 0; j < tamaņoTablero; j++) {
				 
				 if (tablero[i][j] == 1) {
					 g.fillRect(tamaņoCelda * i + tamaņoCelda, tamaņoCelda * j + tamaņoCelda*2, tamaņoCelda - 1, tamaņoCelda - 1);
				 }
			 }
			 
		 }

		 
		 for (Pair pos : camino) {
			 
			 int x = pos.getFirst();
			 int y = pos.getSecond();
			 
			 if (tablero[x][y] == 1) {
				 
				 g.setColor(Color.red);
				 g.fillRect(tamaņoCelda * x + tamaņoCelda, tamaņoCelda * y + tamaņoCelda*2, tamaņoCelda - 1, tamaņoCelda - 1);
			 }
			 else {
				 
				 g.setColor(Color.yellow);
				 g.fillRect(tamaņoCelda * x + tamaņoCelda, tamaņoCelda * y + tamaņoCelda*2, tamaņoCelda - 1, tamaņoCelda - 1);
			 }
			 g.fillRect(tamaņoCelda * x + tamaņoCelda, tamaņoCelda * y + tamaņoCelda*2, tamaņoCelda - 1, tamaņoCelda - 1);
		 }
		 
		/* for(Pair p : casillasComida) {
			 g.fillRect(tamaņoCelda * p.get_y() + 1, tamaņoCelda * p.get_x() + tamaņoCelda + 1, tamaņoCelda - 1, tamaņoCelda - 1);
		 }
		 
		 g.setColor(Color.yellow);
		 
		 for(Pair p : casillasHormiga) {
			 g.fillRect(tamaņoCelda * p.get_y() + 1, tamaņoCelda * p.get_x() + tamaņoCelda + 1, tamaņoCelda - 1, tamaņoCelda - 1);
		 }
		 
		 g.setColor(Color.red);
		 
		 for(Pair comida : casillasComida) {
			 for(Pair hormiga : casillasHormiga) {
				 if(comida.equals(hormiga)) {
					 g.fillRect(tamaņoCelda * comida.get_y(), tamaņoCelda * comida.get_x() + tamaņoCelda , tamaņoCelda - 1, tamaņoCelda - 1);
				 }
			 }
		 }*/
	}

	/*public void repaint(Graphics g) {
		g.setColor(Color.yellow);
		 
		 for(Pair p : casillasHormiga) {
			 g.fillRect(tamaņoCelda * p.get_y() + 1, tamaņoCelda * p.get_x() + tamaņoCelda + 1, tamaņoCelda - 1, tamaņoCelda - 1);
		 }
	}*/
	
	public void setCaminoHormiga(ArrayList<Pair> camino) {
		this.camino = camino;
	}
}
