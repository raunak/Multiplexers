package com.ec.node.terminalNode.mux6;

import com.ec.node.Mux6Node;

public class D3 extends Mux6Node{
	
	public static final byte mask = 0;
	
	@Override
	public boolean eval(int input) {
		return ((input & D3.mask) != 0); 
	}
}
