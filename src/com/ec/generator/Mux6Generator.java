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
 * @version 1.0
 */
public class Mux6Generator extends Generator {
	/** */
	private static final int resMask = 8;
	
	/** */
	private static final int addrShift = 4;
	
	/** */
	private static final boolean[] correctAnswer = getTrueResult();

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
	protected float fitness(Node node) {
		int count = 0;

		for (int i = 0; i < correctAnswer.length; i++) {
			if (correctAnswer[i] == node.eval(i)) {
				count++;
			}
		}
		return count / 64f;
	}

	/**
	 * 
	 * @return
	 */
	private static boolean[] getTrueResult() {
		boolean[] arr = new boolean[64];
		for (int i = 0; i < 64; i++) {
			arr[i] = computeResult(i);
		}
		return arr;
	}

	/**
	 * 
	 * @param input
	 * @return
	 */
	private static boolean computeResult(int input) {
		int addr = input >> addrShift;
		int invaddr = 3 - addr;
		int resmask = resMask >> addr;
		int val = input & resmask;
		int res = val >> invaddr;
		return res != 0;
	}
}
