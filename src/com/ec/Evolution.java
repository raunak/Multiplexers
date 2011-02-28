package com.ec;

import java.util.Arrays;
import java.util.Random;
import java.util.Vector;

import com.ec.generator.Mux6Generator;
import com.ec.node.Node;

/**
 * 
 * @author raunak
 * @version 1.0
 */
public class Evolution {
	
	/** Generation counter. */
	private int currGen;
	
	/** Default population size. */
	private int populationSize = 300;
	
	/** Default tree depth. */
	private int maxDepth = 6;
	
	/** Type is Mux6 if set to 0; else Mux11. */
	private int evolutionType;
	
	/** Crossover probability. */
	private float cxProb = 0.7f;
	
	/** Mutation probability. */
	private float mtProb = 0.1f;
	
	/** Static instance of class <code>Random</code>. */
	private static final Random random = new Random();
	
	/** The best individual thus far.*/
	private Individual individual;
	
	/** Holds x number of individuals, where x refers to the population size.*/
	private Individual[] population;
	
	
	public Evolution(int type){
		this.evolutionType = type;
		this.population =  new Individual[populationSize];
	}
	
	public Evolution(int type, int populationSize){
		this.evolutionType = type;
		this.populationSize = populationSize;
		this.population = new Individual[populationSize];
	}
	
	public Evolution(int type, int populationSize, int treeDepth){
		this.evolutionType = type;
		this.populationSize = populationSize;
		this.population = new Individual[populationSize];
		if (treeDepth > 3){
			this.maxDepth = treeDepth;
		} else {
			System.err.println("Tree depth lower than '4' is not permitted");
			System.exit(1);
		}
	}
	
	public void evolve(){
		generatePopulation();
		individual = getSortedPopulation()[0];
		
		while(individual.getFitness() != 1.0){
			System.out.println("Generation : " + currGen + " Individual: " + individual.toString());
			
			Individual[] selectedIndividuals;
			Individual[] newPopulation = new Individual[populationSize];
			
			for(int i = 0; i < populationSize; i+=2){
				
				//Perform selection
				selectedIndividuals = selection();
				
				newPopulation[i] = Mux6Generator.fitness(mutate(selectedIndividuals[0].getNode()));
				newPopulation[i+1] = Mux6Generator.fitness(mutate(selectedIndividuals[0].getNode()));
			}
			
			population = newPopulation;
			if(getSortedPopulation()[0].getFitness() > individual.getFitness()){
				individual = getSortedPopulation()[0];
			}
			currGen++;
		}
	}
	
	private void generatePopulation(){
		if(evolutionType == EvolutionType.MUX6){
			generateMux6Population();
		} else if (evolutionType == EvolutionType.MUX11){
			generateMux11Population();
		}
	}
	
	private void generateMux6Population(){
		int tDepth1 = maxDepth--;
		int tDepth2 = maxDepth - 2;
		
		try{ //for performance; otherwise needs checks.
			int i = 0;
			while(true){
				//Grow Tree
				population[i] = Mux6Generator.fitness(Mux6Generator.growTree(maxDepth));
				population[i+1] = Mux6Generator.fitness(Mux6Generator.growTree(tDepth1));
				population[i+2] = Mux6Generator.fitness(Mux6Generator.growTree(tDepth2));
				
				//Full Tree
				population[i+3] = Mux6Generator.fitness(Mux6Generator.fullTree(maxDepth));
				population[i+4] = Mux6Generator.fitness(Mux6Generator.fullTree(tDepth1));
				population[i+5] = Mux6Generator.fitness(Mux6Generator.fullTree(tDepth2));
				i+=6;
			}
		} catch (IndexOutOfBoundsException iob){}
	}
	
	//TODO: Implement this
	private void generateMux11Population(){
	}
	
	//TODO: Implement this
	private Node[] crossover(Node father, Node mother){
		Node[] n = {father, mother};
		return n;
	}
	
	private Node mutate(Node node){
		if(evolutionType == EvolutionType.MUX6){
			return mutateMux6(node);
		} else {
			return mutateMux11(node);
		}
	}
	
	//TODO: Clean up!
	private Node mutateMux6(Node node){
		Vector<Node> enumeration = node.enumerate();
		if(enumeration == null){
			return Mux6Generator.growTree(maxDepth);
		} else{
			int randomPoint = random.nextInt(enumeration.size());
			Node parentNode = enumeration.get(randomPoint);
			
			int depth1 = maxDepth - (node.getDepth() - parentNode.getDepth());
		
//			System.out.println("Node Depth: " + node.getDepth() + " random node depth: " + parentNode.getDepth() + " D: " + depth1);
			
			int depth = random.nextInt(depth1+1);
			
			int childCountForParent = parentNode.getChildren().size();
			
			if(childCountForParent == 1){
				parentNode.children.set(0, Mux6Generator.growTree(depth));
			} else if (childCountForParent == 2){
				parentNode.children.set(0, Mux6Generator.growTree(depth));
				parentNode.children.set(1, Mux6Generator.growTree(depth));
			} else if (childCountForParent == 3){
				parentNode.children.set(0, Mux6Generator.growTree(depth));
				parentNode.children.set(1, Mux6Generator.growTree(depth));
				parentNode.children.set(2, Mux6Generator.growTree(depth));
			}

			return node;
		}
	}

	//TODO: Implement this NEEDS A RETURN
	private Node mutateMux11(Node node){
		return individual.getNode();
	}
  	
	/**
	 * NOTE: DOES NOT GAURANTEE UNIQUENESS!!
	 * @return
	 */
	private Individual[] selection(){
		Individual[] luckyIndividuals = new Individual[10];
		
		//add random individual from population to array
		for(int i = 0; i < luckyIndividuals.length; i++){
			luckyIndividuals[i] = population[random.nextInt(populationSize)];
		}
		
		//select two fittest individual from array
		Arrays.sort(luckyIndividuals);
		Individual[] selectedIndividuals = {luckyIndividuals[0], luckyIndividuals[1]};
		
		return selectedIndividuals;
	}
	
	private Individual[] getPopulation(){
		return population;
	}
	
	private Individual[] getSortedPopulation(){
		Individual[] individuals = population.clone();
		Arrays.sort(individuals);
		return individuals;
	}

	class EvolutionType{
		public static final int MUX6 = 0;
		public static final int MUX11 = 1;
	}
	
}
