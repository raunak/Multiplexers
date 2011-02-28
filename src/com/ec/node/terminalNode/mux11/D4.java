package com.ec.node.terminalNode.mux11;

import com.ec.node.Mux11Node;

/**
 * @version 1.0
 */
public class D4 extends Mux11Node{

	/** Bit mask for <code>D4</code> terminal node. */
	public static final byte mask = 3;
	
	@Override
	public boolean eval(int input) {
		return ((input & D4.mask) != 0);
	}

}
