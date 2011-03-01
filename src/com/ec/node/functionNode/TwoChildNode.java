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
 * @version 1.0
 */
public abstract class TwoChildNode extends FunctionNode {

	@Override
	public int countNodes() {
		return getLeftChild().countNodes() + getRightChild().countNodes() + 2;
	}

	@Override
	public Vector<Node> enumerate() {
		Vector<Node> v = new Vector<Node>();
		v.add(this);

		Vector<Node> lenum = this.getLeftChild().enumerate();
		if (null != lenum) {
			v.addAll(lenum);
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
		Vector<Node> renum = this.getRightChild().enumBounded(remainingDepth,
				subtreeDepth);
		v.addAll(renum);
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
