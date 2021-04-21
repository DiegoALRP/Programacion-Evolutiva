package interfaz;

import java.awt.Font;

import org.math.plot.*;


public class graficas {

	private Plot2DPanel plot;
	double[] x;
	
	public graficas() {
		
		plot = new Plot2DPanel();
		//plot.setLayout(null);
		//plot.setBounds(400, 10, 1000, 403);
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
	
	public void actualiza(int numGeneraciones, double[] mejorAbsoluto, double[] mejorGeneracion, double[] mediaGeneracion, double[] presionSelectiva) {
		
		plot.removeAllPlots();
		
		x = new double[numGeneraciones];
		for(int i=0;i<numGeneraciones;i++) {
			x[i]=i;
		}
		
		plot.addLinePlot("Mejor absoluto", x, mejorAbsoluto);
		plot.addLinePlot("Mejor generacion", x, mejorGeneracion);
		plot.addLinePlot("Media generacion", x, mediaGeneracion);
		plot.addLinePlot("Presion selectiva", x, presionSelectiva);

		
		
	}
}
