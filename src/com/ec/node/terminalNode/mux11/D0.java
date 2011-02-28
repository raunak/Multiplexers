package com.ec.node.terminalNode.mux11;

import com.ec.node.Mux11Node;

/**
 * @version 1.0
 */
public class D0 extends Mux11Node{
	
	/** Bit mask for <code>D0</code> terminal node. */
	public static final byte mask = 7;

	@Override
	public boolean eval(int input) {
		return ((input & D0.mask) != 0); 
	}

}
