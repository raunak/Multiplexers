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
 * @version 1.0
 */
public abstract class Node{

	/** Parent node */
	public Node parent;

	/** Children of a Node */
	public Vector<Node> children;

	/** returns max depth of Node */
	public abstract int getDepth();

	/** evaluates a program for a given input */
	public abstract boolean eval(int input);

	/** returns an vector will all the child nodes */
	public abstract Vector<Node> enumerate();

	/** */
	public abstract Vector<Node> enumBounded(int remainingDepth,
			int subtreeDepth);

	/** returns the number of nodes */
	public abstract int countNodes();

	/** Clones an object */
	public abstract Node clone(Node root);
	
	/**
	 * Gets the level of <code>Node</code> relative to root node.
	 * 
	 * @return int
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
