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
package com.ec.node;

import java.util.Vector;

/**
 * <code>Node</code> is a base class. All other <code>Node</code> such as
 * FunctionNode (<code>AndNode</code>, <code>OrNode</code>, <code>IfNode</code>,
 * <code>NotNode</code>) and TerminalNode (<code>A0</code>, <code>A1</code>,
 * <code>D0</code> ... <code>D8</code>) extend this class.
 * 
 * @author raunak
 * @version 1.0
 */
public abstract class Node {

	/**
	 * Holds reference to parent <code>Node</code>.
	 */
	public Node parent;

	/**
	 * A <code>Node</code>'s children.
	 */
	public Vector<Node> children;

	/**
	 * Gets the tree depth.
	 * 
	 * @return depth
	 */
	public abstract int getDepth();

	/**
	 * Evaluate the program (represented by a Tree) for a given input.
	 * 
	 * @param input
	 *            - The value against which the program (Tree) is tested.
	 * 
	 * @return boolean
	 */
	public abstract boolean eval(int input);

	/**
	 * Gathers all the child <code>Node</code>s in a vector.
	 * 
	 * @return a vector containing child <code>Node</code>.
	 */
	public abstract Vector<Node> enumerate();

	/**
	 * Finds potential swap points for crossover.
	 * 
	 * @param remainingDepth
	 *            -
	 * @param subtreeDepth
	 *            -
	 * @return a vector containing swap points
	 */
	public abstract Vector<Node> enumBounded(int remainingDepth,
			int subtreeDepth);

	/**
	 * Counts the total number of <code>Node</code>s that are present in a given
	 * program.
	 * 
	 * @return number of <code>Node</code>'s.
	 */
	public abstract int countNodes();

	/**
	 * Performs a deep copy of <code>Node</code>.
	 * 
	 * @param parent
	 *            - Parent <code>Node</code>
	 * 
	 * @return a deep copy of <code>Node</code> on which <code>.clone()</code>
	 *         was called on.
	 */
	public abstract Node clone(Node parent);

	/**
	 * Gets the depth of a <code>Node</code> relative to root node.
	 * 
	 * @return depth
	 */
	public int getLevel() {
		if (parent == null)
			return 0;
		else
			return parent.getLevel() + 1;
	}

	/**
	 * Gets children of a <code>Node</code>.
	 * 
	 * @return children
	 */
	public Vector<Node> getChildren() {
		return children;
	}

	/**
	 * Gets the parent of a <code>Node</code>
	 * 
	 * @return parent
	 */
	public Node getParent() {
		return parent;
	}

	/**
	 * Set the parent of a <code>Node</code>
	 * 
	 * @param parent
	 *            - <code>Node</code> Object
	 */
	public void setParent(Node parent) {
		this.parent = parent;
	}
}
