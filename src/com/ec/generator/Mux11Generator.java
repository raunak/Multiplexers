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

import com.ec.node.Node;
import com.ec.node.terminalNode.mux11.A0;
import com.ec.node.terminalNode.mux11.A1;
import com.ec.node.terminalNode.mux11.A2;
import com.ec.node.terminalNode.mux11.D0;
import com.ec.node.terminalNode.mux11.D1;
import com.ec.node.terminalNode.mux11.D2;
import com.ec.node.terminalNode.mux11.D3;
import com.ec.node.terminalNode.mux11.D4;
import com.ec.node.terminalNode.mux11.D5;
import com.ec.node.terminalNode.mux11.D6;
import com.ec.node.terminalNode.mux11.D7;

/**
 * <code>Mux11Generator</code> generates trees (both full and grow) which belong
 * to Multiplexer 11 problem.
 * 
 * @author raunak
 * @version 1.0
 */
public class Mux11Generator extends Generator {

	private static final int resMask = 128;

	private static final int addrShift = 8;

	/**
	 * Holds the correct answers for Multiplexer 11 problem.
	 */
	private static final boolean[] correctAnswer = getCorrectAnswer();

	@Override
	public Node getRandomTerminal(Node root) {
		switch (random.nextInt(11)) {
		case 0:
			return new A0(root);
		case 1:
			return new A1(root);
		case 2:
			return new A2(root);
		case 3:
			return new D0(root);
		case 4:
			return new D1(root);
		case 5:
			return new D2(root);
		case 6:
			return new D3(root);
		case 7:
			return new D4(root);
		case 8:
			return new D5(root);
		case 9:
			return new D6(root);
		case 10:
			return new D7(root);
		default: // Should never happen
			return null;
		}
	}

	@Override
	public double fitness(Node node) {
		int count = 0;

		for (int i = 0; i < correctAnswer.length; i++) {
			if (correctAnswer[i] == node.eval(i)) {
				count++;
			}
		}
		return count / 2048d;
	}

	/**
	 * Gets the correct answer for all input cases for Multiplexer 11 problem.
	 * 
	 * @return a boolean array
	 */
	private static boolean[] getCorrectAnswer() {
		boolean[] arr = new boolean[2048];
		for (int i = 0; i < 2048; i++) {
			arr[i] = computeAnswer(i);
		}
		return arr;
	}

	/**
	 * Computes the correct answer for Multiplexer 11 problem for a given input.
	 * 
	 * @param input
	 *            - range 0 : 2047.
	 * 
	 * @return a boolean value.
	 */
	private static boolean computeAnswer(int input) {
		int addr = input >> addrShift;
		int invaddr = 7 - addr;
		int resmask = resMask >> addr;
		int val = input & resmask;
		int res = val >> invaddr;
		return res != 0;
	}
}
