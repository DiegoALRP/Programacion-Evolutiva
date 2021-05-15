package interfaz;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.List;
import java.util.ArrayList;

import javax.swing.JPanel;

import misc.Pair;

public class panelHormiga extends JPanel{

	private final static int tamañoCelda = 20;
	private final static int tamañoTablero = 32;
	
	private ArrayList<Pair> casillasComida;
	private ArrayList<Pair> casillasHormiga;
	
	 public panelHormiga(ArrayList<Pair> casillasComida)    {       
		 setVisible(true);   
		 this.casillasComida = casillasComida;
	 } 
	 
	public void paint(Graphics g)    {  
		 for ( int x = tamañoCelda; x <= tamañoCelda * tamañoTablero; x += tamañoCelda)
			 for ( int y = tamañoCelda; y <= 640; y += tamañoCelda ) 
			 { 
				 g.setColor(Color.black);
				 g.drawRect(x, y + tamañoCelda, tamañoCelda, tamañoCelda);
				 g.setColor(Color.gray);
				 g.fill3DRect(x + 1, y + tamañoCelda + 1, tamañoCelda - 1, tamañoCelda - 1, true);
				
			 }
		 g.setColor(Color.black);
		 
		 for(Pair p : casillasComida) {
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
		 }
	}

	public void repaint(Graphics g) {
		g.setColor(Color.yellow);
		 
		 for(Pair p : casillasHormiga) {
			 g.fillRect(tamañoCelda * p.get_y() + 1, tamañoCelda * p.get_x() + tamañoCelda + 1, tamañoCelda - 1, tamañoCelda - 1);
		 }
	}
	
	public void setCaminoHormiga(ArrayList<Pair> casillas) {
		this.casillasHormiga = casillas;
	}
}
