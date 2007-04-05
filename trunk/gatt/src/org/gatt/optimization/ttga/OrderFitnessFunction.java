package org.gatt.optimization.ttga;

import org.jgap.FitnessFunction;
import org.jgap.IChromosome;
import org.jgap.Gene;

public class OrderFitnessFunction extends FitnessFunction {
	protected double evaluate(IChromosome arg0) {
		double val = 0d;
		Gene[] genes = arg0.getGenes();
		for(int i = 0; i < genes.length; i++)
			val += i * (Integer)genes[i].getAllele();
		return val;
	}

}
