/* 
 * Copyright (c) 2011 Raunak Gupta; Kamil Olesiejuk
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */
package com.ec;

import java.util.Arrays;
import java.util.Random;
import java.util.Vector;

import com.ec.generator.Mux11Generator;
import com.ec.generator.Mux6Generator;
import com.ec.node.Node;

/**
 * @version 1.0
 */
public class Evolution {
	
	/** Generation counter. */
	private int currGen;
	
	/** Default population size. */
	private int populationSize = 300;
	
	/** Default tree depth. */
	private final int maxDepth = 6;
	
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
	
	/** <code>Mux6Generator</code> object */
	private Mux6Generator mux6;
	
	/** <code>Mux11Generator</code> object */
	private Mux11Generator mux11;
	
	public Evolution(int type){
		this.evolutionType = type;
		this.population =  new Individual[populationSize];
	}
	
	public Evolution(int type, int populationSize){
		this.evolutionType = type;
		this.populationSize = populationSize;
		this.population = new Individual[populationSize];
	}
	
	public void evolve(){
		generatePopulation();
		
		if(evolutionType == EvolutionType.MUX6){
			evolveMux6();
		} else if (evolutionType == EvolutionType.MUX11){
			evolveMux11();
		}
	}
	
	private void evolveMux6(){
		individual = getBestIndividual();

		while(individual.getFitness() != 1.0){
			System.out.println("Generation : " + currGen + " Individual: " + individual.toString());
			
			Individual[] selectedIndividuals;
			Individual[] newPopulation = new Individual[populationSize];
			
			for(int i = 0; i < populationSize; i+=2){
				
				//Perform selection
				selectedIndividuals = selection();
				
				newPopulation[i] = mux6.fitness(mutate(selectedIndividuals[0].getNode()));
				newPopulation[i+1] = mux6.fitness(mutate(selectedIndividuals[0].getNode()));
			}
			
			population = newPopulation;
			if(getSortedPopulation()[0].getFitness() > individual.getFitness()){
				individual = getSortedPopulation()[0];
			}
			currGen++;
		}
	}
	
	private void evolveMux11(){
		individual = getBestIndividual();

		while(individual.getFitness() != 1.0){
			System.out.println("Generation : " + currGen + " Individual: " + individual.toString());
			
			Individual[] selectedIndividuals;
			Individual[] newPopulation = new Individual[populationSize];
			
			for(int i = 0; i < populationSize; i+=2){
				
				//Perform selection
				selectedIndividuals = selection();
				
				newPopulation[i] = mux11.fitness(mutate(selectedIndividuals[0].getNode()));
				newPopulation[i+1] = mux11.fitness(mutate(selectedIndividuals[0].getNode()));
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
			mux6 = new Mux6Generator();
			generateMux6Population();
		} else if (evolutionType == EvolutionType.MUX11){
			mux11 = new Mux11Generator();
			generateMux11Population();
		}
	}
	
	private void generateMux6Population(){
		int tDepth1 = maxDepth - 1;
		int tDepth2 = maxDepth - 2;
		
		try{ //for performance; otherwise needs checks.
			int i = 0;
			while(true){
				//Grow Tree
				population[i] = mux6.fitness(mux6.growTree(maxDepth));
				population[i+1] = mux6.fitness(mux6.growTree(tDepth1));
				population[i+2] = mux6.fitness(mux6.growTree(tDepth1));
				
				//Full Tree
				population[i+3] = mux6.fitness(mux6.fullTree(maxDepth));
				population[i+4] = mux6.fitness(mux6.fullTree(tDepth1));
				population[i+5] = mux6.fitness(mux6.fullTree(tDepth2));
				i+=6;
			}
		} catch (IndexOutOfBoundsException iob){}
	}
	
	private void generateMux11Population(){
		int tDepth1 = maxDepth - 1;
		int tDepth2 = maxDepth - 2;
		
		try{ //for performance; otherwise needs checks.
			int i = 0;
			while(true){
				//Grow Tree
				population[i] = mux11.fitness(mux11.growTree(maxDepth));
				population[i+1] = mux11.fitness(mux11.growTree(tDepth1));
				population[i+2] = mux11.fitness(mux11.growTree(tDepth1));
				
				//Full Tree
				population[i+3] = mux11.fitness(mux11.fullTree(maxDepth));
				population[i+4] = mux11.fitness(mux11.fullTree(tDepth1));
				population[i+5] = mux11.fitness(mux11.fullTree(tDepth2));
				i+=6;
			}
		} catch (IndexOutOfBoundsException iob){}
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
	
	private int getGrowSpace(Node node){
		Node raunak = node;
		int count = 0;
		while(!raunak.isRoot){
			count++;
			raunak = raunak.getParent();
		}
		return this.maxDepth - count;
	}
	
	
	private Node mutateMux6(Node root){
		Vector<Node> enumeration = root.enumerate();
		
		if(enumeration ==  null){
			return mux6.growTree(maxDepth);
		} else {
			Node nodeToMutate = enumeration.get(random.nextInt(enumeration.size()));
			
			int noOfChildrenMutateNode = nodeToMutate.getChildren().size();
			int depth = random.nextInt(getGrowSpace(nodeToMutate));
			
			if(noOfChildrenMutateNode == 1){
				nodeToMutate.children.set(0, mux6.growTree(depth));
			} else if (noOfChildrenMutateNode == 2){
				nodeToMutate.children.set(0, mux6.growTree(depth));
				nodeToMutate.children.set(1, mux6.growTree(depth));
			} else if (noOfChildrenMutateNode == 3){
				nodeToMutate.children.set(0, mux6.growTree(depth));
				nodeToMutate.children.set(1, mux6.growTree(depth));
				nodeToMutate.children.set(2, mux6.growTree(depth));
			}
			
		}
		return root;
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
	
	private Individual[] getSortedPopulation(){
		Individual[] individuals = population.clone();
		Arrays.sort(individuals);
		return individuals;
	}
	
	private Individual getBestIndividual(){
		Individual bestIndividual = population[0];
		
		for(int i = 0; i < populationSize; i++){
			if(population[i].getFitness() > bestIndividual.getFitness()){
				bestIndividual = population[i];
			}
		}
		
		return bestIndividual;
	}

	class EvolutionType{
		public static final int MUX6 = 0;
		public static final int MUX11 = 1;
	}
	
}
