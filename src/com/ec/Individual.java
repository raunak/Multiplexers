package com.ec;

import com.ec.node.Node;

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
