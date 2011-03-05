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
 * <code>AndNode</code> has two children; one on left, and one on right.
 * <code>AndNode</code> uses logical <code>and</code> when evaluating itself.
 * 
 * @author raunak
 * @version 1.0
 */
public class AndNode extends TwoChildNode {

	/**
	 * Constructs <code>AndNode</code> with parent set to <code>null</code>.
	 */
	public AndNode() {
		this(null);
	}

	/**
	 * Constructs <code>AndNode</code> using passed parameters.
	 * 
	 * @param parent
	 *            - The parent of this node.
	 */
	public AndNode(Node parent) {
		this.parent = parent;
		children = new Vector<Node>(2);
	}

	/**
	 * Constructs <code>AndNode</code> using passed parameters.
	 * 
	 * @param parent
	 *            - The parent of <code>AndNode</code>.
	 * @param leftChild
	 *            - The node to the left of <code>AndNode</code>
	 * @param rightChild
	 *            - The node to the right of <code>AndNode</code>
	 */
	public AndNode(Node parent, Node leftChild, Node rightChild) {
		this.parent = parent;

		children = new Vector<Node>(2);
		children.add(0, leftChild);
		children.add(1, rightChild);
	}

	@Override
	public Node clone(Node parent) {
		if (parent == null) {
			AndNode andNode = new AndNode();
			andNode.children.add(0, getLeftChild().clone(andNode));
			andNode.children.add(1, getRightChild().clone(andNode));
			return andNode;
		} else {
			AndNode andNode = new AndNode(parent);
			andNode.children.add(0, getLeftChild().clone(andNode));
			andNode.children.add(1, getRightChild().clone(andNode));
			return andNode;
		}
	}

	@Override
	public boolean eval(int input) {
		return getLeftChild().eval(input) && getRightChild().eval(input);
	}

	@Override
	public String toString() {
		return "And[" + getLeftChild().toString() + ","
				+ getRightChild().toString() + "]";
	}

}
