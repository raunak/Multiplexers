package com.ec.node.terminalNode.mux6;

import com.ec.node.Mux6Node;

public class D2 extends Mux6Node{

	public static final byte mask = 1;
	
	@Override
	public boolean eval(int input) {
		return ((input & D2.mask) != 0); 
	}
}
