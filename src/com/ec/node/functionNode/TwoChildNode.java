package com.ec.node.functionNode;

import java.util.Vector;

import com.ec.node.Node;

/**
 * @version 1.0
 */
public abstract class TwoChildNode extends FunctionNode {
	
	@Override
	public int countNodes() {
		return getLeftChild().countNodes() + getRightChild().countNodes()
				+ 2;
	}

	@Override
	public Vector<Node> enumerate() {
		Vector<Node> v = new Vector<Node>();
		v.add(this);
		
		Vector<Node> lenum = this.getLeftChild().enumerate();
		if (null != lenum){
			v.addAll(lenum);
		}
			
		Vector<Node> renum = this.getRightChild().enumerate();
		if (null != renum){
			v.addAll(renum);
		}
		return v;
	}

	@Override
	public boolean eval(int input) {
		return getLeftChild().eval(input) && getRightChild().eval(input);
	}

	@Override
	public int getDepth() {
		return Math.max(getLeftChild().getDepth(), getRightChild().getDepth()) + 1;
	}

	public Node getLeftChild() {
		return children.get(0);
	}

	public void setLeftChild(Node node) {
		children.set(0, node);
	}

	public Node getRightChild() {
		return children.get(1);
	}

	public void setRightChild(Node node) {
		children.set(1, node);
	}
	
}
