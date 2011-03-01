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
 * @version 1.0 
 */
public class Individual implements Comparable<Individual>{
	private Node node;
	private float fitness;
	
	public Individual(Node node){
		this.node = node;
	}
	
	public Individual(Node node, Float fitness){
		this.node = node;
		this.fitness = fitness;
	}
	
	public Node getNode(){
		return node;
	}
	
	public float getFitness(){
		return fitness;
	}
	
	public void setNode(Node node){
		this.node = node;
	}
	
	public void setFitness(Float fitness){
		this.fitness = fitness;
	}

	@Override
	public int compareTo(Individual individual) {
		return Float.compare(individual.fitness, this.fitness);
	}
	
	@Override
	public String toString(){
		return fitness + " : " + node.toString();
	}
}