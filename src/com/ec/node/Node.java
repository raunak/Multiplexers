package com.ec.node;

import java.util.Vector;

/**
 * @version 1.0 
 */
public abstract class Node {
	
	/** Children of a Node */
	public Vector<Node> children;
	
	/** returns max depth of Node */
	public abstract int getDepth();
	
	/** returns the number of nodes */
	public abstract int countNodes();
	
	/** evaluates a program for a given input */
	public abstract boolean eval(int input);
	
	/** returns an vector will all the child nodes*/
	public abstract Vector<Node> enumerate();

	/** returns children for a node */
	public Vector<Node> getChildren() {
		return children;
	}
}
