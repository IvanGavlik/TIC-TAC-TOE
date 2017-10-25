package com.example.util;

import java.util.ArrayList;
import java.util.List;

public class Node<T> {

	private T el;
	private List<Node<T>> childs = new ArrayList<Node<T>>();
	
	
	public T getEl() {
		return el;
	}
	public void setEl(T el) {
		this.el = el;
	}
	public List<Node<T>> getChilds() {
		return childs;
	}
	public void setChilds(List<Node<T>> childs) {
		this.childs = childs;
	}
	
	public void addChilds(Node<T> el) {
		childs.add(el);
	}
	
}
