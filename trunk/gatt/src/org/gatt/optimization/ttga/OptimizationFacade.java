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
	private volatile Thread evolutionThread;
	
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
		        	System.out.println(genotype.getFittestChromosome().toString());
		        	evolutionThread = null;
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
		evolutionThread = null;
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
		//DomainObjectFactoryFacade dofFacade = DomainObjectFactoryFacade.getInstance();
		
		int numberOfRooms = 2;//dofFacade.getRoomsCount();
		int numberOfSessions = 5;//dofFacade.getSessionsCount();
		int numberOfGroups = 8;//dofFacade.getGroupsCount();
		
		int genesArraySize = numberOfRooms*numberOfSessions;
		
		int lowBound = numberOfGroups - genesArraySize;
		UniqueRandomNumberGenerator rand = new UniqueRandomNumberGenerator(lowBound,numberOfGroups);
		
		try {
			Gene[] genes = new Gene[genesArraySize];
			
			for (int i = 0; i < genes.length; i++) {
				genes[i] = new IntegerGene(getConfiguration(),lowBound,numberOfGroups-1);
				genes[i].setAllele(new Integer(rand.nextRandom()));
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
