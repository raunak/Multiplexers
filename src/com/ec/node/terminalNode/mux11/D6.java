package com.ec.node.terminalNode.mux11;

import com.ec.node.Mux11Node;

/**
 * @version 1.0
 */
public class D6 extends Mux11Node{

	/** Bit mask for <code>D6</code> terminal node. */
	public static final byte mask = 1;
	
	@Override
	public boolean eval(int input) {
		return ((input & D6.mask) != 0);
	}

}
