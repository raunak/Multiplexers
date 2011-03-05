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
 * <code>OneChildNode</code> represent all the nodes that have one child. 
 * 
 * @author raunak
 * @version 1.0
 */
public abstract class OneChildNode extends Node {

	@Override
	public int countNodes() {
		return getChild().countNodes() + 1;
	}

	@Override
	public Vector<Node> enumerate() {
		Vector<Node> v = new Vector<Node>();
		v.add(this);

		Vector<Node> cenum = this.getChild().enumerate();
		if (null != cenum) {
			v.addAll(cenum);
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
		Vector<Node> cenum = this.getChild().enumBounded(remainingDepth, subtreeDepth);
		v.addAll(cenum);
		return v;
	}

	@Override
	public int getDepth() {
		return getChild().getDepth() + 1;
	}

	/**
	 * Gets the child node
	 * 
	 * @return <code>Node</code> child.
	 */
	public Node getChild() {
		return children.get(0);
	}

	/**
	 * Set the child node for <code>OneChildNode</code>.
	 * 
	 * @param child
	 */
	public void setChild(Node child) {
		children.set(0, child);
	}
}
