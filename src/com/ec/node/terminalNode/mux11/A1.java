package com.ec.node.terminalNode.mux11;

import com.ec.node.Mux11Node;

/**
 * @version 1.0
 */
public class A1 extends Mux11Node{
	
	/** Bit mask for <code>A1</code> terminal node. */
	public static final byte mask = 9;

	@Override
	public boolean eval(int input) {
		return ((input & A1.mask) != 0); 
	}

}
