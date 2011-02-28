package com.ec.node.terminalNode.mux11;

import com.ec.node.Mux11Node;

/**
 * @version 1.0
 */
public class A2 extends Mux11Node{
	
	/** Bit mask for <code>A2</code> terminal node. */
	public static final byte mask = 8;

	@Override
	public boolean eval(int input) {
		return ((input & A2.mask) != 0); 
	}

}
