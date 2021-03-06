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
 * <code>Evolution</code> manages the evolution of both Multiplexer 6 and
 * Multiplexer 11 problem. It has one public method <code>.evolve()</code>. One
 * can define both the population size and the problem type when creating an
 * instance of <code>Evolution</code>.
 * 
 * @author raunak
 * @version 1.0
 */
public class Evolution {

	/**
	 * Generation counter.
	 */
	private int currGen;

	/**
	 * Default population size.
	 */
	private int populationSize = 300;

	/**
	 * Default tree depth.
	 */
	public static final int maxDepth = 6;

	/**
	 * Type is Mux6 if set to 0; else Mux11.
	 */
	private int evolutionType;

	/**
	 * Crossover probability.
	 */
	private double cxProb = 0.7;

	/**
	 * Mutation probability.
	 */
	private double mtProb = 0.9;

	/**
	 * Static instance of class <code>Random</code>.
	 */
	private static final Random random = new Random();

	/**
	 * The best individual thus far.
	 */
	private Individual individual;

	/**
	 * Holds x number of individuals, where x refers to the population size.
	 */
	public Individual[] population;

	/**
	 * <code>Mux6Generator</code> object
	 */
	private Mux6Generator mux6;

	/**
	 * <code>Mux11Generator</code> object
	 */
	private Mux11Generator mux11;

	/**
	 * 
	 * @param type
	 */
	public Evolution(int type) {
		this.evolutionType = type;
		this.population = new Individual[populationSize];
	}

	/**
	 * 
	 * @param type
	 * @param populationSize
	 */
	public Evolution(int type, int populationSize) {
		this.evolutionType = type;
		this.populationSize = populationSize;
		this.population = new Individual[populationSize];
	}

	/**
	 * 
	 */
	public void evolve() {
		generatePopulation();

		if (evolutionType == EvolutionType.MUX6) {
			evolveMux6();
		} else if (evolutionType == EvolutionType.MUX11) {
			evolveMux11();
		}
	}

	/**
	 * 
	 */
	private void evolveMux6() {
		individual = getBestIndividual();

		while (individual.getFitness() != 1.0) {
			System.out.println("Generation : " + currGen + " Individual: "
					+ individual.toString());

			Individual[] selectedIndividuals;
			Individual[] newPopulation = new Individual[populationSize];

			for (int i = 0; i < populationSize; i += 2) {

				// Perform selection
				selectedIndividuals = selection();
				Node[] crossovered = probCrossover(
						selectedIndividuals[0].getNode(),
						selectedIndividuals[1].getNode());

				newPopulation[i] = mux6
						.createIndividual(mutate(crossovered[0]));
				newPopulation[i + 1] = mux6
						.createIndividual(mutate(crossovered[1]));
			}

			population = newPopulation;

			Individual tmp = getBestIndividual();
			if (tmp.getFitness() > individual.getFitness()) {
				individual = tmp;
			}
			currGen++;
		}
		System.out.println("Generation : " + currGen + " Individual: "
				+ individual.toString());
	}

	/**
	 * 
	 */
	private void evolveMux11() {
		individual = getBestIndividual();

		while (individual.getFitness() != 1.0) {
			System.out.println("Generation : " + currGen + " Individual: "
					+ individual.toString());

			Individual[] selectedIndividuals;
			Individual[] newPopulation = new Individual[populationSize];

			for (int i = 0; i < populationSize; i += 2) {

				// Perform selection
				selectedIndividuals = selection();
				Node[] crossovered = probCrossover(
						selectedIndividuals[0].getNode(),
						selectedIndividuals[1].getNode());

				newPopulation[i] = mux11
						.createIndividual(mutate(crossovered[0]));
				newPopulation[i + 1] = mux11
						.createIndividual(mutate(crossovered[0]));
			}

			population = newPopulation;
			Individual tmp = getBestIndividual();
			if (tmp.getFitness() > individual.getFitness()) {
				individual = tmp;
			}
			currGen++;
		}
		System.out.println("Generation : " + currGen + " Individual: "
				+ individual.toString());
	}

	/**
	 * 
	 */
	private void generatePopulation() {
		if (evolutionType == EvolutionType.MUX6) {
			mux6 = new Mux6Generator();
			generateMux6Population();
		} else if (evolutionType == EvolutionType.MUX11) {
			mux11 = new Mux11Generator();
			generateMux11Population();
		}
	}

