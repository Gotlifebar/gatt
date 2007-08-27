package org.gatt.optimization.ttga;

import org.gatt.domain.factories.DomainObjectFactoryFacade;
import org.gatt.optimization.util.ImpShuffler;
import org.gatt.optimization.util.Shuffler;
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
import org.jgap.Population;
import org.jgap.event.EventManager;
import org.jgap.event.GeneticEvent;
import org.jgap.impl.BestChromosomesSelector;
import org.jgap.impl.ChromosomePool;
import org.jgap.impl.GreedyCrossover;
import org.jgap.impl.IntegerGene;
import org.jgap.impl.StockRandomGenerator;
import org.jgap.impl.SwappingMutationOperator;


/**
 * The facade for the optimization component.
 */
public class OptimizationFacade {
	
	/**
	 * An enumeration that represents the possible states of the optimization process
	 */
	public enum OptimizationState {RUNNING, PAUSED, FINISHED}
	
	/**
	 * The configuration object for the timetabling genetic algorithm
	 */
	private Configuration gaConfig;
	
	/**
	 * This objects represents the entire population for the algorithm
	 */
	private Genotype genotype;
	
	/**
	 * The evolution thread the genetic algorithm uses, so it can run forever, until some
	 * event stops it.
	 */
	private Thread evolutionThread;
	
	/**
	 * The instance of this class. (It's part of the singleton pattern)
	 */
	private static OptimizationFacade instance; 
	
