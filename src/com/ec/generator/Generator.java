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
package com.ec.generator;

import java.util.Random;

import com.ec.Individual;
import com.ec.node.Node;
import com.ec.node.functionNode.AndNode;
import com.ec.node.functionNode.IfNode;
import com.ec.node.functionNode.NotNode;
import com.ec.node.functionNode.OrNode;

/**
 * <code>Generator</code> is a base class. It provides two main methods;
 * <code>growTree()</code> and <code>fullTree()</code>, both of which are used
 * to generate programs of defined depth.
 * 
 * @author raunak
 * @version 1.0
 */
public abstract class Generator {

	/**
	 * Default probablity for stoping grow tree
	 */
	protected static double growStopProbability = 0.25;

	/**
	 * A constant used with <code>generateRoot()</code> informing the method to
	 * return a FunctionNode.
	 */
	protected static final int FULLTREE = 4;

	/**
	 * A constant used with <code>generateRoot()</code> informing the method to
	 * return a FunctionNode or a TerminalNode.
	 */
	protected static final int GROWTREE = 5;

	/**
	 * A static instance of <code>Random</code>, used for generating random
	 * integers and double.
	 */
	protected static Random random = new Random();

	/**
	 * Gets a random terminal <code>Node</code>.
	 * 
	 * @param parent
	 *            - Parent of this terminal <code>Node</code>
	 * 
	 * @return A terminal <code>Node</code>
	 */
	protected abstract Node getRandomTerminal(Node parent);

	/**
	 * Evaluates the program by calculating the number of cases for which that
	 * program passes.
	 * 
	 * @param node
	 *            - The <code>Node</code> to be evaluated.
	 * 
	 * @return fitness - <code>double</code>
	 */
	protected abstract double fitness(Node node);

	/**
	 * Chooses a random <code>Node</code> from a list of <code>Node</code>s.
	 * 
	 * @param range
	 *            - Pass the value 4 if making a FullTree otherwise pass the
	 *            value 5.
	 * @return - A <code>Node</code>, which can potentially be used to make a
	 *         tree.
	 */
	protected Node generateRoot(int range) {
		switch (random.nextInt(range)) {
		case 0:
			return new NotNode();
		case 1:
			return new OrNode();
		case 2:
			return new AndNode();
		case 3:
			return new IfNode();
		case 4:
			return getRandomTerminal(null);
		default: // This should NEVER happen!!
			return null;
		}
	}

	/**
	 * Creates a full tree of defined depth. The root <code>Node</code> of a
	 * full tree is always a Function <code>Node</code>.
	 * 
	 * @param depth
	 *            - depth of tree.
	 * 
	 * @return a Tree of defined depth.
	 */
	public Node fullTree(int depth) {
		Node root = generateRoot(Generator.FULLTREE);

		int rootCapacity = root.getChildren().capacity();
		if (rootCapacity == 1) {
			root.children.add(0, fillTree(root, depth - 1));
		} else if (rootCapacity == 2) {
			root.children.add(0, fillTree(root, depth - 1));
			root.children.add(1, fillTree(root, depth - 1));
		} else if (rootCapacity == 3) {
			root.children.add(0, fillTree(root, depth - 1));
			root.children.add(1, fillTree(root, depth - 1));
			root.children.add(2, fillTree(root, depth - 1));
		}
		return root;
	}

