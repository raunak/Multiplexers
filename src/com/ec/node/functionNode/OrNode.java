package com.ec.node.functionNode;

import java.util.Vector;

import com.ec.node.Node;

/**
 * @version 1.0
 */
public class OrNode extends TwoChildNode{
	
	public OrNode(Node leftChild, Node rightChild){
		children = new Vector<Node>(2);
		children.add(0, leftChild);
		children.add(1, rightChild);
	}

	@Override
	public String toString(){
		return "or[" + getLeftChild().toString() + "," + getRightChild().toString() + "]";
	}
}
