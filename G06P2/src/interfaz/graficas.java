package interfaz;

import java.awt.Font;

import org.math.plot.*;

public class graficas {

	private Plot2DPanel plot;
	double[] x;
	
	public graficas() {
		
		plot = new Plot2DPanel();
		plot.addLegend("SOUTH");
		plot.setAxisLabel(0, "Numero de generaciones");
		plot.setAxisLabel(1, "Fitness");
		plot.getAxis(0).setLabelFont(new Font("Courier", Font.BOLD, 15));
		plot.getAxis(1).setLabelFont(new Font("Courier", Font.BOLD, 15));
		plot.setFont(new Font("Courier", Font.BOLD, 15));
		plot.getAxis(0).setLabelPosition(0.5, -0.1);
	}
	
	public Plot2DPanel getPlot() {
		return this.plot;
	}
}
