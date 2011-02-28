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
import com.ec.node.terminalNode.mux11.*;
import com.ec.node.terminalNode.mux6.A0;

/**
 * @version 1.0
 */
public class Mux11Generator extends Generator {

	private static final int resMask = 128;
	private static final int addrShift = 8;

	public Mux11Generator(){}
	
	public Mux11Generator(float probability){
		Generator.growStopProbability = probability;
	}
	
	@Override
	public Node getRandomTerminal() {
		switch (random.nextInt(12)) {
		case 0:
			return new A0();
		case 1:
			return new A1();
		case 3:
			return new A2();
		case 4:
			return new D0();
		case 5: 
			return new D1();
		case 6:
			return new D2();
		case 7:
			return new D3();
		case 8:
			return new D4();
		case 9:
			return new D5();
		case 10:
			return new D6();
		case 11:
			return new D7();
		default: //Should never happen
			return new A0();
		}
	}

	@Override
	public Node fullTree(int depth) {
		if (depth > 0) {
			switch (random.nextInt(4)) {
			case 0:
				return new NotNode(fullTree(depth - 1));
			case 1:
				return new OrNode(fullTree(depth - 1), fullTree(depth - 1));
			case 2:
				return new AndNode(fullTree(depth - 1), fullTree(depth - 1));
			case 3:
				return new IfNode(fullTree(depth - 1), fullTree(depth - 1),
						fullTree(depth - 1));
			default: // This should NEVER happen!!
				return new A0();
			}
		} 
		else {
			return getRandomTerminal();
		}
	}

	@Override
	public Node growTree(int depth) {
		if (depth > 0) {
			switch (random.nextInt(4)) {
			case 0:
				if (Generator.growStopProbability < random.nextDouble()){
					return new NotNode(growTree(depth - 1));
				} else {
					return getRandomTerminal();
				}
			case 1:
				if (Generator.growStopProbability < random.nextDouble()){
					return new OrNode(growTree(depth - 1), growTree(depth - 1));
				} else {
					return getRandomTerminal();
				}
			case 2:
				if (Generator.growStopProbability < random.nextDouble()){
					return new AndNode(growTree(depth - 1), growTree(depth - 1));
				} else {
					return getRandomTerminal();
				}
			case 3:
				if (Generator.growStopProbability < random.nextDouble()){
					return new IfNode(growTree(depth - 1), growTree(depth - 1), growTree(depth - 1));
				} else {
					return getRandomTerminal();
				}
			default: // This should NEVER happen!!
				return new A0();
			}
		} 
		else {
			return getRandomTerminal();
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
		return new Individual(node, count/2048f);
	}

	private boolean[] getTrueResult(){
		boolean[] arr = new boolean[2048];
		for(int i = 0; i < 2048; i ++){
			arr[i] = computeResult(i);
		}
		return arr;
	}

	private boolean computeResult(int input){
		int addr = input >> addrShift;
		int invaddr = 7 - addr;
		int resmask = resMask >> addr;
		int val = input & resmask;
		int res = val >> invaddr;
		return res != 0;
	}
}