	/**
	 * Keeps track of the states of the optimization
	 */
	private OptimizationState optimizationState;
	
	
	/**
	 * A protected constructor. (It's part of the singleton pattern)
	 */
	protected OptimizationFacade(){
		DomainObjectFactoryFacade doff = DomainObjectFactoryFacade.getInstance();
		try{
			doff.setDAOFactoryClass("org.gatt.domain.factories.mysqldaofactory.MySqlDAOFactory");
		}catch(Exception e){
			e.printStackTrace();
			return;
		}
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
	
	public OptimizationState getOptimizationState(){
		return optimizationState;
	}
	
	public void setOptimizationState(OptimizationState newState){
		optimizationState = newState;
	}
	
	/**
	 * 
	 *
	 */
	public void optimize(){
		System.out.println("Initializating...");
		
		setOptimizationState(OptimizationState.RUNNING);
		
		try {
			gaConfig = createConfiguration();
			
			FitnessFunction fitnessFunction = createFitnessFunction();
			gaConfig.setFitnessFunction(fitnessFunction);
			
			IChromosome sampleChromosome = createSampleChromosome(); 
			gaConfig.setSampleChromosome(sampleChromosome);
		} catch (InvalidConfigurationException e) {
			e.printStackTrace();
			return;
		}
		
		genotype = initPopulation();
		evolutionThread = new Thread(genotype);
		gaConfig.getEventManager().addEventListener(GeneticEvent.GENOTYPE_EVOLVED_EVENT,
												new TimeTablingEvolutionListener(evolutionThread));
		evolutionThread.start();
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
	
	public void printBestSolution(){
		System.out.println(genotype.getFittestChromosome().toString());
	}
	
	/**
	 * 
	 *
	 */
	public void pauseOptimization(){
		setOptimizationState(OptimizationState.PAUSED);
	}
	
	/**
	 * 
	 *
	 */
	public void resumeOptimization(){
		setOptimizationState(OptimizationState.RUNNING);
		evolutionThread.resume();
	}
	
	/**
	 * 
	 *
	 */
	public void stopOptimization(){
		setOptimizationState(OptimizationState.FINISHED);
		evolutionThread.stop();
		printBestSolution();
		
		Configuration.reset();
	}
	
	/**
	 * 
	 * @return
	 */
	private FitnessFunction createFitnessFunction(){
		//return new OrderFitnessFunction();
		DomainObjectFactoryFacade doff = DomainObjectFactoryFacade.getInstance();
		return new TTFitnessFunction(doff.getRoomsCount(), doff.getHoursCount());
	}
	
	/**
	 * 
	 *
	 */
	private Configuration createConfiguration() throws InvalidConfigurationException{
		JFigLocatorIF locator = new GattConfigLocator("config.xml","config");
		JFigIF config = JFig.getInstance(locator);
		
		Configuration configuration = new Configuration();
		BestChromosomesSelector bestChromsSelector = new BestChromosomesSelector(configuration,1.0d);
		configuration.addNaturalSelector(bestChromsSelector,true);
		configuration.setRandomGenerator(new StockRandomGenerator());
		configuration.setMinimumPopSizePercent(0);
		configuration.setEventManager(new EventManager());
		configuration.setFitnessEvaluator(new DefaultFitnessEvaluator());
		configuration.setChromosomePool(new ChromosomePool());
		configuration.setPreservFittestIndividual(true);
		int populationSize = config.getIntegerValue("GAParameters", "PopulationSize", "0");
		System.out.println(populationSize);
		configuration.setPopulationSize(populationSize);
		
		GreedyCrossover cross = new GreedyCrossover(configuration);
		
		configuration.addGeneticOperator(new GreedyCrossover(configuration));
		int mutationRate = config.getIntegerValue("GAParameters", "MutationRate", "0");
		configuration.addGeneticOperator(new SwappingMutationOperator(configuration,mutationRate));
		
		return configuration;
	}
	
	/**
	 * 
	 * @return
	 */
	private IChromosome createSampleChromosome(){
		DomainObjectFactoryFacade dofFacade = DomainObjectFactoryFacade.getInstance();
		
		/*int numberOfRooms = 2;//dofFacade.getRoomsCount();
		int numberOfHours = 5;//dofFacade.getHoursCount();
		int numberOfGroups = 8;//dofFacade.getGroupsCount();*/
		int numberOfRooms = dofFacade.getRoomsCount();
		int numberOfHours = dofFacade.getHoursCount();
		int numberOfGroups = dofFacade.getGroupsCount();
		
		int genesArraySize = numberOfRooms*numberOfHours;
		
		int lowBound = numberOfGroups - genesArraySize;
		UniqueRandomNumberGenerator rand = new UniqueRandomNumberGenerator(lowBound,numberOfGroups);
		
		try {
			Gene[] genes = new Gene[genesArraySize];
			
			for (int i = 0; i < genes.length; i++) {
				genes[i] = new IntegerGene(getConfiguration(),lowBound,numberOfGroups-1);
				genes[i].setAllele(rand.nextRandom());
			}
			
			IChromosome sample = new Chromosome(getConfiguration(),genes);
				
			return sample;
		} catch (InvalidConfigurationException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 
	 *
	 */
	private Genotype initPopulation(){		
		
		IChromosome sampleChromosome = gaConfig.getSampleChromosome();
		
		IChromosome[] chromosomes = new IChromosome[gaConfig.getPopulationSize()];
		
		Shuffler shuffler = new ImpShuffler();
		
		try {
			Gene[] sampleGenes = sampleChromosome.getGenes(); 
			for (int i = 0; i < chromosomes.length; i++) {
				Gene[] genes = new Gene[sampleGenes.length];
				for (int j = 0; j < genes.length; j++) {
					genes[j] = sampleGenes[j].newGene();
					genes[j].setAllele(sampleGenes[j].getAllele());
				}
				shuffler.shuffle(genes);
				chromosomes[i] = new Chromosome(getConfiguration(),genes);
			}
			
			for (int i = 0; i < chromosomes.length; i++) {
				Gene[] genes = chromosomes[i].getGenes();
				System.out.print("\nChromosome "+(i+1)+": ");
				for (int j = 0; j < genes.length; j++) {
					System.out.print(genes[j].getAllele() + " ");
				}
			}
			
			Genotype geno = new Genotype(getConfiguration(), new Population(getConfiguration(),chromosomes));
			
			return geno;
		} catch (InvalidConfigurationException e) {
			e.printStackTrace();
			return null;
		}
		
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
	public static void main(String ar[]){
		OptimizationFacade of = OptimizationFacade.getInstance();
		//of.createConfiguration();
		of.optimize();
		Thread.yield();
	}
}
