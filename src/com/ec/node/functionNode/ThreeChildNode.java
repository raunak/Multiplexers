package com.ec.node.functionNode;

import java.util.Vector;

import com.ec.node.Node;

/**
 * @version 1.0
 */
public abstract class ThreeChildNode extends FunctionNode {

	@Override
	public int countNodes() {
		return getLeftChild().countNodes() + getMiddleChild().countNodes()
				+ getRightChild().countNodes() + 3;
	}

	@Override
	public boolean eval(int input) {
		return getLeftChild().eval(input) ? getMiddleChild().eval(input)
				: getRightChild().eval(input);
	}

	@Override
	public Vector<Node> enumerate() {
		Vector<Node> v = new Vector<Node>();
		v.add(this);
		
		Vector<Node> lenum = this.getLeftChild().enumerate();
		if (null != lenum){
			v.addAll(lenum);
		}
			
		Vector<Node> menum = this.getMiddleChild().enumerate();
		if (null != menum){
			v.addAll(menum);
		}
			
		Vector<Node> renum = this.getRightChild().enumerate();
		if (null != renum){
			v.addAll(renum);
		}
		
		return v;
	}

	@Override
	public int getDepth() {
		return Math
				.max(getLeftChild().getDepth(), Math.max(getMiddleChild()
						.getDepth(), getRightChild().getDepth())) + 1;
	}

	public Node getLeftChild() {
		return children.get(0);
	}

	public void setLeftChild(Node node) {
		children.set(0, node);
	}

	public Node getMiddleChild() {
		return children.get(1);
	}

	public void setMiddleChild(Node node) {
		children.set(1, node);
	}

	public Node getRightChild() {
		return children.get(2);
	}

	public void setRightChild(Node node) {
		children.set(2, node);
	}

}
