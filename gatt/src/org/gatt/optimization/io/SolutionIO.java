package org.gatt.optimization.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.jgap.Chromosome;
import org.jgap.IChromosome;
import org.jgap.impl.IntegerGene;

/**
 * @author Chucho
 * Class in charge of serializing a timetabling solution
 */
public class SolutionIO {
	
	/**
	 * file name 
	 */
	private static final String fileName = "solutions/solution.dat";
	
	/**
	 * File object
	 */
	private File file;
	
	/**
	 * constructor
	 */
	public SolutionIO(){
		file = new File(fileName);
	}
	
/*	public void saveSolution(IntegerGene g){
		try{
			Writer out;
			out = new BufferedWriter( new FileWriter(file));
			out.write(g.getPersistentRepresentation());
			out.close();
		}catch(IOException exp){
			exp.printStackTrace();
		}
	}*/
	
	/**
	 * Save the solutions given a genetic algorithm chromosome
	 * @param c
	 * @throws Exception
	 */
	public void saveSolution(IChromosome c) throws Exception{
		System.out.println(file.getAbsolutePath());
		ObjectOutputStream out;
		out = new ObjectOutputStream(new FileOutputStream(file));
		out.writeObject(c);
		out.close();
	}
	
	/**
	 * return a genetic algorithm chromosome build from a serialized timetabling
	 * solution
	 */
	public Chromosome loadSolution(){
		ObjectInputStream in;
		Chromosome c = null;
		try{
			in = new ObjectInputStream(new FileInputStream(file));
			c = (Chromosome)in.readObject();
			in.close();
		}catch(IOException exp){
			exp.printStackTrace();
		} catch (ClassNotFoundException e) { 
			e.printStackTrace();
		}
		return c;
	}
	
/*	public IntegerGene loadSolution(){
		BufferedReader input = null;
		StringBuffer contents = new StringBuffer();
	    try {
			input = new BufferedReader( new FileReader(file) );
			String line = null; 	      
			while (( line = input.readLine()) != null)
				contents.append(line);
	    }
	    catch (FileNotFoundException ex) {
	    	ex.printStackTrace();
	    }
	    catch (IOException ex){
	    	ex.printStackTrace();
	    }
	     
		return null;
	}*/
}
