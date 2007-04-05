package org.gatt.optimization.ttga;

import org.gatt.domain.factories.DomainObjectFactoryFacade;
import org.gatt.optimization.util.UniqueRandomNumberGenerator;
import org.gatt.util.GattConfigLocator;
import org.igfay.jfig.JFig;
import org.igfay.jfig.JFigIF;
import org.igfay.jfig.JFigLocatorIF;
import org.jgap.Chromosome;
import org.jgap.Configuration;
import org.jgap.DefaultFitnessEvaluator;
import org.jgap.FitnessFunction;
import org.jgap.Gene;
import org.jgap.Genotype;
import org.jgap.IChromosome;
import org.jgap.InvalidConfigurationException;
import org.jgap.event.EventManager;
import org.jgap.impl.BestChromosomesSelector;
import org.jgap.impl.GreedyCrossover;
import org.jgap.impl.IntegerGene;
import org.jgap.impl.StockRandomGenerator;
import org.jgap.impl.SwappingMutationOperator;


/**
 * 
 * @author Andr�s
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
	protected OptimizationFacade(){
	}
	
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
		JFigLocatorIF locator = new GattConfigLocator("config.xml","config");
		JFigIF config = JFig.getInstance(locator);
		int mutationRate = config.getIntegerValue("GAParameters", "MutationRate", "0");
		gaConfig.addGeneticOperator(new SwappingMutationOperator(gaConfig,mutationRate));
	}
	
	/**
	 * 
	 * @return
	 */
	private IChromosome createSampleChromosome(){
		DomainObjectFactoryFacade dofFacade = DomainObjectFactoryFacade.getInstance();
		
		int numberOfRooms = dofFacade.getRoomsCount();
		int numberOfSessions = dofFacade.getSessionsCount();
		int numberOfGroups = dofFacade.getGroupsCount();
		
		int genesArraySize = numberOfRooms*numberOfSessions;
		Gene[] genes = new Gene[genesArraySize];
		
		int lowBound = numberOfGroups - genesArraySize;
		UniqueRandomNumberGenerator rand = new UniqueRandomNumberGenerator(lowBound,numberOfGroups);
		
		IChromosome sample = null;
		
		try {
			for (int i = 0; i < genes.length; i++) {
				genes[i] = new IntegerGene(this.getConfiguration(),lowBound,numberOfGroups);
				genes[i].setAllele(new Integer(rand.nextRandom()));
			}
			
			sample = new Chromosome(this.getConfiguration(),genes);
		} catch (InvalidConfigurationException e) {
			e.printStackTrace();
			return null;
		}
		
		return sample;
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

	public Configuration getConfiguration() {
		return gaConfig;
	}
}
