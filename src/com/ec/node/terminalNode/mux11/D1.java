package com.ec.node.terminalNode.mux11;

import com.ec.node.Mux11Node;

/**
 * @version 1.0
 */
public class D1 extends Mux11Node{

	/** Bit mask for <code>D1</code> terminal node. */
	public static final byte mask = 6;
	
	@Override
	public boolean eval(int input) {
		return ((input & D1.mask) != 0);
	}

}
