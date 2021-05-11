package prueba;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

 public class vista extends JFrame {

	 public vista()    {       
		 setSize( 1000, 1000 );
		 setVisible( true );   
	 } 
	public void paint( Graphics g )    {  
	 for ( int x = 20; x <= 640; x += 20 )
		 for ( int y = 20; y <= 640; y += 20 ) 
		 { 
			 g.setColor(Color.black);
			 g.drawRect( x, y + 20, 20, 20 );
			 g.setColor(Color.gray);
			 g.fill3DRect(x +1, y + 21, 19, 19, true);
			
		 }
	 g.setColor(Color.yellow);
	 
	 ArrayList<Pair> a = new ArrayList<Pair>(); // hormiga
	 Pair p = new Pair(5,5);
	 Pair p2 = new Pair(8,32);
	 
	 a.add(p2);
	 a.add(p);

	 for (Pair p3 : a ) {
		 g.fillRect(20 * p3.get_first() + 1, 20 * p3.get_second() + 21, 19, 19);

	 }
} 
 public static void main( String args[] ) {
     vista application = new vista();
     application.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );   
     }  
 } 