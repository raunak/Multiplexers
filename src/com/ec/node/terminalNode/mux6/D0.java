package com.ec.node.terminalNode.mux6;

import com.ec.node.Mux6Node;

public class D0 extends Mux6Node{
	
	public static final byte mask = 3;
	
	@Override
	public boolean eval(int input) {
		return ((input & D0.mask) != 0); 
	}
}
