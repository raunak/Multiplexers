package com.ec.node;

import java.util.Vector;

/**
 * @version 1.0 
 */
public abstract class Mux11Node extends Node{
	
	/** Bit mask for Multiplexer 11 */
	public static final short mask = 1792;
	
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
