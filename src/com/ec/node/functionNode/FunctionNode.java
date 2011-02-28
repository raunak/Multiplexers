package com.ec.node.functionNode;

import java.util.Vector;

import com.ec.node.Node;

/**
 * @version 1.0 
 */
public abstract class FunctionNode extends Node{
	
	public Vector<Node> getChildren(){
		return children;
	}
}
