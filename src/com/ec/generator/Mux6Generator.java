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
import com.ec.node.terminalNode.mux6.*;

/**
 * <code>Mux6Generator</code> generates trees (both full and grow) which belong
 * to Multiplexer 6 problem.
 * 
 * @author raunak
 * @version 1.0
 */
public class Mux6Generator extends Generator {

	private static final int resMask = 8;

	private static final int addrShift = 4;

	/**
	 * Holds the correct answers for Multiplexer 6 problem.
	 */
	private static final boolean[] correctAnswer = getCorrectAnswer();

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
		default: // Should never happen
			return null;
		}
	}

	@Override
	protected double fitness(Node node) {
		int count = 0;

		for (int i = 0; i < correctAnswer.length; i++) {
			if (correctAnswer[i] == node.eval(i)) {
				count++;
			}
		}
		return count / 64d;
	}

	/**
	 * Gets the correct answer for all input cases for Multiplexer 6 problem.
	 * 
	 * @return a boolean array
	 */
	private static boolean[] getCorrectAnswer() {
		boolean[] arr = new boolean[64];
		for (int i = 0; i < 64; i++) {
			arr[i] = computeAnswer(i);
		}
		return arr;
	}

	/**
	 * Computes the correct answer for Multiplexer 6 problem for a given input.
	 * 
	 * @param input
	 *            - range 0 : 63.
	 * 
	 * @return a boolean value.
	 */
	private static boolean computeAnswer(int input) {
		int addr = input >> addrShift;
		int invaddr = 3 - addr;
		int resmask = resMask >> addr;
		int val = input & resmask;
		int res = val >> invaddr;
		return res != 0;
	}
}
