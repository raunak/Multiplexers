package com.ec.node.terminalNode.mux6;

import com.ec.node.Mux6Node;

public class A0 extends Mux6Node{
	
	/** Bit mask for <code>A0</code> terminal node. */
	public static final byte mask = 5;
	
	@Override
	public boolean eval(int input) {
		return ((input & A0.mask) != 0); 
	}
}
