package optimization.ttga;

import org.jgap.Chromosome;
import org.jgap.Configuration;
import org.jgap.Genotype;
import org.jgap.FitnessFunction;

/**
 * 
 * @author Andrés
 *
 */
public class OptimizationFacade {
	
	/**
	 * 
	 */
	private Configuration gaConfig;
	
	/**
	 * 
	 */
	private Genotype genotype;
	
	/**
	 * 
	 */
	private Thread evolutionThread;
	
	/**
	 * 
	 */
	private static OptimizationFacade instance;
	
	/**
	 * 
	 *
	 */
	protected OptimizationFacade(){}
	
	/**
	 * 
	 * @return
	 */
	public static OptimizationFacade getInstance(){
		if(instance == null)
			instance = new OptimizationFacade();
		
		return instance;
	}
	
	/**
	 * 
	 *
	 */
	public void optimize(){
	
	}
	
	/**
	 * 
	 *
	 */
	public void optimizeFromPreviousSolution(){
		
	}
	
	/**
	 * 
	 * @param numberOfSolutions
	 * @return
	 */
	public Chromosome[] getBestSolutions(int numberOfSolutions){
		return null;
	}
	
	/**
	 * 
	 *
	 */
	public synchronized void pauseOptimization(){
		
	}
	
	/**
	 * 
	 *
	 */
	public synchronized void resumeOptimization(){
		
	}
	
	/**
	 * 
	 *
	 */
	public void stopOptimization(){
		
	}
	
	/**
	 * 
	 * @return
	 */
	private FitnessFunction createFitnessFunction(){
		return new TTFitnessFunction();
	}
	
	/**
	 * 
	 *
	 */
	private void createConfiguration(){
		gaConfig = new Configuration();
		//gaConfig.addNaturalSelector(NaturalSelector,true);
		//gaConfig.setRandomGenerator(new UniqueRandomNumberGenerator());
		
	}
	
	/**
	 * 
	 *
	 */
	private void initPopulation(){
		
	}
	
	/**
	 * 
	 *
	 */
	private void saveSolution(){
		
	}
}
