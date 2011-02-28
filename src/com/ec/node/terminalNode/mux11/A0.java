package com.ec.node.terminalNode.mux11;

import com.ec.node.Mux11Node;

/**
 * @version 1.0
 */
public class A0 extends Mux11Node{

	/** Bit mask for <code>A0</code> terminal node. */
	public static final byte mask = 10;
	
	@Override
	public boolean eval(int input) {
		return ((input & A0.mask) != 0); 
	}
}