	/**
	 * A recursive method, which creates a full tree of defined depth.
	 * 
	 * @param parent
	 *            - the parent <code>Node</code>.
	 * @param depth
	 *            - depth of tree
	 * 
	 * @return a full tree of defined depth.
	 */
	public Node fillTree(Node parent, int depth) {
		if (depth > 0) {
			switch (random.nextInt(Generator.FULLTREE)) {
			case 0:
				NotNode node = new NotNode(parent);
				node.children.add(fillTree(node, depth - 1));
				return node;
			case 1:
				OrNode orNode = new OrNode(parent);
				orNode.children.add(0, fillTree(orNode, depth - 1));
				orNode.children.add(1, fillTree(orNode, depth - 1));
				return orNode;
			case 2:
				AndNode andNode = new AndNode(parent);
				andNode.children.add(0, fillTree(andNode, depth - 1));
				andNode.children.add(1, fillTree(andNode, depth - 1));
				return andNode;
			case 3:
				IfNode ifNode = new IfNode(parent);
				ifNode.children.add(0, fillTree(ifNode, depth - 1));
				ifNode.children.add(1, fillTree(ifNode, depth - 1));
				ifNode.children.add(2, fillTree(ifNode, depth - 1));
				return ifNode;
			default: // This should NEVER happen!!
				return null;
			}
		} else {
			return getRandomTerminal(parent);
		}
	}

	/**
	 * Generates a grow tree, which can be of defined depth. The root
	 * <code>Node</code> of a grow tree can be a terminal <code>Node</code>.
	 * 
	 * @param depth
	 *            - depth of tree
	 * 
	 * @return - a tree
	 */
	public Node growTree(int depth) {
		Node root = generateRoot(Generator.GROWTREE);

		if (root.getChildren() == null) {
			return root;
		} else {
			int childSize = root.getChildren().capacity();

			if (childSize == 1) {
				root.children.add(0, recGrowTree(root, depth - 1));
			} else if (childSize == 2) {
				root.children.add(0, recGrowTree(root, depth - 1));
				root.children.add(1, recGrowTree(root, depth - 1));
			} else if (childSize == 3) {
				root.children.add(0, recGrowTree(root, depth - 1));
				root.children.add(1, recGrowTree(root, depth - 1));
				root.children.add(2, recGrowTree(root, depth - 1));
			}
			return root;
		}
	}

	/**
	 * A recursive method to creates a grow tree, which can be of defined depth.
	 * 
	 * @param parent
	 *            - the parent <code>Node</code>.
	 * @param depth
	 *            - depth of tree
	 *            
	 * @return - a tree which can be of defined depth.
	 */
	public Node recGrowTree(Node parent, int depth) {
		if (depth > 0) {
			switch (random.nextInt(4)) {
			case 0:
				if (Generator.growStopProbability < random.nextFloat()) {
					NotNode node = new NotNode(parent);
					node.children.add(recGrowTree(node, depth - 1));
					return node;
				} else {
					return getRandomTerminal(parent);
				}
			case 1:
				if (Generator.growStopProbability < random.nextFloat()) {
					OrNode orNode = new OrNode(parent);
					orNode.children.add(0, recGrowTree(orNode, depth - 1));
					orNode.children.add(1, recGrowTree(orNode, depth - 1));
					return orNode;
				} else {
					return getRandomTerminal(parent);
				}
			case 2:
				if (Generator.growStopProbability < random.nextFloat()) {
					AndNode andNode = new AndNode(parent);
					andNode.children.add(0, recGrowTree(andNode, depth - 1));
					andNode.children.add(1, recGrowTree(andNode, depth - 1));
					return andNode;
				} else {
					return getRandomTerminal(parent);
				}
			case 3:
				if (Generator.growStopProbability < random.nextFloat()) {
					IfNode ifNode = new IfNode(parent);
					ifNode.children.add(0, recGrowTree(ifNode, depth - 1));
					ifNode.children.add(1, recGrowTree(ifNode, depth - 1));
					ifNode.children.add(2, recGrowTree(ifNode, depth - 1));
					return ifNode;
				} else {
					return getRandomTerminal(parent);
				}
			default: // This should NEVER happen!!
				return null;
			}
		} else {
			return getRandomTerminal(parent);
		}
	}

	/**
	 * Create <code>Individual</code> object. It will contain both
	 * <code>Node</code> and its fitness.
	 * 
	 * @param root
	 *            - The root of the tree.
	 * 
	 * @return an <code>Individual</code> object.
	 */
	public Individual createIndividual(Node root) {
		return new Individual(root, fitness(root));
	}

}
