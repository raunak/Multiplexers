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
package com.ec.node;

import java.util.Vector;

/**
 * @version 1.0 
 */
public abstract class Node {
	
	public Node parent;
	
	public boolean isRoot;
	
	/** Children of a Node */
	public Vector<Node> children;
	
	/** returns max depth of Node */
	public abstract int getDepth();
	
	public int getLevel() {
		if (parent == null ) return 0;
		else return parent.getLevel() +1;
	}
	
	/** returns the number of nodes */
	public abstract int countNodes();
	
	/** evaluates a program for a given input */
	public abstract boolean eval(int input);
	
	/** returns an vector will all the child nodes*/
	public abstract Vector<Node> enumerate();
	
	public abstract Vector<Node> enumBounded(int remainingDepth, int subtreeDepth);

	/** returns children for a node */
	public Vector<Node> getChildren() {
		return children;
	}
	
	public boolean isRoot(){
		return isRoot;
	}
	
	public void setIsRoot(boolean isRoot){
		this.isRoot = isRoot;
	}
	
	public Node getParent(){
		return parent;
	}
	
	public void setParent(Node node){
		this.parent = node;
	}
}
