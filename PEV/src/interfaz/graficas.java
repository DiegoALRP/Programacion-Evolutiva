package interfaz;

import javax.swing.*;
import org.math.plot.*;

public class graficas{
	
	private Plot2DPanel plot;
	
	graficas(){
		double[] x = { 1, 2, 3, 4, 5, 6 };
		double[] y = { 45, 89, 6, 32, 63, 12 };
		
		plot = new Plot2DPanel();
		
		// define the legend position
		plot.addLegend("SOUTH");
		// add a line plot to the PlotPanel
		
		plot.addLinePlot("my plot", x, y);
		
		// put the PlotPanel in a JFrame like a JPanel
		//JFrame frame = new JFrame("a plot panel");
		//frame.setSize(100, 100);
		//frame.setContentPane(plot);
		//frame.setVisible(true);
	}
	
	public Plot2DPanel getPlot() {
		return this.plot;
	}

}

