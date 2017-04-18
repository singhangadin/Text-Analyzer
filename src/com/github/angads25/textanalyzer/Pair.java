package com.github.angads25.textanalyzer;

import java.io.Serializable;

public class Pair<F,S> implements Serializable {
	/**	
	 * 	Version 1.0
	 */
	private static final long serialVersionUID = 2370926083421246835L;
	
	private F first;
	private S second;

	public Pair(F first, S second) {
		this.first = first;
		this.second = second;
	}

	public F getFirst() { 
		return first; 
	}
	
	public S getSecond() { 
		return second; 
	}

	@Override
	public int hashCode() { 
		return first.hashCode() ^ second.hashCode(); 
	}
	
	@Override
	public String toString() {
		return "["+first+","+second+"]";
	}
}