	/**
	 * 
	 */
	private void generateMux6Population() {
		int tDepth1 = maxDepth - 1;
		int tDepth2 = maxDepth - 2;

		try { // for performance; otherwise needs checks.
			int i = 0;
			while (true) {
				// Grow Tree
				population[i] = mux6.createIndividual(mux6.growTree(maxDepth));
				population[i + 1] = mux6.createIndividual(mux6
						.growTree(tDepth1));
				population[i + 2] = mux6.createIndividual(mux6
						.growTree(tDepth2));

				// Full Tree
				population[i + 3] = mux6.createIndividual(mux6
						.fullTree(maxDepth));
				population[i + 4] = mux6.createIndividual(mux6
						.fullTree(tDepth1));
				population[i + 5] = mux6.createIndividual(mux6
						.fullTree(tDepth2));
				i += 6;
			}
		} catch (IndexOutOfBoundsException iob) {
		}
	}

	/**
	 * 
	 */
	private void generateMux11Population() {
		int tDepth1 = maxDepth - 1;
		int tDepth2 = maxDepth - 2;

		try { // for performance; otherwise needs checks.
			int i = 0;
			while (true) {
				// Grow Tree
				population[i] = mux11
						.createIndividual(mux11.growTree(maxDepth));
				population[i + 1] = mux11.createIndividual(mux11
						.growTree(tDepth1));
				population[i + 2] = mux11.createIndividual(mux11
						.growTree(tDepth2));

				// Full Tree
				population[i + 3] = mux11.createIndividual(mux11
						.fullTree(maxDepth));
				population[i + 4] = mux11.createIndividual(mux11
						.fullTree(tDepth1));
				population[i + 5] = mux11.createIndividual(mux11
						.fullTree(tDepth2));
				i += 6;
			}
		} catch (IndexOutOfBoundsException iob) {
		}
	}

	/**
	 * 
	 * @param father
	 * @param mother
	 * @return
	 */
	private Node[] probCrossover(Node father, Node mother) {
		Node[] children = { father, mother };

		if (random.nextDouble() > cxProb) {
			children = crossover(father.clone(null), mother.clone(null));
		}
		return children;
	}

	private Node[] crossover(Node father, Node mother) {

		int fd = father.getDepth();
		int md = mother.getDepth();
		if ((fd != 0) && (md != 0)) {
			if (father.getDepth() > mother.getDepth()) {
				Node tmp = father;
				father = mother;
				mother = tmp;
			}
			Vector<Node> fenum = father.enumerate();

			int randomPoint = random.nextInt(fenum.size());
			Node p1 = fenum.get(randomPoint);
			int p1depth = p1.getDepth();
			int depthLeft = maxDepth - p1.getLevel();
			Vector<Node> menum = mother.enumBounded(depthLeft, p1depth);

			randomPoint = random.nextInt(menum.size());
			Node p2 = menum.get(randomPoint);
			int c1 = random.nextInt(p1.children.size());
			int c2 = random.nextInt(p2.children.size());
			Node swapFromF = p1.children.get(c1);
			Node swapFromM = p2.children.get(c2);
			swapFromF.setParent(p2);
			swapFromM.setParent(p1);
			p1.children.set(c1, swapFromM);
			p2.children.set(c2, swapFromF);
			Node[] n = { father, mother };
			return n;
		} else {
			if (father.getDepth() == 0 && mother.getDepth() != 0) {
				Vector<Node> menum = mother.enumerate();
				int randomPoint = random.nextInt(menum.size());
				Node p2 = menum.get(randomPoint);

				int c2 = random.nextInt(p2.children.size());
				Node swapFromM = p2.children.get(c2);
				father.setParent(p2);
				p2.children.set(c2, father);
				father.setParent(p2);
				swapFromM.setParent(null);

				Node[] n = { swapFromM, mother };
				return n;
			} else if (father.getDepth() == 0 && mother.getDepth() != 0) {
				System.out.println("Mother's depth is 0");
				Vector<Node> fenum = father.enumerate();

				int randomPoint = random.nextInt(fenum.size());
				Node p1 = fenum.get(randomPoint);

				int c1 = random.nextInt(p1.children.size());
				Node swapFromF = p1.children.get(c1);
				p1.children.set(c1, mother);
				mother.setParent(p1);
				swapFromF.setParent(null);
				Node[] n = { swapFromF, father };
				return n;
			} else {
				Node[] n = { mother, father };
				return n;
			}
		}
	}

