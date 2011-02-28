package com.ec.node;

import java.util.Vector;

/**
 * @version 1.0
 */
public abstract class Mux6Node extends Node{
	
	/** Bit mask for Multiplexer 6*/
	public static final byte mask = 48;
	
	@Override
	public int countNodes() {
		return 0;
	}

	@Override
	public Vector<Node> enumerate() {
		return null;
	}

	@Override
	public int getDepth() {
		return 0;
	}

	@Override
	public String toString(){
		return this.getClass().getSimpleName();
	}
}
