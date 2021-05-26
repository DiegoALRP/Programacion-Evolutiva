package prueba;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;

 public class vista extends JFrame {

	 public vista()    {       
		 setSize( 1000, 1000 );
		 
		 JScrollPane scrollPane = new JScrollPane();
		 GroupLayout groupLayout = new GroupLayout(getContentPane());
		 groupLayout.setHorizontalGroup(
		 	groupLayout.createParallelGroup(Alignment.LEADING)
		 		.addGroup(groupLayout.createSequentialGroup()
		 			.addGap(66)
		 			.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 204, GroupLayout.PREFERRED_SIZE)
		 			.addContainerGap(716, Short.MAX_VALUE))
		 );
		 groupLayout.setVerticalGroup(
		 	groupLayout.createParallelGroup(Alignment.LEADING)
		 		.addGroup(groupLayout.createSequentialGroup()
		 			.addGap(72)
		 			.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE)
		 			.addContainerGap(626, Short.MAX_VALUE))
		 );
		 
		 JPanel panel = new JPanel();
		 scrollPane.setViewportView(panel);
		 
		 JLabel lblNewLabel = new JLabel("New label");
		 GroupLayout gl_panel = new GroupLayout(panel);
		 gl_panel.setHorizontalGroup(
		 	gl_panel.createParallelGroup(Alignment.LEADING)
		 		.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
		 			.addContainerGap()
		 			.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE))
		 );
		 gl_panel.setVerticalGroup(
		 	gl_panel.createParallelGroup(Alignment.LEADING)
		 		.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
		 			.addContainerGap(27, Short.MAX_VALUE)
		 			.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE))
		 );
		 panel.setLayout(gl_panel);
		 getContentPane().setLayout(groupLayout);
		 setVisible( true );   
	 } 
	
 public static void main( String args[] ) {
     vista application = new vista();
     application.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );   
     }  
 } 