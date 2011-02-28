package com.ec.generator;

import java.util.Random;

import com.ec.Individual;
import com.ec.node.Node;

public abstract class Generator{
	
	/** Default probablity for stoping grow tree*/
	protected static float growStopProbability = 0.25f;
	
	/** A static instance of <code>Random</code>*/
	protected static Random random = new Random();
	
	public abstract Node getRandomTerminal();
	public abstract Node fullTree(int depth);
	public abstract Node growTree(int depth);
	public abstract Individual fitness(Node node);
	
}
