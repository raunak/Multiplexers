package com.ec.node.functionNode;

import java.util.Vector;

import com.ec.node.Node;

/**
 * @version 1.0 
 */
public class NotNode extends OneChildNode{
	
	public NotNode(Node node){
		children = new Vector<Node>(1);
		children.add(node);
	}
	
	@Override
	public String toString(){
		return "not[" + getChild().toString() + "]";
	}
}
