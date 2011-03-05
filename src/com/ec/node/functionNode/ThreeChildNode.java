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
package com.ec.node.functionNode;

import java.util.Vector;

import com.ec.Evolution;
import com.ec.node.Node;

/**
 * <code>ThreeChildNode</code> represent all the nodes that have three children.
 * 
 * @author raunak
 * @version 1.0
 */
public abstract class ThreeChildNode extends Node {

	@Override
	public int countNodes() {
		return getLeftChild().countNodes() + getMiddleChild().countNodes()
				+ getRightChild().countNodes() + 3;
	}

	@Override
	public Vector<Node> enumerate() {
		Vector<Node> v = new Vector<Node>();
		v.add(this);

		Vector<Node> lenum = this.getLeftChild().enumerate();
		if (null != lenum) {
			v.addAll(lenum);
		}

		Vector<Node> menum = this.getMiddleChild().enumerate();
		if (null != menum) {
			v.addAll(menum);
		}

		Vector<Node> renum = this.getRightChild().enumerate();
		if (null != renum) {
			v.addAll(renum);
		}

		return v;
	}

	@Override
	public Vector<Node> enumBounded(int remainingDepth, int subtreeDepth) {
		Vector<Node> v = new Vector<Node>();
		int mydepth = this.getDepth();
		int leftSpace = Evolution.maxDepth - this.getLevel();
		if ((mydepth <= remainingDepth) && (leftSpace >= subtreeDepth)) {
			v.add(this);
		}
		Vector<Node> lenum = this.getLeftChild().enumBounded(remainingDepth,
				subtreeDepth);
		v.addAll(lenum);
		Vector<Node> menum = this.getMiddleChild().enumBounded(remainingDepth,
				subtreeDepth);
		v.addAll(menum);
		Vector<Node> renum = this.getRightChild().enumBounded(remainingDepth,
				subtreeDepth);
		v.addAll(renum);
		return v;
	}

	@Override
	public int getDepth() {
		return Math.max(getLeftChild().getDepth(), Math.max(getMiddleChild()
				.getDepth(), getRightChild().getDepth())) + 1;
	}

	/**
	 * Gets the left child node
	 * 
	 * @return left child <code>Node</code>.
	 */
	public Node getLeftChild() {
		return children.get(0);
	}

	/**
	 * Set the left child node for <code>ThreeChildNode</code>.
	 * 
	 * @param leftChild
	 */
	public void setLeftChild(Node leftChild) {
		children.set(0, leftChild);
	}

	/**
	 * Gets the left child node
	 * 
	 * @return middle child <code>Node</code>.
	 */
	public Node getMiddleChild() {
		return children.get(1);
	}

	/**
	 * Set the middle child node for <code>ThreeChildNode</code>.
	 * 
	 * @param middleChild
	 */
	public void setMiddleChild(Node middleChild) {
		children.set(1, middleChild);
	}

	/**
	 * Gets the left child node
	 * 
	 * @return right child <code>Node</code>.
	 */
	public Node getRightChild() {
		return children.get(2);
	}

	/**
	 * Set the right child node for <code>ThreeChildNode</code>.
	 * 
	 * @param rightChild
	 */
	public void setRightChild(Node rightChild) {
		children.set(2, rightChild);
	}

}
