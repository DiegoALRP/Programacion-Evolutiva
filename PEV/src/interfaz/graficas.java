package interfaz;

import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import org.math.plot.*;

public class graficas{
	
	private Plot2DPanel plot;
	double[] x;
	
	graficas(int numGeneraciones){
		
		x = new double[numGeneraciones];
		plot = new Plot2DPanel();
		plot.addLegend("SOUTH");
		
	}
	
	public Plot2DPanel getPlot() {
		return this.plot;
	}

	public void actualiza(int numGeneraciones, double[] mejorAbsoluto, double[] mejorGeneracion, double[] mediaGeneracion) {
		
		x = new double[numGeneraciones];
		
		plot.addLinePlot("Mejor absoluto", x, mejorAbsoluto);
		plot.addLinePlot("Mejor generacion", x, mejorGeneracion);
		plot.addLinePlot("Media generacion", x, mediaGeneracion);
		
	}

}

