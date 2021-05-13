package interfaz;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.List;
import java.util.ArrayList;

import javax.swing.JPanel;

import misc.Pair;

public class panelHormiga extends JPanel{

	private final static int tamaņoCelda = 20;
	private final static int tamaņoTablero = 32;
	
	private ArrayList<Pair> casillasComida;
	private ArrayList<Pair> casillasHormiga;
	
	 public panelHormiga(ArrayList<Pair> casillasComida)    {       
		 setVisible(true);   
		 this.casillasComida = casillasComida;
	 } 
	 
	public void paint(Graphics g)    {  
		 for ( int x = tamaņoCelda; x <= tamaņoCelda * tamaņoTablero; x += tamaņoCelda)
			 for ( int y = tamaņoCelda; y <= 640; y += tamaņoCelda ) 
			 { 
				 g.setColor(Color.black);
				 g.drawRect(x, y + tamaņoCelda, tamaņoCelda, tamaņoCelda);
				 g.setColor(Color.gray);
				 g.fill3DRect(x + 1, y + tamaņoCelda + 1, tamaņoCelda - 1, tamaņoCelda - 1, true);
				
			 }
		 g.setColor(Color.black);
		 
		 for(Pair p : casillasComida) {
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
		 }
	}

	public void repaint(Graphics g) {
		g.setColor(Color.yellow);
		 
		 for(Pair p : casillasHormiga) {
			 g.fillRect(tamaņoCelda * p.get_y() + 1, tamaņoCelda * p.get_x() + tamaņoCelda + 1, tamaņoCelda - 1, tamaņoCelda - 1);
		 }
	}
	
	public void setCaminoHormiga(ArrayList<Pair> casillas) {
		this.casillasHormiga = casillas;
	}
}
