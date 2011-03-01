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
package com.ec.generator;

import com.ec.Individual;
import com.ec.node.Node;
import com.ec.node.functionNode.AndNode;
import com.ec.node.functionNode.IfNode;
import com.ec.node.functionNode.NotNode;
import com.ec.node.functionNode.OrNode;
import com.ec.node.terminalNode.mux6.A0;
import com.ec.node.terminalNode.mux6.A1;
import com.ec.node.terminalNode.mux6.D0;
import com.ec.node.terminalNode.mux6.D1;
import com.ec.node.terminalNode.mux6.D2;
import com.ec.node.terminalNode.mux6.D3;

/**
 * @version 1.0
 */
public class Mux6Generator extends Generator {
	private static final int resMask = 8;
	private static final int addrShift = 4;
	
	public static final int RANDOMNODE = 10;
	public static final int FUNCTIONALNODE = 4;
	
	
	@Override
	public Node getRandomTerminal(Node root) {
		switch (random.nextInt(6)) {
		case 0:
			return new A0(root);
		case 1:
			return new A1(root);
		case 2:
			return new D0(root);
		case 3:
			return new D1(root);
		case 4: 
			return new D2(root);
		case 5:
			return new D3(root);
		default: //Should never happen
			return null;
		}
	}

	@Override
	public Node fullTree(int depth){
		Node root = generateRoot(Mux6Generator.FUNCTIONALNODE);
		
		int rootCapacity = root.getChildren().capacity();
		if(rootCapacity == 1){
			root.children.add(0, fillTree(root, depth - 1));
		} else if (rootCapacity == 2){
			root.children.add(0, fillTree(root, depth - 1));
			root.children.add(1, fillTree(root, depth - 1));
		} else if (rootCapacity == 3){
			root.children.add(0, fillTree(root, depth - 1));
			root.children.add(1, fillTree(root, depth - 1));
			root.children.add(2, fillTree(root, depth - 1));
		}
		return root;
	}
	
	@Override 
	public Node growTree(int depth){
		Node root = generateRoot(Mux6Generator.RANDOMNODE);
		
		if (root.getChildren() == null){
			return root;
			
		} else {
			
			int childSize = root.getChildren().capacity();
			if(childSize == 1){
				root.children.add(0, recGrowTree(root, depth - 1));
			} else if (childSize == 2){
				root.children.add(0, recGrowTree(root, depth - 1));
				root.children.add(1, recGrowTree(root, depth - 1));
			} else if (childSize == 3){
				root.children.add(0, recGrowTree(root, depth - 1));
				root.children.add(1, recGrowTree(root, depth - 1));
				root.children.add(2, recGrowTree(root, depth - 1));
			}
			return root;
		}
	}

	@Override
	public Individual fitness(Node node) {
		int count = 0;
		boolean[] actualAnswer = getTrueResult();
		
		for(int i = 0; i < actualAnswer.length; i++){
			if(actualAnswer[i] == node.eval(i)){
				count++;
			}
		}
		return new Individual(node, count/64f);
	}

	private Node generateRoot(int range){
		switch (random.nextInt(range)) {
		case 0:
			return new NotNode();
		case 1:
			return new OrNode();
		case 2:
			return new AndNode();
		case 3:
			return new IfNode();
		case 4: 
			// TODO: move out terminals to a separate method to balance probabilities
			return new A0(null);
		case 5:
			return new A1(null);
		case 6: 
			return new D0(null);
		case 7: 
			return new D1(null);
		case 8:
			return new D2(null);
		case 9:
			return new D3(null);
		default: // This should NEVER happen!!
			return null;
		}
	}

	private Node fillTree(Node root, int depth) {
		if (depth > 0) {
			switch (random.nextInt(Mux6Generator.FUNCTIONALNODE)) {
			case 0:
				NotNode node = new NotNode(root);
				node.children.add(fillTree(node, depth - 1));
				return node;
			case 1:
				OrNode orNode = new OrNode(root);
				orNode.children.add(0, fillTree(orNode, depth - 1));
				orNode.children.add(1, fillTree(orNode, depth - 1));
				return orNode;
			case 2:
				AndNode andNode = new AndNode(root);
				andNode.children.add(0, fillTree(andNode, depth - 1));
				andNode.children.add(1, fillTree(andNode, depth - 1));
				return andNode;
			case 3:
				IfNode ifNode = new IfNode(root);
				ifNode.children.add(0, fillTree(ifNode, depth - 1));
				ifNode.children.add(1, fillTree(ifNode, depth - 1));
				ifNode.children.add(2, fillTree(ifNode, depth - 1));
				return ifNode;
			default: // This should NEVER happen!!
				return null;
			}
		} 
		else {
			return getRandomTerminal(root);
		}
	}
	
	public Node recGrowTree(Node root, int depth){
		if (depth > 0) {
			switch (random.nextInt(4)) {
			case 0:
				if (Generator.growStopProbability < random.nextDouble()){
					NotNode node = new NotNode(root);
					node.children.add(recGrowTree(node, depth - 1));
					return node;
				} else {
					return getRandomTerminal(root);
				}
			case 1:
				if (Generator.growStopProbability < random.nextDouble()){
					OrNode orNode = new OrNode(root);
					orNode.children.add(0, recGrowTree(orNode, depth - 1));
					orNode.children.add(1, recGrowTree(orNode, depth - 1));
					return orNode;
				} else {
					return getRandomTerminal(root);
				}
			case 2:
				if (Generator.growStopProbability < random.nextDouble()){
					AndNode andNode = new AndNode(root);
					andNode.children.add(0, recGrowTree(andNode, depth - 1));
					andNode.children.add(1, recGrowTree(andNode, depth - 1));
					return andNode;
				} else {
					return getRandomTerminal(root);
				}
			case 3:
				if (Generator.growStopProbability < random.nextDouble()){
					IfNode ifNode = new IfNode(root);
					ifNode.children.add(0, recGrowTree(ifNode, depth - 1));
					ifNode.children.add(1, recGrowTree(ifNode, depth - 1));
					ifNode.children.add(2, recGrowTree(ifNode, depth - 1));
					return ifNode;
				} else {
					return getRandomTerminal(root);
				}
			default: // This should NEVER happen!!
				return null;
			}
		} 
		else {
			return getRandomTerminal(root);
		}
	}
	
	private boolean[] getTrueResult(){
		boolean[] arr = new boolean[64];
		for(int i = 0; i < 64; i ++){
			arr[i] = computeResult(i);
		}
		return arr;
	}

	private boolean computeResult(int input){
		int addr = input >> addrShift;
		int invaddr = 3 - addr;
		int resmask = resMask >> addr;
		int val = input & resmask;
		int res = val >> invaddr;
		return res != 0;
	}
}
