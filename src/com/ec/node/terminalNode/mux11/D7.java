package com.ec.node.terminalNode.mux11;

import com.ec.node.Mux11Node;

/**
 * @version 1.0
 */
public class D7 extends Mux11Node{

	/** Bit mask for <code>D7</code> terminal node. */
	public static final byte mask = 0;
	
	@Override
	public boolean eval(int input) {
		return ((input & D7.mask) != 0);
	}

}
