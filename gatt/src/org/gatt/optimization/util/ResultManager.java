package org.gatt.optimization.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Vector;

public class ResultManager {
	private float totalTime;
	private Vector<Double> fitnesses;
	public ResultManager(float totalTime, Vector<Double> fitnesses){
		this.totalTime = totalTime;
		this.fitnesses = fitnesses;
	}
	public void saveResults(){
		File f = new File("resultados.txt");
		PrintWriter out = null;
		try {
			out = new PrintWriter(new FileOutputStream(f));
			out.println("Resultados: ");
			out.println("Tiempo tomado: " + totalTime);
			out.println("Fitnesses: ");
			for(double fitness : fitnesses){
				out.println(fitness);
			}
			out.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
