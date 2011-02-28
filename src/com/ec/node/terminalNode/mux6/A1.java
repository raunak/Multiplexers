package com.ec.node.terminalNode.mux6;

import com.ec.node.Mux6Node;

public class A1 extends Mux6Node{

	public static final byte mask = 4;
	
	@Override
	public boolean eval(int input) {
		return ((input & A1.mask) != 0); 
	}
}
