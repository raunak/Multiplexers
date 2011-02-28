package com.ec.node.functionNode;

import java.util.Vector;

import com.ec.node.Node;

/**
 * @version 1.0
 */
public abstract class OneChildNode extends FunctionNode {
	
	@Override
	public int countNodes() {
		return getChild().countNodes() + 1;
	}

	@Override
	public Vector<Node> enumerate() {
		Vector<Node> v = new Vector<Node>();
		v.add(this);
		
		Vector<Node> cenum = this.getChild().enumerate();
		if (null != cenum){
			v.addAll(cenum);
		}
		
		return v;
	}

	@Override
	public boolean eval(int input) {
		return !getChild().eval(input);
	}

	@Override
	public int getDepth() {
		return getChild().getDepth() + 1;				
	}

	public void setChild(Node node){
		children.set(0, node);
	}
	
	public Node getChild(){
		return children.get(0);
	}
}
