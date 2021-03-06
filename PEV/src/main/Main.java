package main;

//import java.lan
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		double max = 12.1;
		double min = -3.0;
		double precision = 1.0;
		
		System.out.println(Math.log10(((max - min)/precision)+1)/Math.log10(2));
		
		int dec = Integer.parseInt("1010", 2);
		
		System.out.println("Num: " + dec);
	}

}
