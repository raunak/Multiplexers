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
 * <code>NotNode</code> has one child and uses logical <code>not</code> when
 * evaluating itself.
 * 
 * @author raunak
 * @version 1.0
 */
public class NotNode extends OneChildNode {

	/**
	 * Constructs <code>NotNode</code> with parent set to <code>null</code>.
	 */
	public NotNode() {
		this(null);
	}

	/**
	 * Constructs <code>NotNode</code> using passed parameters.
	 * 
	 * @param parent
	 *            - The parent of this node.
	 */
	public NotNode(Node parent) {
		this.parent = parent;
		children = new Vector<Node>(1);
	}

	/**
	 * Constructs <code>NotNode</code> using passed parameters.
	 * 
	 * @param parent
	 *            - The parent of <code>NotNode</code>.
	 * @param child
	 *            - The node to the middle of <code>NotNode</code>
	 */
	public NotNode(Node parent, Node child) {
		this.parent = parent;
		children = new Vector<Node>(1);
		children.add(0, child);
	}

	@Override
	public Node clone(Node parent) {
		if (parent == null) {
			NotNode notNode = new NotNode();
			notNode.children.add(0, getChild().clone(notNode));
			return notNode;
		} else {
			NotNode notNode = new NotNode(parent);
			notNode.children.add(0, getChild().clone(notNode));
			return notNode;
		}
	}

	@Override
	public boolean eval(int input) {
		return !getChild().eval(input);
	}

	@Override
	public String toString() {
		return "Not[" + getChild().toString() + "]";
	}
}
