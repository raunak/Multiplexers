package com.ec.node.terminalNode.mux11;

import com.ec.node.Mux11Node;

/**
 * @version 1.0
 */
public class D2 extends Mux11Node{
	
	/** Bit mask for <code>D2</code> terminal node. */
	public static final byte mask = 5;

	@Override
	public boolean eval(int input) {
		return ((input & D2.mask) != 0);
	}

}
