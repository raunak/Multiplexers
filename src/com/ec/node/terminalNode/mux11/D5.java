package com.ec.node.terminalNode.mux11;

import com.ec.node.Mux11Node;

/**
 * @version 1.0
 */
public class D5 extends Mux11Node{
	
	/** Bit mask for <code>D5</code> terminal node. */
	public static final byte mask = 2;

	@Override
	public boolean eval(int input) {
		return ((input & D5.mask) != 0);
	}

}
