package org.gatt.optimization.ttga;

import java.util.Collection;
import java.util.List;

import org.gatt.domain.InitialTT;
import org.gatt.domain.factories.DomainObjectFactoryFacade;
import org.gatt.domain.factories.mysqldaofactory.MySqlRoomDAO;
import org.gatt.optimization.util.ImpShuffler;
import org.gatt.optimization.util.NumericTransformationFunction;
import org.gatt.optimization.util.ResultManager;
import org.gatt.optimization.util.SessionsResetter;
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
	
	private static final float REQUIRED_MEDIA_PCT = 0.3f,
							   DISPONIBLE_MEDIA_PCT = 0.3f;
	
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
	 * Listener for evolution events.
	 */
	private TimeTablingEvolutionListener listener;
	
	/**
	 * Starting Time of an optimization
	 */
	private long startTime;
	
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
	 * Gets the instance of this Optimization facade. It's part of the singleton pattern
	 * @return The only instance of this class
	 */
	public static OptimizationFacade getInstance(){
		if(instance == null)
			instance = new OptimizationFacade();
	
		return instance;
	}
	
	/**
	 * Gives the current state of the optimization process
	 * @return A value of the enumeration OptimizationState
	 */
	public OptimizationState getOptimizationState(){
		return optimizationState;
	}
	
	/**
	 * Sets the current state of the optimization process
	 * @param newState The new state for the optimization process.
	 */
	public void setOptimizationState(OptimizationState newState){
		optimizationState = newState;
	}
	
	/**
	 * Initializes the optimization process
	 */
	public void optimize(){
		System.out.println("Initializating...");
		
		setOptimizationState(OptimizationState.RUNNING);
		
		try {
			gaConfig = createConfiguration();
			
			FitnessFunction fitnessFunction = createFitnessFunction();
			gaConfig.setFitnessFunction(fitnessFunction);

			SessionsResetter resetter = new SessionsResetter();
			resetter.resetSessions(REQUIRED_MEDIA_PCT);
			//This will be deleted
			MySqlRoomDAO roomDao = new MySqlRoomDAO();
			roomDao.randomizeMedia(DISPONIBLE_MEDIA_PCT);
			
			IChromosome sampleChromosome = createSampleChromosome();
			gaConfig.setSampleChromosome(sampleChromosome);
		} catch (InvalidConfigurationException e) {
			e.printStackTrace();
			return;
		}
		
		genotype = initPopulation();
		evolutionThread = new Thread(genotype);
		listener = new TimeTablingEvolutionListener(evolutionThread);
		gaConfig.getEventManager().addEventListener(GeneticEvent.GENOTYPE_EVOLVED_EVENT,
												listener);
		startTime = System.currentTimeMillis();
		evolutionThread.start();
	}
	
	/**
	 * Starts the optimization process from a previously obtained solution
	 */
	public void optimizeFromPreviousSolution(IChromosome previousSolution){
		System.out.println("Initializating...");
		
		setOptimizationState(OptimizationState.RUNNING);
		
		try {
			gaConfig = createConfiguration();
			
			FitnessFunction fitnessFunction = createFitnessFunction();
			gaConfig.setFitnessFunction(fitnessFunction);

			SessionsResetter resetter = new SessionsResetter();
			resetter.resetSessions(REQUIRED_MEDIA_PCT);
			
			MySqlRoomDAO roomDao = new MySqlRoomDAO();
			roomDao.randomizeMedia(DISPONIBLE_MEDIA_PCT);
			
			IChromosome sampleChromosome = createSampleChromosome();
			gaConfig.setSampleChromosome(sampleChromosome);
		} catch (InvalidConfigurationException e) {
			e.printStackTrace();
			return;
		}
		
		genotype = initPopulation();
		evolutionThread = new Thread(genotype);
		listener = new TimeTablingEvolutionListener(evolutionThread);
		gaConfig.getEventManager().addEventListener(GeneticEvent.GENOTYPE_EVOLVED_EVENT,
												listener);
		startTime = System.currentTimeMillis();
		evolutionThread.start();
		
	}
	
	/**
	 * return the best solution
	 */
	public IChromosome getBestSolution(){
		return genotype.getFittestChromosome();
	}
	
	/**
	 * return true if a optimization process has been started
	 */
	public boolean isOptimizationProcessStarted(){
		return (genotype != null);
	}
	
	/**
	 * Gives the best solutions so far, which have been found by the optimization process
	 * @param numberOfSolutions An integer representing the number of solutions
	 * @return An array of Chromosome object
	 */
	public List getBestSolutions(int numberOfSolutions){
		return genotype.getFittestChromosomes(numberOfSolutions);
	}
	
	// TODO: Delete this method
	/*public void printBestSolution(){
		System.out.println(genotype.getFittestChromosome().toString());
	}*/
	
	/**
	 * Pauses the optimization process
	 */
	public void pauseOptimization(){
		setOptimizationState(OptimizationState.PAUSED);
	}
	
	/**
	 * This method resumes an optimization process that was paused.
	 */
	public void resumeOptimization(){
		setOptimizationState(OptimizationState.RUNNING);
		evolutionThread.resume();
	}
	
	/**
	 * Stops the optimization process.
	 */
	public void stopOptimization(){
		setOptimizationState(OptimizationState.FINISHED);
		evolutionThread.stop();
		long stopTime = System.currentTimeMillis();
		//printBestSolution();
		float totalTime = ((float)(stopTime - startTime)) / 1000;
		
		ResultManager rm = new ResultManager(totalTime, listener.getFitnessValues());
		rm.saveResults();
		
		Configuration.reset();
	}
	
	/**
	 * Creates the fitness function for the genetic algorithm used in the
	 * optimization process.
	 * @return An object of the class FitnessFunction
	 */
	private FitnessFunction createFitnessFunction(){
		//return new OrderFitnessFunction();
		DomainObjectFactoryFacade doff = DomainObjectFactoryFacade.getInstance();
		return new TTFitnessFunction(doff.getRoomsCount(), doff.getHoursCount());
	}
	
	/**
	 * Creates the configuration object for the genetic algorithm
	 * @return An object of the class Configuration
	 * @throws InvalidConfigurationException If there are some problem with the
	 * configuration parameters
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
	 * Creates a sample chromosome which is used as a basis for creating the entire
	 * population of the genetic algorithm.
	 * @return An object whose type is IChromosome
	 */
	private IChromosome createSampleChromosome(){
		DomainObjectFactoryFacade dofFacade = DomainObjectFactoryFacade.getInstance();
		int numberOfRooms = dofFacade.getRoomsCount();
		int numberOfHours = dofFacade.getHoursCount();
		
		//int numberOfGroups = dofFacade.getGroupsCount();
		Collection<InitialTT> initialTT = dofFacade.getInitialTT();
		//int numberOfSessions = dofFacade.getSessionsCount();
		int numberOfSessions = initialTT.size();
		
		int genesArraySize = numberOfRooms*numberOfHours;
		
		//int lowBound = numberOfGroups - genesArraySize;
		int lowBound = -genesArraySize;//numberOfSessions - genesArraySize;
		
		//UniqueRandomNumberGenerator rand = new UniqueRandomNumberGenerator(lowBound,numberOfGroups);
		UniqueRandomNumberGenerator rand = new UniqueRandomNumberGenerator(lowBound,numberOfSessions);		
		
		NumericTransformationFunction ntf = new NumericTransformationFunction(numberOfRooms, numberOfHours);
		
		
		try {
			Gene[] genes = new Gene[genesArraySize];
			
//			Fill blank spaces.
			for(int i = 0; i < genes.length; i++){
				genes[i] = new IntegerGene(getConfiguration(),lowBound,numberOfSessions-1);
				//Set blank space.
				genes[i].setAllele(-(i+1));				
			}
			
			for(InitialTT tt : initialTT)
				genes[ntf.getIndexFor(tt.getRoom(), tt.getHour())].setAllele(tt.getGroup());
			
			
		/*	for (int i = 0; i < genes.length; i++) {
				//genes[i] = new IntegerGene(getConfiguration(),lowBound,numberOfGroups-1);
				genes[i] = new IntegerGene(getConfiguration(),lowBound,numberOfSessions-1);
				genes[i].setAllele(rand.nextRandom());
			}*/
			
			IChromosome sample = new Chromosome(getConfiguration(),genes);
				
			return sample;
		} catch (InvalidConfigurationException e) {
			e.printStackTrace();
			return null;
		}
/*		
		//UniqueRandomNumberGenerator rand = new UniqueRandomNumberGenerator(lowBound,numberOfGroups);
		UniqueRandomNumberGenerator rand = new UniqueRandomNumberGenerator(lowBound,numberOfSessions);
		
		try {
			Gene[] genes = new Gene[genesArraySize];
			
			for (int i = 0; i < genes.length; i++) {
				//genes[i] = new IntegerGene(getConfiguration(),lowBound,numberOfGroups-1);
				genes[i] = new IntegerGene(getConfiguration(),lowBound,numberOfSessions-1);
				genes[i].setAllele(rand.nextRandom());
			}
			
			IChromosome sample = new Chromosome(getConfiguration(),genes);
				
			return sample;
		} catch (InvalidConfigurationException e) {
			e.printStackTrace();
			return null;
		}*/
	}
	
	/**
	 * Initiates the population for the genetic algorithm
	 * @return An object of the class Genotype
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
			
			/*for (int i = 0; i < chromosomes.length; i++) {
				Gene[] genes = chromosomes[i].getGenes();
				System.out.print("\nChromosome "+(i+1)+": ");
				for (int j = 0; j < genes.length; j++) {
					System.out.print(genes[j].getAllele() + " ");
				}
			}*/
			
			Genotype geno = new Genotype(getConfiguration(), new Population(getConfiguration(),chromosomes));
			
			return geno;
		} catch (InvalidConfigurationException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	/**
	 * Saves the current population of the algorithm
	 */
	private void saveSolution(){
		
	}
	
	/**
	 * Gives the configuration of the genetic algorithm
	 * @return An object of the class Configuration
	 */
	public Configuration getConfiguration() {
		return gaConfig;
	}
	
	// TODO: Delete this method
	/*public static void main(String ar[]){
		OptimizationFacade of = OptimizationFacade.getInstance();
		//of.createConfiguration();
		of.optimize();
		Thread.yield();
	}*/
}
