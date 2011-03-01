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
 * @version 1.0
 */
public class A0 extends Mux11Node{

	/** Bit mask for <code>A0</code> terminal node. */
	public static final byte mask = 10;
	
	public A0(Node node){
		this.parent = node;
	}
	
	@Override
	public boolean eval(int input) {
		return ((input & A0.mask) != 0); 
	}
}
