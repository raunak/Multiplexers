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
package com.ec.node.terminalNode.mux11;

import com.ec.node.Mux11Node;
import com.ec.node.Node;

/**
 * @author raunak
 * @version 1.0
 */
public class D5 extends Mux11Node {

	/** Bit mask for <code>D5</code> terminal node. */
	public static final byte mask = 4;

	public D5(Node node) {
		this.parent = node;
	}

	@Override
	public boolean eval(int input) {
		return ((input & D5.mask) != 0);
	}

	@Override
	public Node clone(Node parent) {
		return new D5(parent);
	}
}
