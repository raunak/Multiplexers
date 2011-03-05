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

import com.ec.node.Node;

/**
 * <code>Individual</code> represent a single program in Genetic Programming. An
 * <code>Individual</code> holds both a root <code>Node</code> object and its
 * fitness.
 * 
 * @author raunak
 * @version 1.0
 */
public class Individual implements Comparable<Individual> {

	/** <code>Node</code> holds the root to a program. */
	private Node node;

	/** a value which corresponds to the fitness of a program in a domain. */
	private double fitness;

	/**
	 * Constructs a <code>Individual</code> using the passed parameters.
	 * 
	 * @param node
	 *            - root of a program.
	 * @param fitness
	 *            - fitness of a program
	 */
	public Individual(Node node, double fitness) {
		this.node = node;
		this.fitness = fitness;
	}

	/**
	 * Gets the <code>Node</code>.
	 * 
	 * @return node
	 */
	public Node getNode() {
		return node;
	}

	/**
	 * Gets the fitness
	 * 
	 * @return fitness
	 */
	public double getFitness() {
		return fitness;
	}

	/**
	 * Set the <code>Node</code>.
	 * 
	 * @param node
	 *            - the root
	 */
	public void setNode(Node node) {
		this.node = node;
	}

	/**
	 * Set the fitness.
	 * 
	 * @param fitness
	 *            - fitness of the program.
	 */
	public void setFitness(double fitness) {
		this.fitness = fitness;
	}

	@Override
	public int compareTo(Individual individual) {
		return Double.compare(individual.fitness, this.fitness);
	}

	@Override
	public String toString() {
		return fitness + " : " + node.toString();
	}
}