	/**
	 * 
	 * @param node
	 * @return
	 */
	private Node mutate(Node node) {
		if (evolutionType == EvolutionType.MUX6) {
			return probMutate6(node.clone(null));
		} else if (evolutionType == EvolutionType.MUX11) {
			return probMutate11(node.clone(null));
		} else {
			return node;
		}
	}

	/**
	 * 
	 * @param node
	 * @return
	 */
	private Node probMutate6(Node node) {
		if (random.nextDouble() > mtProb) {
			return mutateMux6(node);
		} else
			return node;
	}

	/**
	 * 
	 * @param node
	 * @return
	 */
	private Node probMutate11(Node node) {
		if (random.nextDouble() > mtProb) {
			return mutateMux11(node);
		} else
			return node;
	}

	/**
	 * 
	 * @param root
	 * @return
	 */
	private Node mutateMux6(Node root) {
		Vector<Node> enumeration = root.enumerate();

		if (enumeration.isEmpty()) {
			return mux6.growTree(maxDepth);
		} else {
			Node nodeToMutate = enumeration.get(random.nextInt(enumeration
					.size()));

			int noOfChildrenMutateNode = nodeToMutate.getChildren().size();
			int depth = random.nextInt(maxDepth - nodeToMutate.getLevel());

			if (noOfChildrenMutateNode == 1) {
				nodeToMutate.children.set(0,
						mux6.recGrowTree(nodeToMutate, depth));
			} else if (noOfChildrenMutateNode == 2) {
				nodeToMutate.children.set(0,
						mux6.recGrowTree(nodeToMutate, depth));
				nodeToMutate.children.set(1,
						mux6.recGrowTree(nodeToMutate, depth));
			} else if (noOfChildrenMutateNode == 3) {
				nodeToMutate.children.set(0,
						mux6.recGrowTree(nodeToMutate, depth));
				nodeToMutate.children.set(1,
						mux6.recGrowTree(nodeToMutate, depth));
				nodeToMutate.children.set(2,
						mux6.recGrowTree(nodeToMutate, depth));
			}

		}

		if (root.getDepth() > 6) {
			System.out.println("BOGUS!!!!!");
		}

		return root;
	}

	/**
	 * 
	 * @param root
	 * @return
	 */
	private Node mutateMux11(Node root) {
		Vector<Node> enumeration = root.enumerate();

		if (enumeration.isEmpty()) {
			return mux11.growTree(maxDepth);
		} else {
			Node nodeToMutate = enumeration.get(random.nextInt(enumeration
					.size()));

			int noOfChildrenMutateNode = nodeToMutate.getChildren().size();
			int depth = random.nextInt(maxDepth - nodeToMutate.getLevel());

			if (noOfChildrenMutateNode == 1) {
				nodeToMutate.children.set(0,
						mux11.recGrowTree(nodeToMutate, depth));
			} else if (noOfChildrenMutateNode == 2) {
				nodeToMutate.children.set(0,
						mux11.recGrowTree(nodeToMutate, depth));
				nodeToMutate.children.set(1,
						mux11.recGrowTree(nodeToMutate, depth));
			} else if (noOfChildrenMutateNode == 3) {
				nodeToMutate.children.set(0,
						mux11.recGrowTree(nodeToMutate, depth));
				nodeToMutate.children.set(1,
						mux11.recGrowTree(nodeToMutate, depth));
				nodeToMutate.children.set(2,
						mux11.recGrowTree(nodeToMutate, depth));
			}

		}
		return root;
	}

	/**
	 * NOTE: DOES NOT GAURANTEE UNIQUENESS!!
	 * 
	 * @return
	 */
	private Individual[] selection() {
		Individual[] luckyIndividuals = new Individual[10];

		// add random individual from population to array
		for (int i = 0; i < luckyIndividuals.length; i++) {
			luckyIndividuals[i] = population[random.nextInt(populationSize)];
		}

		// select two fittest individual from array
		Arrays.sort(luckyIndividuals);
		Individual[] selectedIndividuals = { luckyIndividuals[0],
				luckyIndividuals[1] };

		return selectedIndividuals;
	}

	/**
	 * 
	 * @return
	 */
	private Individual getBestIndividual() {
		Individual bestIndividual = population[0];

		for (int i = 0; i < populationSize; i++) {
			if (population[i].getFitness() > bestIndividual.getFitness()) {
				bestIndividual = population[i];
			}
		}

		return bestIndividual;
	}

	/**
	 * 
	 * @author raunak
	 * @version 1.0
	 */
	static class EvolutionType {
		public static final int MUX6 = 0;
		public static final int MUX11 = 1;
	}
}
