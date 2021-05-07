package interfaz;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.List;
import java.util.ArrayList;

import javax.swing.JPanel;

import misc.Pair;

public class panelHormiga extends JPanel{

	private final static int tama�oCelda = 20;
	private final static int tama�oTablero = 32;
	
	private ArrayList<Pair> casillasComida;
	private ArrayList<Pair> casillasHormiga;
	
	 public panelHormiga(ArrayList<Pair> casillasComida)    {       
		 setVisible(true);   
		 this.casillasComida = casillasComida;
	 } 
	 
	public void paint(Graphics g)    {  
		 for ( int x = tama�oCelda; x <= tama�oCelda * tama�oTablero; x += tama�oCelda)
			 for ( int y = tama�oCelda; y <= 640; y += tama�oCelda ) 
			 { 
				 g.setColor(Color.black);
				 g.drawRect( x, y + tama�oCelda, tama�oCelda, tama�oCelda);
				 g.setColor(Color.gray);
				 g.fill3DRect(x + 1, y + tama�oCelda + 1, tama�oCelda - 1, tama�oCelda - 1, true);
				
			 }
		 g.setColor(Color.black);
		 
		 for(Pair p : casillasComida) {
			 g.fillRect(tama�oCelda * p.get_first() + 1, tama�oCelda * p.get_second() + tama�oCelda + 1, tama�oCelda - 1, tama�oCelda - 1);
		 }
		 
		 g.setColor(Color.yellow);
		 
		 for(Pair p : casillasHormiga) {
			 g.fillRect(tama�oCelda * p.get_first() + 1, tama�oCelda * p.get_second() + tama�oCelda + 1, tama�oCelda - 1, tama�oCelda - 1);
		 }
	}
	
	/*public void pintaCamino(Graphics g, ArrayList<Pair> casillasComida) {
		g.setColor(Color.yellow);
		 
		 for(Pair p : casillasComida) {
			 g.fillRect(tama�oCelda * p.get_first() + 1, tama�oCelda * p.get_second() + tama�oCelda + 1, tama�oCelda - 1, tama�oCelda - 1);
		 }
	}*/
	
	public void repaint(Graphics g) {
		g.setColor(Color.yellow);
		 
		 for(Pair p : casillasHormiga) {
			 g.fillRect(tama�oCelda * p.get_first() + 1, tama�oCelda * p.get_second() + tama�oCelda + 1, tama�oCelda - 1, tama�oCelda - 1);
		 }
	}
	
	public void setCaminoHormiga(ArrayList<Pair> casillas) {
		this.casillasHormiga = casillas;
	}
}
