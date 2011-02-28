package com.ec.node.terminalNode.mux6;

import com.ec.node.Mux6Node;

public class D1 extends Mux6Node{

	public static final byte mask = 2;
	
	@Override
	public boolean eval(int input) {
		return ((input & D1.mask) != 0); 
	}
}
