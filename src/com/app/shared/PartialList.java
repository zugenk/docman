package com.app.shared;

import java.util.LinkedList;


/**
 * @author Martin - Digibox - WebCode Generator 1.5
 * @project Document Manager
 * @version 1.0.0
 * @createDate 03-10-2017 20:59:59
 */
 
public class PartialList extends LinkedList {
	private int total;
	private int start;
	private int count;

	private static final long serialVersionUID = 4336916498560811824L;

	public PartialList() {
		super();
	}
	
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
}
