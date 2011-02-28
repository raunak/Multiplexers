package com.ec.node.terminalNode.mux11;

import com.ec.node.Mux11Node;

/**
 * @version 1.0
 */
public class D3 extends Mux11Node{

	/** Bit mask for <code>D3</code> terminal node. */
	public static final byte mask = 4;
	
	@Override
	public boolean eval(int input) {
		return ((input & D3.mask) != 0);
	}

}
