package com.ec.node.functionNode;

import java.util.Vector;

import com.ec.node.Node;

/**
 * @version 1.0
 */
public class IfNode extends ThreeChildNode{
	
	public IfNode(Node condition, Node leftChild, Node rightChild){
		children = new Vector<Node>(3);
		children.add(0, condition);
		children.add(1, leftChild);
		children.add(2, rightChild);
	}
	
	@Override
	public String toString(){
		return "if[" + getLeftChild().toString() + "," + getMiddleChild().toString() + "," + getRightChild().toString() + "]";
	}

}
