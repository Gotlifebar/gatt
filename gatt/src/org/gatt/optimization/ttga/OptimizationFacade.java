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
import org.jgap.Population;
import org.jgap.event.EventManager;
import org.jgap.event.GeneticEvent;
import org.jgap.event.GeneticEventListener;
import org.jgap.impl.BestChromosomesSelector;
import org.jgap.impl.ChromosomePool;
import org.jgap.impl.GreedyCrossover;
import org.jgap.impl.IntegerGene;
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
		System.out.println("Initializating...");
		try {
			this.createConfiguration();
			FitnessFunction fitnessFunction = this.createFitnessFunction();
			gaConfig.setFitnessFunction(fitnessFunction);
			gaConfig.setSampleChromosome(this.createSampleChromosome());
		} catch (InvalidConfigurationException e) {
			e.printStackTrace();
			return;
		}
		this.initPopulation();
		evolutionThread = new Thread(genotype);
		final int numEvolutions = 1000;
		gaConfig.getEventManager().addEventListener(GeneticEvent.GENOTYPE_EVOLVED_EVENT, new GeneticEventListener() {
			public void geneticEventFired(GeneticEvent a_firedEvent) {
				Genotype genotype = (Genotype) a_firedEvent.getSource();
		        int evno = genotype.getConfiguration().getGenerationNr();
		        System.out.println("Evolution:"+evno);
		        if (evno % 10 == 0) {
		          double bestFitness = genotype.getFittestChromosome().getFitnessValue();
		          System.out.println(evolutionThread.getName() + ": Evolving generation " + evno
		                             + ", best fitness: " + bestFitness);
		        }
		        if (evno > numEvolutions) {
		          evolutionThread.stop();
		        }
		      /*  else {
		          try {
		            evolutionThread.sleep( (j + 1) * 3);
		          } catch (InterruptedException iex) {
		            iex.printStackTrace();
		            System.exit(1);
		          }
		        }*/
		      }
		    });
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
		return new OrderFitnessFunction();
	}
	
	/**
	 * 
	 *
	 */
	private void createConfiguration() throws InvalidConfigurationException{
		JFigLocatorIF locator = new GattConfigLocator("config.xml","config");
		JFigIF config = JFig.getInstance(locator);
		
		gaConfig = new Configuration();
		BestChromosomesSelector bestChromsSelector = new BestChromosomesSelector(gaConfig,1.0d);
		gaConfig.addNaturalSelector(bestChromsSelector,true);
		gaConfig.setRandomGenerator(new StockRandomGenerator());
		gaConfig.setMinimumPopSizePercent(0);
		gaConfig.setEventManager(new EventManager());
		gaConfig.setFitnessEvaluator(new DefaultFitnessEvaluator());
		gaConfig.setChromosomePool(new ChromosomePool());
		gaConfig.setPreservFittestIndividual(true);
		int populationSize = config.getIntegerValue("GAParameters", "PopulationSize", "0");
		System.out.println(populationSize);
		gaConfig.setPopulationSize(populationSize);
		gaConfig.addGeneticOperator(new GreedyCrossover(gaConfig));
		int mutationRate = config.getIntegerValue("GAParameters", "MutationRate", "0");
		gaConfig.addGeneticOperator(new SwappingMutationOperator(gaConfig,mutationRate));
	}
	
	/**
	 * 
	 * @return
	 */
	private IChromosome createSampleChromosome(){
		DomainObjectFactoryFacade dofFacade = DomainObjectFactoryFacade.getInstance();
		
		int numberOfRooms = 3;//dofFacade.getRoomsCount();
		int numberOfSessions = 4;//dofFacade.getSessionsCount();
		int numberOfGroups = 10;//dofFacade.getGroupsCount();
		
		int genesArraySize = numberOfRooms*numberOfSessions;
		Gene[] genes = new Gene[genesArraySize];
		
		int lowBound = numberOfGroups - genesArraySize;
		UniqueRandomNumberGenerator rand = new UniqueRandomNumberGenerator(lowBound,numberOfGroups);
		
		IChromosome sample = null;
		
		try {
			for (int i = 0; i < genes.length; i++) {
				genes[i] = new IntegerGene(this.getConfiguration(),lowBound,numberOfGroups-1);
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
		
		DomainObjectFactoryFacade dofFacade = DomainObjectFactoryFacade.getInstance();

		int numberOfRooms = 3;//dofFacade.getRoomsCount();
		int numberOfSessions = 4;//dofFacade.getSessionsCount();
		int numberOfGroups = 10;//dofFacade.getGroupsCount();
		int genesArraySize = numberOfRooms*numberOfSessions;
		int lowBound = numberOfGroups - genesArraySize;
		UniqueRandomNumberGenerator rand = new UniqueRandomNumberGenerator(lowBound,numberOfGroups);
		
		IChromosome[] chromosomes = new IChromosome[gaConfig.getPopulationSize()];
		
		try {
			for (int i = 0; i < chromosomes.length; i++) {
				Gene[] genes = new Gene[genesArraySize];
				for (int j = 0; j < genes.length; j++) {
					genes[j] = new IntegerGene(gaConfig,lowBound,numberOfGroups-1);
					genes[j].setAllele(new Integer(rand.nextRandom()));
				}
				chromosomes[i] = new Chromosome(gaConfig,genes);
				System.out.print("Chromosome " + i + ": ");
				for (int j = 0; j < genes.length; j++) {
					System.out.print(genes[j].getAllele() + " ");
				}
				System.out.println();
			}
			
			genotype = new Genotype(gaConfig, new Population(gaConfig,chromosomes));
		} catch (InvalidConfigurationException e) {
			e.printStackTrace();
			return;
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
