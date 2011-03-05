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

import com.ec.node.Node;

/**
 * <code>IfNode</code> has three children; one on left (the condition child),
 * one on center (to execute if condition is true), and one on right (to execute
 * if condition is false).
 * 
 * @author raunak
 * @version 1.0
 */
public class IfNode extends ThreeChildNode {

	/**
	 * Constructs <code>IfNode</code> with parent set to <code>null</code>.
	 */
	public IfNode() {
		this(null);
	}

	/**
	 * Constructs <code>IfNode</code> using passed parameters.
	 * 
	 * @param parent
	 *            - The parent of this node.
	 */
	public IfNode(Node parent) {
		this.parent = parent;
		children = new Vector<Node>(3);
	}

	/**
	 * Constructs <code>IfNode</code> using passed parameters.
	 * 
	 * @param parent
	 *            - The parent of <code>IfNode</code>.
	 * @param leftChild
	 *            - The node to the left of <code>IfNode</code>
	 * @param middleChild
	 *            - The node to the middle of <code>IfNode</code>
	 * @param rightChild
	 *            - The node to the right of <code>IfNode</code>
	 */
	public IfNode(Node parent, Node leftChild, Node middleChild, Node rightChild) {
		this.parent = parent;

		children = new Vector<Node>(3);
		children.add(0, leftChild);
		children.add(1, middleChild);
		children.add(2, rightChild);
	}

	@Override
	public Node clone(Node parent) {
		if (parent == null) {
			IfNode ifNode = new IfNode();
			ifNode.children.add(0, getLeftChild().clone(ifNode));
			ifNode.children.add(1, getMiddleChild().clone(ifNode));
			ifNode.children.add(2, getRightChild().clone(ifNode));
			return ifNode;
		} else {
			IfNode ifNode = new IfNode(parent);
			ifNode.children.add(0, getLeftChild().clone(ifNode));
			ifNode.children.add(1, getMiddleChild().clone(ifNode));
			ifNode.children.add(2, getRightChild().clone(ifNode));
			return ifNode;
		}
	}

	@Override
	public boolean eval(int input) {
		return getLeftChild().eval(input) ? getMiddleChild().eval(input)
				: getRightChild().eval(input);
	}

	@Override
	public String toString() {
		return "If[" + getLeftChild().toString() + ","
				+ getMiddleChild().toString() + ","
				+ getRightChild().toString() + "]";
	}

}
