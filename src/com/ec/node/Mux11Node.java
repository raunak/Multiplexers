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
public abstract class Mux11Node extends Node{
	
	/** Bit mask for Multiplexer 11 */
	public static final short mask = 1792;
	
	@Override
	public int countNodes() {
		return 0;
	}

	@Override
	public Vector<Node> enumerate() {
		return null;
	}
	
	@Override
	public Vector<Node> enumBounded(int remainingLevels, int incomingDepth) {
		return new Vector<Node>(0);
	}

	@Override
	public int getDepth() {
		return 0;
	}
	
	@Override
	public String toString(){
		return this.getClass().getSimpleName();
	}
	
}
