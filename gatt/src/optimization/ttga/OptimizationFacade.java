package optimization.ttga;

import org.jgap.Chromosome;
import org.jgap.Configuration;
import org.jgap.DefaultFitnessEvaluator;
import org.jgap.FitnessFunction;
import org.jgap.Genotype;
import org.jgap.InvalidConfigurationException;
import org.jgap.event.EventManager;
import org.jgap.impl.BestChromosomesSelector;
import org.jgap.impl.GreedyCrossover;
import org.jgap.impl.StockRandomGenerator;
import org.jgap.impl.SwappingMutationOperator;


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
	private void createConfiguration() throws InvalidConfigurationException{
		gaConfig = new Configuration();
		BestChromosomesSelector bestChromsSelector = new BestChromosomesSelector(gaConfig,1.0d);
		gaConfig.addNaturalSelector(bestChromsSelector,true);
		gaConfig.setRandomGenerator(new StockRandomGenerator());
		gaConfig.setMinimumPopSizePercent(0);
		gaConfig.setEventManager(new EventManager());
		gaConfig.setFitnessEvaluator(new DefaultFitnessEvaluator());
		//config.setChromosomePool(new ChromosomePool());
		gaConfig.setPreservFittestIndividual(true);
		gaConfig.addGeneticOperator(new GreedyCrossover(gaConfig));
		// Utilizar algún mecanismo para cargar los parámetros de un archivo de configuración.
		gaConfig.addGeneticOperator(new SwappingMutationOperator(gaConfig,50));
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
