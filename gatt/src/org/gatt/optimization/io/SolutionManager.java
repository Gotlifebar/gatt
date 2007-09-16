package org.gatt.optimization.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Writer;

import org.jgap.impl.IntegerGene;

public class SolutionManager {
	
	private static final String fileName = "solutions/solution.dat";
	
	private File file;
	
	public SolutionManager(){
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
	
	public void saveSolution(IntegerGene g){
		ObjectOutputStream out;
		try{
			out = new ObjectOutputStream(new FileOutputStream(file));
			out.writeObject(g);
			out.close();
		}catch(IOException exp){
			exp.printStackTrace();
		}
	}
	
	public IntegerGene loadSolution(){
		ObjectInputStream in;
		IntegerGene gene = null;
		try{
			in = new ObjectInputStream(new FileInputStream(file));
			gene = (IntegerGene)in.readObject();
			in.close();
		}catch(IOException exp){
			exp.printStackTrace();
		} catch (ClassNotFoundException e) { 
			e.printStackTrace();
		}
		return gene;
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
