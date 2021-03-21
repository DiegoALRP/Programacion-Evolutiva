package interfaz;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import org.math.plot.*;

public class graficas{
	
	private Plot2DPanel plot;
	double[] x;
	
	graficas(int numGeneraciones){
		
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

	public void actualiza(int numGeneraciones, double[] mejorAbsoluto, double[] mejorGeneracion, double[] mediaGeneracion) {
		
		plot.removeAllPlots();
		
		x = new double[numGeneraciones];
		for(int i=0;i<numGeneraciones;i++) {
			x[i]=i;
		}
		
		plot.addLinePlot("Mejor absoluto", x, mejorAbsoluto);
		plot.addLinePlot("Mejor generacion", x, mejorGeneracion);
		plot.addLinePlot("Media generacion", x, mediaGeneracion);
		
		
	}

}

