package com.ec.node.functionNode;

import java.util.Vector;

import com.ec.node.Node;

/**
 * @version 1.0 
 */
public class AndNode extends TwoChildNode {
	
	public AndNode(Node leftChild, Node rightChild) {
		children = new Vector<Node>(2);
		children.add(0, leftChild);
		children.add(1, rightChild);
	}

	@Override
	public String toString() {
		return "and[" + getLeftChild().toString() + ","
				+ getRightChild().toString() + "]";
	}

}
